package com.example.p5;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.p5.ui.main.SectionsPagerAdapter2;
import com.example.p5.databinding.ActivityOrderingBinding;

import project4.Order;

public class OrderingActivity extends AppCompatActivity {

    private ActivityOrderingBinding binding;
    TextView editTextPhone2;
    Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        binding = ActivityOrderingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i=getIntent();
        editTextPhone2=(TextView)  findViewById(R.id.editTextPhone2);
        String number= i.getExtras().getString("number");
        order=new Order(number);
        editTextPhone2.setText(number);
        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment frg = null;
                frg = getSupportFragmentManager().findFragmentByTag(sectionsPagerAdapter.getFragment(position));
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            data.getSerializableExtra("Order");
            order = (Order) data.getSerializableExtra("Order");
            System.out.println("ACTIVITY: " + order);
        }
        catch(Exception e){
            System.out.println("ACTIVITY: " + "empty");
        }
    }
    public void setNumber(String number){
        editTextPhone2.setText(number);
    }

    public String getNumber(){
        return editTextPhone2.getText().toString();
    }

    public Order getOrder(){
        return order;
    }
    public void setOrder(Order order){
        this.order=order;
    }
}