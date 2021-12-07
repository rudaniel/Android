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
    Order order;
    static final String orderKey = "Order";

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
    }

    /**
     * Gets an orders and adds it to store orders.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            data.getSerializableExtra(orderKey);
            order = (Order) data.getSerializableExtra(orderKey);
            orders.addOrder(order);
        }
        catch(Exception e){

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
}