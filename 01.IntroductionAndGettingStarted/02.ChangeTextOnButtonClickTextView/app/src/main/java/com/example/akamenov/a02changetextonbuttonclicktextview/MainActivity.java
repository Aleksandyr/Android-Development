package com.example.akamenov.a02changetextonbuttonclicktextview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {

    Button mFirstButton;
    Button mSecondButton;
    Button mThirdButton;

    TextView mTextView;

    int mFirstButtonCounter;
    int mSecondButtonCounter;
    int mThirdButtonCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstButton = (Button) findViewById(R.id.firstButton);
        mFirstButton.setOnClickListener(this);

        mSecondButton = (Button) findViewById(R.id.secondButton);
        mSecondButton.setOnClickListener(this);

        mThirdButton = (Button) findViewById(R.id.thridButton);
        mThirdButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.firstButton) {
            mFirstButtonCounter++;
            setButtonAndTextViewContent(mFirstButton, mTextView, mFirstButtonCounter);

            mSecondButton.setText(String.valueOf(getResources().getString(R.string.button2_text)));
            mThirdButton.setText(String.valueOf(getResources().getString(R.string.button3_text)));

            mSecondButtonCounter = 0;
            mThirdButtonCounter = 0;
        } else if (view.getId() == R.id.secondButton) {
            mSecondButtonCounter++;
            setButtonAndTextViewContent(mSecondButton, mTextView, mSecondButtonCounter);

            mFirstButton.setText(getResources().getString(R.string.button1_text));
            mThirdButton.setText(getResources().getString(R.string.button3_text));

            mFirstButtonCounter = 0;
            mThirdButtonCounter = 0;
        } else if (view.getId() == R.id.thridButton) {
            mThirdButtonCounter++;
            setButtonAndTextViewContent(mThirdButton, mTextView, mThirdButtonCounter);

            mFirstButton.setText(getResources().getString(R.string.button1_text));
            mSecondButton.setText(getResources().getString(R.string.button2_text));

            mFirstButtonCounter = 0;
            mSecondButtonCounter = 0;
        }

        if (mFirstButtonCounter > 0 || mSecondButtonCounter > 0 || mThirdButtonCounter > 0) {
            if (view.getId() == R.id.textView) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("firstActivityTextViewId", mTextView.getId());
                intent.putExtra("firstActivityTextViewContent", mTextView.getText());

                startActivity(intent);
            }
        }
    }

    private void setButtonAndTextViewContent(Button button, TextView textView, int counter) {
        if (counter >= 2) {
            button.setText(getResources().getString(R.string.second_click));
        } else {
            textView.setText(String.valueOf(button.getId()));
        }
    }
}
