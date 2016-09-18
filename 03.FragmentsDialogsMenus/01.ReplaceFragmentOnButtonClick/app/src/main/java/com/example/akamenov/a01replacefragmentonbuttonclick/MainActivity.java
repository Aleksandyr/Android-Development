package com.example.akamenov.a01replacefragmentonbuttonclick;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements FragmentsWithButtons.IButtonClickedElements {


    private FragmentsWithButtons mFragmentsWithButtons;
    private CountryDescriptionFragment mCountryDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentsWithButtons = new FragmentsWithButtons();

        getFragmentManager().beginTransaction().add(R.id.fragmentContainer, mFragmentsWithButtons).commit();
    }

    @Override
    public void onBulgariaButtonClicked(String description) {
        if (mFragmentsWithButtons != null) {

            mCountryDescriptionFragment = new CountryDescriptionFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mCountryDescriptionFragment).commit();
        }
    }

    @Override
    public void onAmericaButtonClicked(String description) {

    }

    @Override
    public void onFranceButtonClicked(String description) {

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mFragmentsWithButtons).commit();
    }
}
