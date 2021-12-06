package com.example.p5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import project4.Order;
//Manav's Comment
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private static final int request=0;
    private static final int result=-1;
    Order order=null;

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    Button deluxe;
    Button hawaiian;
    Button pepperoni;

    TextView phoneHolder;
    String phoneNumber=null;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_menu newInstance(String param1, String param2) {
        fragment_menu fragment = new fragment_menu();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragment_menu() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

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
                i.putExtra("pizza","Deluxe");
                i.putExtra("Order",order);
                startActivityForResult(i,request);
            }
        });

        hawaiian= (Button) view.findViewById(R.id.button2);
        hawaiian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CustomizeActivity.class);
                i.putExtra("pizza","Hawaiian");
                i.putExtra("Order",order);
                startActivityForResult(i,request);
            }
        });

        pepperoni= (Button) view.findViewById(R.id.button3);
        pepperoni.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CustomizeActivity.class);
                i.putExtra("pizza","Pepperoni");
                i.putExtra("Order",order);
                startActivityForResult(i,request);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request){
            if(resultCode==result){
                try {
                    data.getSerializableExtra("Order");
                order=(Order) data.getSerializableExtra("Order");
                System.out.println("FRAGMENT: "+order);
                }
                catch(Exception e){
                    System.out.println("FRAGMENT: " + "empty");
                }
            }
        }
    }
}