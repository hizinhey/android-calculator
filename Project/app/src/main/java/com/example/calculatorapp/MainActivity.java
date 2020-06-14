package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String res;
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6,  btn_7, btn_8, btn_9,
            btn_equal, btn_sum, btn_dif, btn_mul, btn_div, btn_dot, btn_ac, btn_delete;
    TextView result, process;

    String unit_a, unit_b, operator;
    boolean isA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectWidgets();
        getButton();
    }

    private void connectWidgets(){
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_equal = findViewById(R.id.btn_equal);
        btn_sum = findViewById(R.id.btn_sum);
        btn_dif = findViewById(R.id.btn_dif);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_dot = findViewById(R.id.btn_dot);
        btn_ac = findViewById(R.id.btn_AC);
        btn_delete = findViewById(R.id.btn_delete);

        result = findViewById(R.id.string_result);
        process = findViewById(R.id.string_process);

        unit_a = "";
        unit_b = "";
        operator = "";
        res = "";

        isA = true;
    }


    private void getButton(){
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(0);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString( 1);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(2);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(3);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(4);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(5);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(6);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(7);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(8);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumberString(9);
            }
        });

        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit_a = "";
                unit_b = "";
                res = "";
                operator = "";
                updateStringTemp();
                updateResule();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!unit_b.equals("")){
                    unit_b = unit_b.substring(0, unit_b.length()-1);
                } else if(!operator.equals("")){
                    operator = "";
                    isA = !isA;
                } else if(!unit_a.equals("")){
                    unit_a = unit_a.substring(0, unit_a.length()-1);
                }
                updateStringTemp();
            }
        });

        btn_dif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "-";
                isA = false;
                updateStringTemp();
            }
        });

        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "+";
                isA = false;
                updateStringTemp();
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "/";
                isA = false;
                updateStringTemp();
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "*";
                isA = false;
                updateStringTemp();
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res = processCalculator();

                isA = true;
                operator = "";
                unit_a = "";
                unit_b = "";

                updateResule();
                updateStringTemp();
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isA){
                    if(unit_a.contains(".")){
                        unit_a = unit_a.replace(".", "");
                        unit_a += ".";
                    } else {
                        unit_a += ".";
                    }
                } else {
                    if(unit_b.contains(".")){
                        unit_b = unit_b.replace(".", "");
                        unit_b += ".";
                    } else {
                        unit_b += ".";
                    }
                }
                updateStringTemp();
            }
        });
    }

    private void addNumberString(int number){
        if(isA){
            if(!(number == 0 && unit_a.equals("0"))){
                if(unit_a.equals("0")) {
                    unit_a = number + "";
                } else {unit_a += number;}
            }
        } else {
            if(!(number == 0 && unit_b.equals("0"))){
                if(unit_b.equals("0")) {
                    unit_b = number + "";
                } else {unit_b += number;}
            }
        }
        updateStringTemp();
    }

    private void updateStringTemp(){
        String temp = (isA)?unit_a:unit_b;
        if(temp.equals("")){
            temp = "0";
        }
        process.setText(temp);
    }

    private String processCalculator(){
        String temp = "";
        float a,b;

        if(unit_b.equals("") || unit_a.equals("") || operator.equals("")){
            return "Error";
        }

        a = Float.parseFloat(unit_a);
        b = Float.parseFloat(unit_b);

        float fresult;

        if(operator.equals("+")){
            fresult = a + b;
            return String.valueOf(Math.round(fresult*10000000)/10000000.0);
        }

        if(operator.equals("-")){
            fresult = a - b;
            return String.valueOf(Math.round(fresult*10000000)/10000000.0);
        }

        if(operator.equals("*")){
            fresult = a*b;
            return String.valueOf(Math.round(fresult*10000000)/10000000.0);
        }

        if(operator.equals("/")){
            if(b == 0) return "Error";
            fresult = a/b;
            return String.valueOf(Math.round(fresult*10000000)/10000000.0);
        }
        return "Error";
    }

    private void updateResule(){
        result.setText(res);
    }

    private void switchUnit(){
        isA = !isA;
    }

    private void convert(String def, char res){

    }
}
