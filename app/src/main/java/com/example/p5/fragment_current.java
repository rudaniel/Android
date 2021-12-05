package com.example.p5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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
import project4.Pepperoni;
import project4.Pizza;
import project4.PizzaMaker;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_current#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_current extends Fragment {
    TextView phoneHolder;
    TextView phoneTemp;
    String phoneNumber;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_current() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_current.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_current newInstance(String param1, String param2) {
        fragment_current fragment = new fragment_current();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    ListView pizzaList;

    ArrayList<Pizza> pizzas =  new ArrayList<Pizza>();
   // ArrayList<String> pizzasString =  new ArrayList<String>();
    ArrayAdapter<Pizza> adapterPizzas;
    Pizza currentPizza=null;
    Button placeOrderButton;
    Button removePizzaButton;
    TextView subTotalView;
    TextView salesTaxView;
    TextView orderTotalView;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current, container, false);
        phoneHolder = (TextView) getActivity().findViewById(R.id.editTextPhone2);
        phoneNumber= phoneHolder.getText().toString();
        pizzaList = (ListView) view.findViewById(R.id.pizzaList);


        Pizza deluxePizza = new Deluxe();
        Pizza hawaiianPizza = new Hawaiian();
        Pizza pepperoniPizza = new Pepperoni();


        pizzas.add(deluxePizza); //Instead of adding phoneNumber we will add all of the pizzas to the list.
        pizzas.add(hawaiianPizza);
        pizzas.add(pepperoniPizza);

//        String parse0 = pizzas.get(0).toString();
//        String parse1 = pizzas.get(1).toString();
//        String parse2 = pizzas.get(2).toString();
//
//        pizzasString.add(parse0);
//        pizzasString.add(parse1);
//        pizzasString.add(parse2);

        subTotalView = (TextView) view.findViewById(R.id.subTotalView);
        salesTaxView = (TextView) view.findViewById(R.id.salesTaxView);
        orderTotalView = (TextView) view.findViewById(R.id.orderTotalView);
        double pizzaSubTotal = 0;
        double taxAmount = 1.06625;

        adapterPizzas = new ArrayAdapter<>(getActivity(), R.layout.spinner_item , pizzas);
        pizzaList.setAdapter(adapterPizzas);
        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPizza = (Pizza) pizzaList.getItemAtPosition(position);
               // System.out.println(currentNumber);
            }
        });
        placeOrderButton = (Button) view.findViewById(R.id.placeOrderButton);
        removePizzaButton = (Button) view.findViewById(R.id.removePizzaButton);
        removePizzaButton.setOnClickListener(new View.OnClickListener() { //When the button is clicked the method will run.
            public void onClick(View v){
                String message = "Must Select A Phone Number";
                if (currentPizza == null){
                    Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
                    toast.show();
                }
                double newSubTotal = 0;
                for (int i = 0; i<pizzas.size(); i++){
                    if(pizzas.get(i).equals(currentPizza)){
                        pizzas.remove(i);
                        for (int j = 0; j<pizzas.size(); j++){
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
                adapterPizzas.remove(currentPizza); //Instead of removing phone number we would remove pizza.
                adapterPizzas.notifyDataSetChanged();
                pizzaList.setAdapter(adapterPizzas);
                pizzaList.setSelection(-1);
            }
        });



       // ArrayList<Pizza> pizzas

        for (int i = 0; i<pizzas.size(); i++){
            double temp = pizzas.get(i).price();
            pizzaSubTotal = temp + pizzaSubTotal;

        }
      // double pizzaSubTotal = deluxePizza.price();
      // String amount = Double.toString(pizzaSubTotal);
       Double tax = (pizzaSubTotal * taxAmount) - pizzaSubTotal;
       Double orderTotal = (pizzaSubTotal * taxAmount);


        subTotalView.setText(df.format(pizzaSubTotal));
        salesTaxView.setText(df.format(tax));
        orderTotalView.setText(df.format(orderTotal));
        //  getPizzas();
        return view;
    }

    public void getPizzas() {
        // get phone number using this.phoneNumber;
    }
}