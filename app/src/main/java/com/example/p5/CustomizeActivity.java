package com.example.p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import project4.Size;

public class CustomizeActivity extends AppCompatActivity {
    ArrayAdapter<Size> sizes;
    ArrayList<Size> s= new ArrayList<>(Arrays.asList(Size.values()));
    Spinner spinner;
    String pizza;
    ImageView image;
    private static final String deluxe="Deluxe";
    private static final String hawaiian="Hawaiian";
    private static final String pepperoni="Pepperoni";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_customize);
        Intent i=getIntent();
        pizza= i.getExtras().getString("pizza");
        sizes=new ArrayAdapter<>(this, R.layout.spinner_item , Arrays.asList(Size.values()));
        spinner= (Spinner) findViewById(R.id.spinner);
        image= (ImageView) findViewById(R.id.imageView5);
        imageSet();
        spinner.setAdapter(sizes);

    }

    public void imageSet() {
        String uri="";
        if(pizza.equals(deluxe)){
            uri = "@drawable/deluxe";
        }
        else if(pizza.equals(hawaiian)){
            uri = "@drawable/hawaiian";
        }
        else if(pizza.equals(pepperoni)){
            uri = "@drawable/pepperoni";
        }
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        image.setImageDrawable(res);
    }
}