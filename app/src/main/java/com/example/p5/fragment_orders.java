package com.example.p5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import project4.Order;
import project4.Pizza;
import project4.StoreOrders;

/**
 * Controls the fragment order page.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class fragment_orders extends Fragment {

    /**
     * Default constructor.
     */
    public fragment_orders() {
        // Required empty public constructor
    }

    /**
     * Get the current instance.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ArrayList <String> phoneNumbers =  new ArrayList<String>();
    StoreOrders orders;
    private static final  double taxAmount = 1.06625;
    ArrayList<Order> orderList=new ArrayList<Order>();
    Spinner autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    TextView phoneTextView;
    TextView orderTotal;
    Button deleteButton;
    int none=-1;
    int pos = -1;
    MainActivity activity=null;
    final double zero =0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Will control all buttons and functions with listeners.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        linkValues(view);
        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pos=position;
                ArrayList<Pizza> pizzaTemp;
                pizzaTemp = orderList.get(position).getPizzas();
                phoneTextView.setText(pizzaTemp.toString());
                double total = 0;
                for(int i = 0; i < pizzaTemp.size(); i++){
                    total += pizzaTemp.get(i).price();
                }
                total = total * taxAmount;
                orderTotal.setText(df.format(total));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        deleteButton .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                delete();
            }
        });
        return view;
    }

    /**
     * Links GUI elements to java file
     */
    private void linkValues(View view) {
        pos=0;
        activity=(MainActivity) getActivity();
        orders= activity.getOrders();
        orderList=orders.getOrders();
        phoneNumbers=new ArrayList<String>();
        for(int i = 0; i < orderList.size();i++){
            phoneNumbers.add(orderList.get(i).getPhone());
        }
        autoCompleteTextView = (Spinner) view.findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item , phoneNumbers);
        autoCompleteTextView.setAdapter(adapterItems);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        orderTotal = (TextView) view.findViewById(R.id.orderTotal);
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        phoneTextView.setMovementMethod(new ScrollingMovementMethod());
        orderTotal.setText(String.valueOf(zero));
    }

    /**
     * Deletes the order when a button is clicked.
     */
    public void delete(){
        String message = "Must Select A Phone Number";
        if (pos == none){
            Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            phoneNumbers.remove(pos);
            adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, phoneNumbers);
            adapterItems.notifyDataSetChanged();
            autoCompleteTextView.setAdapter(adapterItems);
            autoCompleteTextView.setSelection(none);
            orderList.remove(pos);
            activity.setOrders(orders);
            String blank = "";
            phoneTextView.setText(blank);
            pos = -1;
            autoCompleteTextView.setSelection(none);
            orders.setOrders(orderList);
            activity.setOrders(orders);
            orderTotal.setText(String.valueOf(zero));
        }
    }

}