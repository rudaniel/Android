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
    Spinner autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    TextView phoneTextView;
    TextView orderTotal;
    Button deleteButton;
    String currentNumber = null;
    MainActivity activity=null;
    final double zero =0;

    /**
     * Will control all buttons and functions with listeners.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        activity=(MainActivity) getActivity();
        orders= activity.getOrders();
        phoneNumbers=activity.getNumberList();
        autoCompleteTextView = (Spinner) view.findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item , phoneNumbers);
        autoCompleteTextView.setAdapter(adapterItems);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        orderTotal = (TextView) view.findViewById(R.id.orderTotal);
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        phoneTextView.setMovementMethod(new ScrollingMovementMethod());
        orderTotal.setText(String.valueOf(zero));
        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentNumber = autoCompleteTextView.getItemAtPosition(position).toString();
                phoneTextView.setText(orders.getOrder(currentNumber).toString());
                Order temp;
                temp = orders.getOrder(currentNumber);
                ArrayList<Pizza> pizzaTemp;
                pizzaTemp = temp.getPizzas();
                double total = 0;
                for(int i = 0; i<pizzaTemp.size(); i++){
                    total += pizzaTemp.get(i).price();
                }
                orderTotal.setText(String.valueOf(total));
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
     * Deletes the order when a button is clicked.
     */
    public void delete(){
        String message = "Must Select A Phone Number";
        if (currentNumber == null){
            int offset=0;
            Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            adapterItems.remove(currentNumber);
            adapterItems.notifyDataSetChanged();
            autoCompleteTextView.setAdapter(adapterItems);
            autoCompleteTextView.setSelection(-1);
            orders.removeOrder(currentNumber);
            activity.setOrders(orders);
            phoneNumbers.remove(currentNumber);
            activity.setNumberList(phoneNumbers);
            phoneTextView.setText("");
            currentNumber = null;
        }
        orderTotal.setText(String.valueOf(zero));
    }

}