package com.example.akamenov.a02changetextonbuttonclicktextview;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
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

    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstButton = (Button) findViewById(R.id.firstButton);
        mSecondButton = (Button) findViewById(R.id.secondButton);
        mThirdButton = (Button) findViewById(R.id.thridButton);
        mTextView = (TextView) findViewById(R.id.textView);

        mFirstButton.setOnClickListener(this);
        mSecondButton.setOnClickListener(this);
        mThirdButton.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.firstButton) {
            onClickAndOnDoubleClickTextChange(mFirstButton,
                    mTextView,
                    getResources().getString(R.string.button1_text),
                    mLastClickTime);

            mLastClickTime = SystemClock.elapsedRealtime();

        } else if (view.getId() == R.id.secondButton) {
            onClickAndOnDoubleClickTextChange(mSecondButton,
                    mTextView,
                    getResources().getString(R.string.button2_text),
                    mLastClickTime);

            mLastClickTime = SystemClock.elapsedRealtime();

        } else if (view.getId() == R.id.thridButton) {
            onClickAndOnDoubleClickTextChange(mThirdButton,
                    mTextView,
                    getResources().getString(R.string.button3_text),
                    mLastClickTime);

            mLastClickTime = SystemClock.elapsedRealtime();
        }

        if (mLastClickTime > 0) {
            if (view.getId() == R.id.textView) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("firstActivityTextViewId", mTextView.getId());
                intent.putExtra("firstActivityTextViewContent", mTextView.getText());

                startActivity(intent);
            }
        }
    }

    private void onClickAndOnDoubleClickTextChange(Button button, TextView textView, String text, long lastClick) {
        if (SystemClock.elapsedRealtime() - lastClick < 200) {
            button.setText(getResources().getString(R.string.twice_click));
        } else {
            textView.setText(String.valueOf(button.getId()));
            button.setText(String.valueOf(text));
        }
    }
}
