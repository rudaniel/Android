package com.example.p5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class fragment_home extends AppCompatActivity {
    TextView editTextPhone2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        Intent i=getIntent();
        editTextPhone2=(TextView)  findViewById(R.id.editTextPhone2);
        String number= i.getExtras().getString("number");
        editTextPhone2.setText(number);

    }

}