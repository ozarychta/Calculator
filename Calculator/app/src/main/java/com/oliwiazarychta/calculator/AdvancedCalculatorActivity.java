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

public class AdvancedCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LAST_CALC = "last_calc";
    private static final String CURRENT_CALC = "current_calc";
    private static final String VALUE_ONE = "value_one";
    private static final String VALUE_TWO = "value_two";
    private static final String LAST_TWO_ARG_OPERATION = "last_two_arg_operation";
    private static final String LAST_ONE_ARG_OPERATION = "last_one_arg_operation";
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
    private Button sinBtn;
    private Button cosBtn;
    private Button tanBtn;
    private Button lnBtn;
    private Button logBtn;
    private Button sqrtBtn;
    private Button squareBtn;
    private Button powBtn;
    private Button percentBtn;

    private double firstValue = Double.NaN;
    private double secondValue = Double.NaN;
    private String lastTwoArgumentOperation = "";
    private String lastOneArgumentOperation = "";
    private boolean wasCBtnAlreadyClickedOnce = false;
    private DecimalFormat simpleFormat = new DecimalFormat("#.########");
    private DecimalFormat scientificFormat = new DecimalFormat("#.######E0");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);

        lastCalculationTextView = findViewById(R.id.lastCalculationsTextView);
        currentlyEnteredTextView = findViewById(R.id.currentlyEnteredTextView);

        if (savedInstanceState != null) {
            lastCalculationTextView.setText(savedInstanceState.getString(LAST_CALC));
            currentlyEnteredTextView.setText(savedInstanceState.getString(CURRENT_CALC));
            firstValue = Double.parseDouble(savedInstanceState.getString(VALUE_ONE));
            secondValue = Double.parseDouble(savedInstanceState.getString(VALUE_TWO));
            lastTwoArgumentOperation = savedInstanceState.getString(LAST_TWO_ARG_OPERATION);
            lastOneArgumentOperation = savedInstanceState.getString(LAST_ONE_ARG_OPERATION);
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
        sinBtn = findViewById(R.id.sinBtn);
        cosBtn = findViewById(R.id.cosBtn);
        tanBtn = findViewById(R.id.tanBtn);
        lnBtn = findViewById(R.id.lnBtn);
        logBtn = findViewById(R.id.logBtn);
        sqrtBtn = findViewById(R.id.sqrtBtn);
        squareBtn = findViewById(R.id.squareBtn);
        powBtn = findViewById(R.id.powBtn);
        percentBtn = findViewById(R.id.percentBtn);

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
        sinBtn.setOnClickListener(this);
        cosBtn.setOnClickListener(this);
        tanBtn.setOnClickListener(this);
        lnBtn.setOnClickListener(this);
        logBtn.setOnClickListener(this);
        sqrtBtn.setOnClickListener(this);
        squareBtn.setOnClickListener(this);
        powBtn.setOnClickListener(this);
        percentBtn.setOnClickListener(this);

        simpleFormat.setRoundingMode(RoundingMode.HALF_UP);
        scientificFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        simpleFormat.setDecimalFormatSymbols(symbols);
        scientificFormat.setDecimalFormatSymbols(symbols);
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
                currentlyEnteredTextView.setText("");
                lastOneArgumentOperation = "";

                if (wasCBtnAlreadyClickedOnce) {
                    firstValue = Double.NaN;
                    secondValue = Double.NaN;

                    lastCalculationTextView.setText("");
                    wasCBtnAlreadyClickedOnce = false;
                } else {
                    wasCBtnAlreadyClickedOnce = true;
                }

            } else if ("AC".equals(buttonValue)) {
                firstValue = Double.NaN;
                secondValue = Double.NaN;

                lastOneArgumentOperation = "";
                currentlyEnteredTextView.setText("");
                lastCalculationTextView.setText("");
                lastTwoArgumentOperation = "";

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

    private void calculateOperation(String btnValue) {
        String currentTextInTextView = currentlyEnteredTextView.getText().toString();
        String lastCalculationsText = lastCalculationTextView.getText().toString();

        if(isOneArgumentOperationButton(btnValue)){
            if(isProperNumber(currentTextInTextView)){
                Double calculatedValue = calculateOneArgumentOperation(btnValue, currentTextInTextView);
                if(Double.isNaN(calculatedValue)) return;

                if(lastOneArgumentOperation.isEmpty()){
                    lastOneArgumentOperation = btnValue + "("+currentTextInTextView+")";
                } else {
                    lastOneArgumentOperation = btnValue + "("+lastOneArgumentOperation+")";
                }

                currentlyEnteredTextView.setText(simpleFormat.format(calculatedValue));
            }
            return;
        }

        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(currentTextInTextView);


            if (lastTwoArgumentOperation.equals("+"))
                firstValue += secondValue;
            else if (lastTwoArgumentOperation.equals("-"))
                firstValue -= secondValue;
            else if (lastTwoArgumentOperation.equals("*"))
                firstValue *= secondValue;
            else if (lastTwoArgumentOperation.equals("/")) {
                if (secondValue == 0.0) {
                    Toast.makeText(getApplicationContext(), "Dividing by zero is not allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else firstValue /= secondValue;
            } else if (lastTwoArgumentOperation.equals("x^y")){
                firstValue = Math.pow(firstValue, secondValue);
            }

            if(lastOneArgumentOperation.isEmpty()){
                if("=".equals(lastTwoArgumentOperation)){
                    lastCalculationTextView.setText(simpleFormat.format(secondValue));
                } else {
                    lastCalculationTextView.setText(lastCalculationsText + simpleFormat.format(secondValue));
                }
            } else {
                if("=".equals(lastTwoArgumentOperation)){
                    lastCalculationTextView.setText(lastOneArgumentOperation);
                } else {
                    lastCalculationTextView.setText(lastCalculationsText + lastOneArgumentOperation);
                }
                lastOneArgumentOperation = "";
            }
            currentlyEnteredTextView.setText("");
        } else {
            firstValue = Double.parseDouble(currentTextInTextView);
            if(lastOneArgumentOperation.isEmpty()){
                lastCalculationTextView.setText(simpleFormat.format(firstValue));
            } else {
                lastCalculationTextView.setText(lastOneArgumentOperation);
                lastOneArgumentOperation = "";
            }
        }

        lastCalculationsText = lastCalculationTextView.getText().toString();

        if ("=".equals(btnValue)) {
            currentlyEnteredTextView.setText(simpleFormat.format(firstValue));
            lastCalculationTextView.setText(lastCalculationsText + btnValue + simpleFormat.format(firstValue));
            firstValue = Double.NaN;
            secondValue = Double.NaN;
        } else if("x^y".equals(btnValue)){
            currentlyEnteredTextView.setText("");
            lastCalculationTextView.setText(lastCalculationsText + "^");
        } else {
            currentlyEnteredTextView.setText("");
            lastCalculationTextView.setText(lastCalculationsText + btnValue);
        }

        lastTwoArgumentOperation = btnValue;
    }

    private Double calculateOneArgumentOperation(String operation, String currentlyEnteredText){
        Double enteredValue = Double.parseDouble(currentlyEnteredText);
        Double calculatedValue = Double.NaN;

        if("sin".equals(operation)){
            calculatedValue = Math.sin(enteredValue);
        } else if("cos".equals(operation)){
            calculatedValue = Math.cos(enteredValue);
        } else if("tan".equals(operation)){
            calculatedValue = Math.tan(enteredValue);
        } else if("ln".equals(operation)){
            if (enteredValue == 0.0) {
                Toast.makeText(getApplicationContext(), "Natural logarithm of 0 is infinity!", Toast.LENGTH_SHORT).show();
                return Double.NaN;
            } else if (enteredValue < 0.0){
                Toast.makeText(getApplicationContext(), "Natural logarithm argument must be a positive number!", Toast.LENGTH_SHORT).show();
                return Double.NaN;
            }
            calculatedValue = Math.log(enteredValue);
        } else if("log".equals(operation)){
            if (enteredValue == 0.0) {
                Toast.makeText(getApplicationContext(), "Logarithm of 0 is infinity!", Toast.LENGTH_SHORT).show();
                return Double.NaN;
            } else if (enteredValue < 0.0){
                Toast.makeText(getApplicationContext(), "Logarithm argument must be a positive number!", Toast.LENGTH_SHORT).show();
                return Double.NaN;
            }
            calculatedValue = Math.log10(enteredValue);
        } else if("sqrt".equals(operation)){
            if (enteredValue < 0.0){
                Toast.makeText(getApplicationContext(), "Square root argument must be a positive number!", Toast.LENGTH_SHORT).show();
                return Double.NaN;
            }
            calculatedValue = Math.sqrt(enteredValue);
        } else if("x^2".equals(operation)){
            calculatedValue = Math.pow(enteredValue, 2);
        } else if("%".equals(operation)){
            calculatedValue = enteredValue/100.0;
        }

        return calculatedValue;
    }

    private boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isProperNumber(String currentlyEnteredText) {
        return currentlyEnteredText.matches("(-)?\\d+(\\.\\d+)?(\\.)?$");
    }

    private boolean isOperationButton(String btnValue) {
        return isOneArgumentOperationButton(btnValue)
                || "x^y".equals(btnValue) || "+".equals(btnValue) || "-".equals(btnValue) || "*".equals(btnValue) || "/".equals(btnValue) || "=".equals(btnValue);
    }

    private boolean isOneArgumentOperationButton(String btnValue) {
        return "sin".equals(btnValue) || "cos".equals(btnValue) || "%".equals(btnValue)
                || "tan".equals(btnValue) || "ln".equals(btnValue) || "lg".equals(btnValue)
                || "sqrt".equals(btnValue) || "cos".equals(btnValue) || "x^2".equals(btnValue);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_CALC, lastCalculationTextView.getText().toString());
        outState.putString(CURRENT_CALC, currentlyEnteredTextView.getText().toString());
        outState.putString(VALUE_ONE, String.valueOf(firstValue));
        outState.putString(VALUE_TWO, String.valueOf(secondValue));
        outState.putString(LAST_TWO_ARG_OPERATION, lastTwoArgumentOperation);
        outState.putString(LAST_ONE_ARG_OPERATION, lastOneArgumentOperation);
        outState.putString(WAS_C_CLICKED, String.valueOf(wasCBtnAlreadyClickedOnce));
    }
}
