package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Areas extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);

        Spinner sp_shapes = findViewById(R.id.areas_sp_shapes);
        EditText et_rectangle_width = findViewById(R.id.areas_et_rectangle_width);
        EditText et_rectangle_hieght = findViewById(R.id.areas_et_rectangle_hieght);
        EditText et_circle_radius = findViewById(R.id.areas_et_circle_radius);
        EditText et_triangle_base = findViewById(R.id.areas_et_triangle_base);
        EditText et_triangle_hieght = findViewById(R.id.areas_et_triangle_hieght);
        TextView tv_result = findViewById(R.id.areas_tv_result);
        Button btn_calculate = findViewById(R.id.areas_btn_calculate);

        // circle = pi * r * r
        // rectangle = w * h
        // triangle = 0.5 * base * h

        sp_shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        // Select shapes
                        et_rectangle_hieght.setVisibility(View.GONE);
                        et_rectangle_width.setVisibility(View.GONE);
                        et_circle_radius.setVisibility(View.GONE);
                        et_triangle_base.setVisibility(View.GONE);
                        et_triangle_hieght.setVisibility(View.GONE);
                        break;

                    case 1:
                        // Rectangle
                        et_rectangle_hieght.setVisibility(View.VISIBLE);
                        et_rectangle_width.setVisibility(View.VISIBLE);
                        et_circle_radius.setVisibility(View.GONE);
                        et_triangle_base.setVisibility(View.GONE);
                        et_triangle_hieght.setVisibility(View.GONE);
                        break;

                    case 2:
                        // Circle
                        et_rectangle_hieght.setVisibility(View.GONE);
                        et_rectangle_width.setVisibility(View.GONE);
                        et_circle_radius.setVisibility(View.VISIBLE);
                        et_triangle_base.setVisibility(View.GONE);
                        et_triangle_hieght.setVisibility(View.GONE);
                        break;

                    case 3:
                        // Triangle
                        et_rectangle_hieght.setVisibility(View.GONE);
                        et_rectangle_width.setVisibility(View.GONE);
                        et_circle_radius.setVisibility(View.GONE);
                        et_triangle_base.setVisibility(View.VISIBLE);
                        et_triangle_hieght.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = sp_shapes.getSelectedItemPosition();

                double area = 0;
                switch (index){

                    // Rectangle
                    case 1:
                       // double rect_width = Double.parseDouble(et_rectangle_width.getText().toString());

                        String Width = et_rectangle_width.getText().toString();
                        double rect_width = Double.parseDouble(Width);

                      // double rect_hieght = Double.parseDouble(et_rectangle_hieght.getText().toString());

                        String hieght = et_rectangle_hieght.getText().toString();
                        double rect_hieght = Double.parseDouble(Width);

                        area = rect_width * rect_hieght;
                        break;

                    // Circle
                    case 2:
                        // double cir_radius = Double.parseDouble(et_circle_radius.getText().toString());

                        String radius = et_circle_radius.getText().toString();
                        double cir_radius = Double.parseDouble(radius);

                        area = Math.PI * Math.pow(cir_radius,2);
                        break;

                    // Triangle
                    case 3:
                        // double cir_radius = Double.parseDouble(et_circle_radius.getText().toString());

                        String base = et_triangle_base.getText().toString();
                        double tri_base = Double.parseDouble(base);

                        // double tri_hieght = Double.parseDouble(et_triangle_hieght.getText().toString());
                        String hieght2 = et_triangle_hieght.getText().toString();
                        double tri_hieght = Double.parseDouble(hieght2);

                        area = .5 * tri_base * tri_hieght;
                        break;
                }

                tv_result.setText(area+"");
            }
        });
    }
}