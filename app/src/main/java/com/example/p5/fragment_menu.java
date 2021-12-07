package com.example.p5;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import project4.Order;

/**
 * Current orders based on current phone number.
 * @author Manav Bali
 * @author Daniel Lopez
 */

public class fragment_menu extends Fragment {

    private static final int request=0;
    private static final int result=-1;
    Order order=null;
    Button deluxe;
    Button hawaiian;
    Button pepperoni;
    TextView phoneHolder;
    String phoneNumber=null;
    static final String pizzaKey = "pizza";
    static final String deluxeKey = "Deluxe";
    static final String orderKey = "Order";
    static final String hawaiianKey = "Hawaiian";
    static final String pepperoniKey = "Pepperoni";
    static final String fragKey =  "FRAGMENT: ";
    static final String emptyKey =  "empty";

    /**
     * Default constructor.
     */
    public fragment_menu() {}

    /**
     * Gets the saved instance.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Based on the button asked a page will open with a certain pizza picture.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);
        phoneHolder = (TextView) getActivity().findViewById(R.id.editTextPhone2);
        phoneNumber= phoneHolder.getText().toString();
        OrderingActivity activity=(OrderingActivity) getActivity();
        order= activity.getOrder();
        deluxe= (Button) view.findViewById(R.id.button);
        deluxe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CustomizeActivity.class);
                i.putExtra(pizzaKey,deluxeKey);
                i.putExtra(orderKey,order);
                startActivityForResult(i,request);
            }
        });
        hawaiian= (Button) view.findViewById(R.id.button2);
        hawaiian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CustomizeActivity.class);
                i.putExtra(pizzaKey,hawaiianKey);
                i.putExtra(orderKey,order);
                startActivityForResult(i,request);
            }
        });
        pepperoni= (Button) view.findViewById(R.id.button3);
        pepperoni.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CustomizeActivity.class);
                i.putExtra(pizzaKey,pepperoniKey);
                i.putExtra(orderKey,order);
                startActivityForResult(i,request);
            }
        });
        return view;
    }

    /**
     * Getting any possible orders.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request){
            if(resultCode==result){
                try {
                    data.getSerializableExtra(orderKey);
                order=(Order) data.getSerializableExtra(orderKey);
                System.out.println(fragKey+order);
                }
                catch(Exception e){
                    System.out.println(fragKey + emptyKey);
                }
            }
        }
    }
}