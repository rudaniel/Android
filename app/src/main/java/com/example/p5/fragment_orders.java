package com.example.p5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
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

import project4.StoreOrders;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_orders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_orders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_orders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_orders.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_orders newInstance(String param1, String param2) {
        fragment_orders fragment = new fragment_orders();
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



  //  ArrayList phoneNumbers = {"9084561296", "9084945104"};

    ArrayList <String> phoneNumbers =  new ArrayList<String>();


    Spinner autoTextView;
    ArrayAdapter<String> adapterItems;
    TextView phoneTextView;
    Button deleteButton;
    String currentNumber = "-1";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        autoTextView = (Spinner) view.findViewById(R.id.autoCompleteTextView);

        phoneNumbers.add("9084563456");
        phoneNumbers.add("0987654321");

        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item , phoneNumbers);

        autoTextView.setAdapter(adapterItems);

        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);

        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        phoneTextView.setMovementMethod(new ScrollingMovementMethod());
        autoTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                currentNumber = autoTextView.getItemAtPosition(position).toString();
                phoneTextView.setText(currentNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        deleteButton .setOnClickListener(new View.OnClickListener() { //When the button is clicked the method will run.
            public void onClick(View v){
                String message = "Must Select A Phone Number";

                if (currentNumber == "-1"){
                //    System.out.println(currentNumber);
                    Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
                    toast.show();
                }
               // System.out.println(currentNumber);
                adapterItems.remove(currentNumber);
                adapterItems.notifyDataSetChanged();
                autoTextView.setAdapter(adapterItems);

             //  autoTextView.setText("");

                autoTextView.setSelection(-1);


                phoneTextView.setText("");



            }
        });

        return view;
    }
}