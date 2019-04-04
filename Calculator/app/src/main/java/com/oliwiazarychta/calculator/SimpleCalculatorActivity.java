package com.oliwiazarychta.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SimpleCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        if (savedInstanceState != null) {
//            helloText.setText(savedInstanceState.getString("text"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("text", helloText.getText().toString());
    }
}
