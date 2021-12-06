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


/**
 * Main start up page opens app.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private StoreOrders orders = new StoreOrders();
    private ArrayList<String> numberList = new ArrayList<String>();
    Order order;

    /**
     * Sets the Theme/Environment.
     */
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

    /**
     * Gets an orders and adds it to store orders.
     */
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

    /**
     * Gets orders.
     */
    public Order getOrder(){
        return order;
    }

    /**
     * Sets orders.
     */
    public void setOrder(Order order){
        this.order=order;
    }

    /**
     * Gets Store orders.
     */
    public StoreOrders getOrders(){
        return orders;
    }

    /**
     * Set Store orders.
     */
    public void setOrders(StoreOrders orders){
        this.orders=orders;
    }

    /**
     * Get list of numbers.
     */
    public ArrayList<String> getNumberList(){
        return numberList;
    }

    /**
     * Set list of numbers.
     */
    public void setNumberList(ArrayList<String> numberList){
        this.numberList=numberList;
    }

}