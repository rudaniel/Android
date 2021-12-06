package com.example.p5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import project4.Deluxe;
import project4.Hawaiian;
import project4.Order;
import project4.Pepperoni;
import project4.Pizza;
import project4.PizzaMaker;

/**
 * Current orders based on current phone number.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class fragment_current extends Fragment {
    TextView phoneHolder;
    TextView phoneTemp;
    String phoneNumber;


    /**
     * Default constructor.
     */
    public fragment_current() { }



    /**
     * Gets on saved instances.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ListView pizzaList;
    ArrayList<Pizza> pizzas =  new ArrayList<Pizza>();
    ArrayAdapter<Pizza> adapterPizzas;
    Pizza currentPizza=null;
    Button placeOrderButton;
    Button removePizzaButton;
    TextView subTotalView;
    TextView salesTaxView;
    TextView orderTotalView;
    double pizzaSubTotal = 0;
    OrderingActivity activity;
    private static final  double taxAmount = 1.06625;
    Order order=null;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Checks to make sure an order was first made.
     * Handles functionality of the overall page.
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_current, container, false);
        fillObjects(view);
        activity=(OrderingActivity) getActivity();
        order= activity.getOrder();
        try{
            pizzas = order.getPizzas();
            if(pizzas.isEmpty())
                error();
        }
        catch(Exception e) {
            error();
        }
        adapterPizzas = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, pizzas);
        pizzaList.setAdapter(adapterPizzas);
        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPizza = (Pizza) pizzaList.getItemAtPosition(position);
            }
        });

        removePizzaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                removePizza();
                currentPizza=null;
            }
        });

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                placeOrder();
            }
        });
        calculateTotals();
        return view;
    }

    /**
     * Gets the pizza and adds it to a store order.
     */
    private void placeOrder() {
        try{
            pizzas = order.getPizzas();
            if(pizzas.isEmpty()) {
                errorOrder();
            }
            else{
                Intent send= new Intent();
                send.putExtra("Order", order);
                getActivity().setResult(Activity.RESULT_OK, send);
                getActivity().finish();
            }
        }
        catch(Exception e) {
            errorOrder();
        }
    }

    /**
     * Error message.
     */
    private void errorOrder() {
        String message = "Order is Incomplete, Finish Order or Press Cancel Order.";
        Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Creates the objects and tags them to the xml.
     */
    private void fillObjects(View view) {
        phoneHolder = (TextView) getActivity().findViewById(R.id.editTextPhone2);
        phoneNumber= phoneHolder.getText().toString();
        pizzaList = (ListView) view.findViewById(R.id.pizzaList);
        subTotalView = (TextView) view.findViewById(R.id.subTotalView);
        salesTaxView = (TextView) view.findViewById(R.id.salesTaxView);
        orderTotalView = (TextView) view.findViewById(R.id.orderTotalView);
        placeOrderButton = (Button) view.findViewById(R.id.placeOrderButton);
        removePizzaButton = (Button) view.findViewById(R.id.removePizzaButton);
    }

    /**
     * Sets the price based on the pizzas.
     */
    private void calculateTotals() {
        pizzaSubTotal=0;
        for (int i = 0; i<pizzas.size(); i++){
            double temp = pizzas.get(i).price();
            pizzaSubTotal = temp + pizzaSubTotal;
        }
        Double tax = (pizzaSubTotal * taxAmount) - pizzaSubTotal;
        Double orderTotal = (pizzaSubTotal * taxAmount);
        subTotalView.setText(df.format(pizzaSubTotal));
        salesTaxView.setText(df.format(tax));
        orderTotalView.setText(df.format(orderTotal));
    }

    /**
     * Removes the pizza from the order and updates the pricing.
     */
    private void removePizza() {
        int offset=0;
        if (currentPizza == null){
            String message = "Must Select A Pizza";
            Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            double newSubTotal = 0;
            for (int i = 0; i < pizzas.size(); i++) {
                if (pizzas.get(i).equals(currentPizza)) {
                    pizzas.remove(i);
                    for (int j = 0; j < pizzas.size(); j++) {
                        double temp = pizzas.get(j).price();
                        newSubTotal = temp + newSubTotal;
                    }
                    Double tax = (newSubTotal * taxAmount) - newSubTotal;
                    Double orderTotal = (newSubTotal * taxAmount);
                    subTotalView.setText(df.format(newSubTotal));
                    salesTaxView.setText(df.format(tax));
                    orderTotalView.setText(df.format(orderTotal));
                }
            }
            adapterPizzas.remove(currentPizza);
            adapterPizzas.notifyDataSetChanged();
            pizzaList.setAdapter(adapterPizzas);
            pizzaList.setSelection(-1);
            activity.setOrder(order);
        }
    }

    /**
     * Displays the errors messages.
     */

    private void error() {
        int offset=0;
        String message="Order is Currently Empty, Add Pizzas!";
        Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
        toast.show();
    }

}