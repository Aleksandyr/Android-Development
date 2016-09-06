package com.example.akamenov.a01changebuttontextonclick;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG_FOR_DEBUG  = MainActivity.class.getSimpleName();

    Button onImplicitChangeButtonText;
    Button onExplicitChangeButtonText;


    int mButtonPressCount;
    int mExplicitButtonPressCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onImplicitChangeButtonText = (Button) findViewById(R.id.button);
        onImplicitChangeButtonText.setOnClickListener(this);

        onExplicitChangeButtonText = (Button) findViewById(R.id.button2);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            mButtonPressCount++;
            if(onImplicitChangeButtonText != null){
                onImplicitChangeButtonText.setText(String.valueOf(mButtonPressCount));
            }

        }
    }

    public void onButtonClicked(View view) {
        if(view.getId() == R.id.button2){
            mExplicitButtonPressCount++;

            if(onExplicitChangeButtonText != null) {
                onExplicitChangeButtonText.setText(String.valueOf(mExplicitButtonPressCount));
            }
        }
    }
}
