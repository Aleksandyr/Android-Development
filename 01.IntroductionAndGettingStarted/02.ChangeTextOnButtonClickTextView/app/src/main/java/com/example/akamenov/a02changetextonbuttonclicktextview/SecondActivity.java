package com.example.akamenov.a02changetextonbuttonclicktextview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by AKamenov on 9/6/2016.
 */
public class SecondActivity extends Activity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);

        mTextView = (TextView) findViewById(R.id.second_activity_textView);
        Intent getIntent = getIntent();
		
		if(getIntent != null && mTextView != null) {
			mTextView.setText(String.valueOf("First text view id: " +
					getIntent.getIntExtra("firstActivityTextViewId", -1) +
					", and content: " +
					getIntent.getStringExtra("firstActivityTextViewContent")));
		}
    }
}
