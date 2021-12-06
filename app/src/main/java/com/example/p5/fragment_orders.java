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

import project4.StoreOrders;


public class fragment_orders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public fragment_orders() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
//    public static fragment_orders newInstance(String param1, String param2) {
//        fragment_orders fragment = new fragment_orders();
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

    ArrayList <String> phoneNumbers =  new ArrayList<String>();
    StoreOrders orders;
    Spinner autoTextView;
    ArrayAdapter<String> adapterItems;
    TextView phoneTextView;
    Button deleteButton;
    String currentNumber = null;
    MainActivity activity=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        activity=(MainActivity) getActivity();
        orders= activity.getOrders();
        phoneNumbers=activity.getNumberList();
        autoTextView = (Spinner) view.findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item , phoneNumbers);
        autoTextView.setAdapter(adapterItems);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        deleteButton = (Button) view.findViewById(R.id.deleteButton);
        phoneTextView.setMovementMethod(new ScrollingMovementMethod());
        autoTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentNumber = autoTextView.getItemAtPosition(position).toString();
                phoneTextView.setText(orders.getOrder(currentNumber).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        deleteButton .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String message = "Must Select A Phone Number";
                if (currentNumber == null){
                    int offset=0;
                    Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
                    View t= toast.getView();
                    toast.setGravity(Gravity.CENTER,offset,offset);
                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(getResources().getColor(R.color.maroon_200));
                    toastMessage.setBackgroundColor(getResources().getColor(R.color.clear));
                    toast.show();
                }
                else {
                    adapterItems.remove(currentNumber);
                    adapterItems.notifyDataSetChanged();
                    autoTextView.setAdapter(adapterItems);
                    autoTextView.setSelection(-1);
                    orders.removeOrder(currentNumber);
                    activity.setOrders(orders);
                    phoneNumbers.remove(currentNumber);
                    activity.setNumberList(phoneNumbers);
                    phoneTextView.setText("");
                    currentNumber = null;
                }
            }
        });

        return view;
    }
}