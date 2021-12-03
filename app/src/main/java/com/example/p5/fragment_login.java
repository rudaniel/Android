package com.example.p5;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText editTextPhone;
    String phoneNumberString;
    Button button4;

    public fragment_login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_login.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_login newInstance(String param1, String param2) {
        fragment_login fragment = new fragment_login();
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
    public boolean alert(String phoneNumberString) {
        if(!phoneCheck(phoneNumberString)) {
            int offset=0;
            String message="Enter Valid Phone number";
            Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
            View t= toast.getView();
            toast.setGravity(Gravity.CENTER,offset,offset);
           // t.setBackgroundColor(getResources().getColor(R.color.maroon_500));
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setTextColor(getResources().getColor(R.color.maroon_200));
            toastMessage.setBackgroundColor(getResources().getColor(R.color.clear));
            toast.show();
            return false;
        }
        return true;
    }

    public boolean phoneCheck(String phoneNumberString) {
        boolean result = phoneNumberString.matches("[0-9]+");

        if(result && phoneNumberString.length() == 10){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        editTextPhone = (EditText) view.findViewById(R.id.editTextPhone);
        button4= (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() { //When the button is clicked the method will run.
            public void onClick(View v){

                phoneNumberString = editTextPhone.getText().toString();

                if(alert(phoneNumberString)) {
                    Intent i = new Intent(getActivity(), OrderingActivity.class);

                    final int result=1;
                    i.putExtra("number",phoneNumberString);
                    startActivity(i);
                }

            }
        });
        return view;
    }
}