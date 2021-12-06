package com.example.p5;

import android.annotation.SuppressLint;
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


public class fragment_current extends Fragment {
    TextView phoneHolder;
    TextView phoneTemp;
    String phoneNumber;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
   //private String mParam2;

    public fragment_current() { }


//    public static fragment_current newInstance(String param1, String param2) {
//        fragment_current fragment = new fragment_current();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
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
        calculateTotals();
        return view;
    }

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

    private void removePizza() {
        int offset=0;
        if (currentPizza == null){
            String message = "Must Select A Pizza";
            Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
            View t= toast.getView();
            toast.setGravity(Gravity.CENTER,offset,offset);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setTextColor(getResources().getColor(R.color.maroon_200));
            toastMessage.setBackgroundColor(getResources().getColor(R.color.clear));
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

    private void error() {
        int offset=0;
        String message="Order is Currently Empty, Add Pizzas!";
        Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
        View t= toast.getView();
        toast.setGravity(Gravity.CENTER,offset,offset);
        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(getResources().getColor(R.color.maroon_200));
        toastMessage.setBackgroundColor(getResources().getColor(R.color.clear));
        toast.show();
    }

}