package com.example.p5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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

    ArrayList<String> pizzas =  new ArrayList<String>();
    ArrayAdapter<String> adapterPizzas;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current, container, false);
        phoneHolder = (TextView) getActivity().findViewById(R.id.editTextPhone2);
        phoneNumber= phoneHolder.getText().toString();

        pizzaList = (ListView) view.findViewById(R.id.pizzaList);

        pizzas.add(phoneNumber); //Instead of adding phoneNumber we will add all of the pizzas to the list.
        pizzas.add("9086342536");
        pizzas.add("1010203040");



        adapterPizzas = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1 , pizzas);
        pizzaList.setAdapter(adapterPizzas);

        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  view.getFocusables(position);
              //  view.setSelected(true);



            }
        });
        
        getPizzas();
        return view;
    }

    public void getPizzas() {
        // get phone number using this.phoneNumber;
    }
}