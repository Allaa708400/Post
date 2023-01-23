package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         EditText et_num1 = findViewById(R.id.num1);
        EditText et_num2 = findViewById(R.id.num2);
        TextView tV_resulte = findViewById(R.id.resulte);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum();


            }
        });


    }
    public void   sum(){

        EditText et_num1 = findViewById(R.id.num1);
        EditText et_num2 = findViewById(R.id.num2);
        TextView tV_resulte = findViewById(R.id.resulte);
        Button btn = findViewById(R.id.button);

        String num1 = et_num1.getText().toString();
        String num2 = et_num2.getText().toString();

        int number1 = Integer.parseInt(num1);
        int number2 = Integer.parseInt(num2);

        int resulte = number1 + number2;

        tV_resulte.setText(" Resulte: "+resulte);






    }
}