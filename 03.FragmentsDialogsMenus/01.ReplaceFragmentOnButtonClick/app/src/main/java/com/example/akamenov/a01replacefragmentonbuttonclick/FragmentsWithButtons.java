package com.example.akamenov.a01replacefragmentonbuttonclick;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by AKamenov on 9/16/2016.
 */
public class FragmentsWithButtons extends Fragment implements View.OnClickListener {

    private Button mBulgariaButton;
    private Button mAmericaButton;
    private Button mFranceButton;

    public interface IButtonClickedElements {
        void onBulgariaButtonClicked(String description);
        void onAmericaButtonClicked(String description);
        void onFranceButtonClicked(String description);
    }

    IButtonClickedElements callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (IButtonClickedElements) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons_fragment_layout, container, false);

        mBulgariaButton = (Button) view.findViewById(R.id.bulgaria_button);
        mAmericaButton = (Button) view.findViewById(R.id.america_button);
        mFranceButton = (Button) view.findViewById(R.id.france_button);

        mBulgariaButton.setOnClickListener(this);
        mAmericaButton.setOnClickListener(this);
        mFranceButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bulgaria_button) {
            callback.onBulgariaButtonClicked("Good!");
        }

        if (view.getId() == R.id.america_button) {
            callback.onAmericaButtonClicked("Bad!");
        }

        if (view.getId() == R.id.france_button) {
            callback.onFranceButtonClicked("Bad!");
        }
    }
}
