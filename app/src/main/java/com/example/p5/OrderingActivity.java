package com.example.p5;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.p5.ui.main.SectionsPagerAdapter2;
import com.example.p5.databinding.ActivityOrderingBinding;

public class OrderingActivity extends AppCompatActivity {

    private ActivityOrderingBinding binding;
    TextView editTextPhone2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        binding = ActivityOrderingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i=getIntent();
        editTextPhone2=(TextView)  findViewById(R.id.editTextPhone2);
        String number= i.getExtras().getString("number");
        editTextPhone2.setText(number);
        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setNumber(String number){
        editTextPhone2.setText(number);
    }

    public String getNumber(){
        return editTextPhone2.getText().toString();
    }
}