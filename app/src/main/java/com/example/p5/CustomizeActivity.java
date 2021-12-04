package com.example.p5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import project4.Size;

public class CustomizeActivity extends AppCompatActivity {
    ArrayAdapter<Size> sizes;
    ArrayList<Size> s= new ArrayList<>(Arrays.asList(Size.values()));
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_customize);
        sizes=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , Arrays.asList(Size.values()));

        spinner= (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(sizes);

    }
}