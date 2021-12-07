package com.example.p5;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project4.Order;

/**
 * Log in page before an order can be made.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class fragment_login extends Fragment {

    EditText editTextPhone;
    String phoneNumberString;
    Button button4;
    Order order=null;
    private static final int request=0;
    private static final int result=-1;
    static final String message="Enter Valid Phone number";
    static final String numberKey = "number";
    static final String orderKey = "Order";

    /**
     * Required empty public constructor
     */
    public fragment_login() {
    }

    /**
     * Creates the page.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Sends an error message if phone number is not valid.
     */
    public boolean alert(String phoneNumberString) {
        if(!phoneCheck(phoneNumberString)) {
            Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }

    /**
     * Checks the phone number being entered.
     */
    public boolean phoneCheck(String phoneNumberString) {
        boolean result = phoneNumberString.matches("[0-9]+");

        if(result && phoneNumberString.length() == 10){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Manages the log in and letting the user in.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        editTextPhone = (EditText) view.findViewById(R.id.editTextPhone);
        button4= (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                phoneNumberString = editTextPhone.getText().toString();
                if(alert(phoneNumberString)) {
                    int request=0;
                    String empty="";
                    Intent i = new Intent(getActivity(), OrderingActivity.class);
                    i.putExtra(numberKey , phoneNumberString);
                    startActivityForResult(i,request);
                    phoneNumberString=empty;
                    editTextPhone.setText(empty);
                }
            }
        });
        return view;
    }

    /**
     * Gets an orders that may have been made..
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request){
            if(resultCode==result){
                try {
                    data.getSerializableExtra(orderKey);
                    order=(Order) data.getSerializableExtra(orderKey);
                }
                catch(Exception e){
                }
            }
        }
    }
}