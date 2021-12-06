package com.example.p5;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.p5.ui.main.SectionsPagerAdapter;
import com.example.p5.databinding.ActivityMainBinding;

import java.util.ArrayList;

import project4.Order;
import project4.StoreOrders;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private StoreOrders orders = new StoreOrders();
    private ArrayList<String> numberList = new ArrayList<String>();
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setTheme(android.R.style.Theme);
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            data.getSerializableExtra("Order");
            order = (Order) data.getSerializableExtra("Order");
            orders.addOrder(order);
            System.out.println("ADDED: " + order);
        }
        catch(Exception e){
            System.out.println("ORDER NOT ADDED: " + "empty");
        }
    }

    public Order getOrder(){
        return order;
    }

    public void setOrder(Order order){
        this.order=order;
    }

    public StoreOrders getOrders(){
        return orders;
    }

    public void setOrders(StoreOrders orders){
        this.orders=orders;
    }

    public ArrayList<String> getNumberList(){
        return numberList;
    }

    public void setNumberList(ArrayList<String> numberList){
        this.numberList=numberList;
    }

}