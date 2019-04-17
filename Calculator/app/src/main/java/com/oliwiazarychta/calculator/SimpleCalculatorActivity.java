package com.oliwiazarychta.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SimpleCalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LAST_CALC = "last_calc";
    private static final String CURRENT_CALC = "current_calc";
    private static final String VALUE_ONE = "value_one";
    private static final String VALUE_TWO = "value_two";
    private static final String LAST_OPERATION = "last_operation";
    private static final String WAS_C_CLICKED = "was_c_clicked";

    private TextView lastCalculationTextView;
    private TextView currentlyEnteredTextView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button acBtn;
    private Button cBtn;
    private Button addBtn;
    private Button subtractBtn;
    private Button multiplyBtn;
    private Button divideBtn;
    private Button equalsBtn;
    private Button pointBtn;
    private Button plusMinusBtn;

    private double firstValue = Double.NaN;
    private double secondValue = Double.NaN;
    private String lastOperation = "";
    private boolean wasCBtnAlreadyClickedOnce = false;
    private DecimalFormat simpleFormat = new DecimalFormat("#.########");
    private DecimalFormat scientificFormat = new DecimalFormat("#.######E0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        lastCalculationTextView = findViewById(R.id.lastCalculationsTextView);
        currentlyEnteredTextView = findViewById(R.id.currentlyEnteredTextView);

        if (savedInstanceState != null) {
            lastCalculationTextView.setText(savedInstanceState.getString(LAST_CALC));
            currentlyEnteredTextView.setText(savedInstanceState.getString(CURRENT_CALC));
            firstValue = Double.parseDouble(savedInstanceState.getString(VALUE_ONE));
            secondValue = Double.parseDouble(savedInstanceState.getString(VALUE_TWO));
            lastOperation = savedInstanceState.getString(LAST_OPERATION);
            wasCBtnAlreadyClickedOnce = Boolean.parseBoolean(savedInstanceState.getString(WAS_C_CLICKED));

        }

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        acBtn = findViewById(R.id.acBtn);
        cBtn = findViewById(R.id.cBtn);
        addBtn = findViewById(R.id.addBtn);
        subtractBtn = findViewById(R.id.subtractBtn);
        multiplyBtn = findViewById(R.id.multiplyBtn);
        divideBtn = findViewById(R.id.divideBtn);
        equalsBtn = findViewById(R.id.equalsBtn);
        pointBtn = findViewById(R.id.pointBtn);
        plusMinusBtn = findViewById(R.id.plusMinusBtn);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        acBtn.setOnClickListener(this);
        cBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        subtractBtn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
        divideBtn.setOnClickListener(this);
        equalsBtn.setOnClickListener(this);
        pointBtn.setOnClickListener(this);
        plusMinusBtn.setOnClickListener(this);

        simpleFormat.setRoundingMode(RoundingMode.HALF_UP);
        scientificFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        simpleFormat.setDecimalFormatSymbols(symbols);
        scientificFormat.setDecimalFormatSymbols(symbols);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_CALC, lastCalculationTextView.getText().toString());
        outState.putString(CURRENT_CALC, currentlyEnteredTextView.getText().toString());
        outState.putString(VALUE_ONE, String.valueOf(firstValue));
        outState.putString(VALUE_TWO, String.valueOf(secondValue));
        outState.putString(LAST_OPERATION, lastOperation);
        outState.putString(WAS_C_CLICKED, String.valueOf(wasCBtnAlreadyClickedOnce));
    }


    @Override
    public void onClick(View v) {

        if (v instanceof Button) {
            String buttonValue = ((Button) v).getText().toString();

            String currentTextInTextView = currentlyEnteredTextView.getText().toString();

            if (!"C".equals(buttonValue)) {
                wasCBtnAlreadyClickedOnce = false;
            }

            if (isInt(buttonValue) || (".".equals(buttonValue) && !currentTextInTextView.contains("."))) {
                currentTextInTextView += buttonValue;
                currentlyEnteredTextView.setText(currentTextInTextView);

            } else if (isOperationButton(buttonValue)) {

                if (currentTextInTextView.isEmpty() || !isProperNumber(currentTextInTextView)) {
                    Toast.makeText(getApplicationContext(), "You have to enter a proper number now.", Toast.LENGTH_SHORT).show();
                    return;
                }

                calculateOperation(buttonValue);

            } else if ("C".equals(buttonValue)) {
                if (wasCBtnAlreadyClickedOnce) {
                    firstValue = Double.NaN;
                    secondValue = Double.NaN;

                    currentlyEnteredTextView.setText("");
                    lastCalculationTextView.setText("");
                    wasCBtnAlreadyClickedOnce = false;
                } else {
                    currentlyEnteredTextView.setText("");
                    wasCBtnAlreadyClickedOnce = true;
                }

            } else if ("AC".equals(buttonValue)) {
                firstValue = Double.NaN;
                secondValue = Double.NaN;

                currentlyEnteredTextView.setText("");
                lastCalculationTextView.setText("");
                lastOperation = "";

            } else if ("+/-".equals(buttonValue)) {
                if (isProperNumber(currentTextInTextView)) {
                    if (currentTextInTextView.startsWith("-")) {
                        currentTextInTextView = currentTextInTextView.substring(1);
                    } else {
                        currentTextInTextView = "-" + currentTextInTextView;
                    }
                    currentlyEnteredTextView.setText(currentTextInTextView);
                } else {
                    Toast.makeText(getApplicationContext(), "You have to enter a number first", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }

    private boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isOperationButton(String btnValue) {
        return "+".equals(btnValue) || "-".equals(btnValue) || "*".equals(btnValue) || "/".equals(btnValue) || "=".equals(btnValue);
    }

    private boolean isProperNumber(String currentlyEnteredText) {
        return currentlyEnteredText.matches("(-)?\\d+(\\.\\d+)?(\\.)?$");
    }

    private void calculateOperation(String btnValue){
        String currentTextInTextView = currentlyEnteredTextView.getText().toString();

        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(currentTextInTextView);
            String lastCalculationsText = lastCalculationTextView.getText().toString();

            if (lastOperation.equals("+"))
                firstValue += secondValue;
            else if (lastOperation.equals("-"))
                firstValue -= secondValue;
            else if (lastOperation.equals("*"))
                firstValue *= secondValue;
            else if (lastOperation.equals("/")) {
                if (secondValue == 0.0) {
                    Toast.makeText(getApplicationContext(), "Dividing by zero is not allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else firstValue /= secondValue;
            }

            if("=".equals(lastOperation)){
                lastCalculationTextView.setText(simpleFormat.format(secondValue));
            } else {
                lastCalculationTextView.setText(lastCalculationsText + simpleFormat.format(secondValue));
            }
            currentlyEnteredTextView.setText("");
        } else {
            firstValue = Double.parseDouble(currentTextInTextView);
            lastCalculationTextView.setText(simpleFormat.format(firstValue));
        }

        String lastCalculationsText = lastCalculationTextView.getText().toString();

        if ("=".equals(btnValue)) {
            currentlyEnteredTextView.setText(simpleFormat.format(firstValue));
            lastCalculationTextView.setText(lastCalculationsText + btnValue + simpleFormat.format(firstValue));
            firstValue = Double.NaN;
            secondValue = Double.NaN;
        } else {
            currentlyEnteredTextView.setText("");
            lastCalculationTextView.setText(lastCalculationsText + btnValue);
        }

        lastOperation = btnValue;
    }
}