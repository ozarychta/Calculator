package com.oliwiazarychta.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button simpleCalculatorBtn;
    private Button advancedCalculatorBtn;
    private Button aboutBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleCalculatorBtn = findViewById(R.id.simpleCalculatorBtn);
        advancedCalculatorBtn = findViewById(R.id.advancedCalculatorBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        exitBtn = findViewById(R.id.exitBtn);

        if (savedInstanceState != null) {
//            helloText.setText(savedInstanceState.getString("text"));
        }

        simpleCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v,SimpleCalculatorActivity.class);
            }
        });

        advancedCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v, AdvancedCalculatorActivity.class);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v, AboutActivity.class);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
                System.exit(0);
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                helloText.setText("changed!!!!");
//            }
//        });

//        helloText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("text", helloText.getText().toString());
    }

    public void launchActivity(View view, Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
