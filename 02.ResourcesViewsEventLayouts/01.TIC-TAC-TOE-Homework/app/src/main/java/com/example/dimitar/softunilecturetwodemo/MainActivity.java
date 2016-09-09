package com.example.dimitar.softunilecturetwodemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class MainActivity extends Activity implements IRecycleViewSelectedElement {

    private static final int MAX_CHOICES = 5;

    private Context mCtx;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> data;
    private HashSet<Integer> choicesMade;

    private Random rndGenerator;
    private boolean isWin = false;
    private int choicesCount;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        choicesMade = new HashSet<>();
        rndGenerator = new Random();

        for (int index = 0; index < 9; index++) {
            data.add(index, String.valueOf(""));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleViewAdapter(data, this);

        mRecyclerView.setAdapter(mAdapter);

        RecycleViewCustomDecoration itemCustomDecoration = new RecycleViewCustomDecoration();

        mRecyclerView.addItemDecoration(itemCustomDecoration);

        mCtx = this;
    }

    @Override
    public void onItemSelected(int position) {
        if (!isWin) {
            makeAChoice(position);
        }

        mAdapter.notifyDataSetChanged();
    }

    private void makeAChoice(int position) {
        int n = rndGenerator.nextInt(mAdapter.getItemCount());
        Boolean isPlayerMadeAChoice = false;

        if (!choicesMade.contains(position) ) {
            choicesCount++;

            data.remove(position);
            data.add(position, getString(R.string.player_sign));
            choicesMade.add(position);

            isPlayerMadeAChoice = true;

            if (checkIfHasAWinner(getString(R.string.player_sign))) {
                Toast.makeText(mCtx, getString(R.string.player_win), Toast.LENGTH_LONG).show();
                isWin = true;
            }
        }

        if (choicesCount < MAX_CHOICES && isPlayerMadeAChoice && !isWin) {
            while (choicesMade.contains(n)) {
                n = rndGenerator.nextInt(mAdapter.getItemCount());
            }

            data.remove(n);
            data.add(n, getString(R.string.computer_sign));
            choicesMade.add(n);

            if (checkIfHasAWinner(getString(R.string.computer_sign))) {
                Toast.makeText(mCtx, getString(R.string.player_lose), Toast.LENGTH_LONG).show();
                isWin = true;
            }
        }
    }

    private Boolean checkIfHasAWinner(String sign) {
        return lineCombinationChecker(sign, 0, 4, 8) ||
                lineCombinationChecker(sign, 0, 1, 2) ||
                lineCombinationChecker(sign, 0, 3, 6) ||
                lineCombinationChecker(sign, 2, 5, 8) ||
                lineCombinationChecker(sign, 8, 7, 6) ||
                lineCombinationChecker(sign, 2, 4, 6) ||
                lineCombinationChecker(sign, 3, 4, 5) ||
                lineCombinationChecker(sign, 1, 4, 7);
    }

    private Boolean lineCombinationChecker(String sign, int firstPosition, int secondPosition, int thirdPosition) {
        return data.get(firstPosition).equals(sign) &&
                data.get(secondPosition).equals(sign) &&
                data.get(thirdPosition).equals(sign);
    }
}
