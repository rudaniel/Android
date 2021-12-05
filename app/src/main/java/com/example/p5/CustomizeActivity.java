package com.example.p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import project4.Deluxe;
import project4.Hawaiian;
import project4.Pepperoni;
import project4.Pizza;
import project4.Size;
import project4.Topping;

public class CustomizeActivity extends AppCompatActivity {
    ArrayAdapter<Size> sizes;
    ArrayList<Size> s= new ArrayList<>(Arrays.asList(Size.values()));
    Spinner spinner;
    String pizza;
    ImageView image;
    TextView title;
    private static final String deluxe="Deluxe";
    private static final String hawaiian="Hawaiian";
    private static final String pepperoni="Pepperoni";

    //daniel
    TextView phoneHolder;
    String phoneNumber;
    ListView toppingsEditor;
    ArrayList<Topping> toppings =  new ArrayList<Topping>();
    ArrayAdapter<Topping> adapterTopping;
    Button addToOrderButton;
    Topping temp =  null;
    ArrayList<Topping> tempList =  new ArrayList<Topping>();
    String[] message = new String[11];
    Pizza finalPizza;
    Intent i=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_customize);
        i=getIntent();
        pizza= i.getExtras().getString("pizza");
        sizes=new ArrayAdapter<>(this, R.layout.spinner_item , Arrays.asList(Size.values()));
        title=(TextView) findViewById(R.id.textView4);
        spinner= (Spinner) findViewById(R.id.spinner);
        image= (ImageView) findViewById(R.id.imageView5);
        imageSet();
        spinner.setAdapter(sizes);

        //daniel
      //  phoneHolder = (TextView) findViewById(R.id.editTextPhone2);
      //  phoneNumber = phoneHolder.getText().toString();
        toppingsEditor = (ListView) findViewById(R.id.toppingsEditor);
        Collections.addAll(toppings, project4.Topping.Pineapple, project4.Topping.Ham, project4.Topping.Cheese,
                project4.Topping.Sausage, project4.Topping.Chicken, project4.Topping.Beef, project4.Topping.Pepperoni,
                project4.Topping.GreenPepper, project4.Topping.Onion, project4.Topping.Mushroom, project4.Topping.BlackOlives);
        adapterTopping = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_multiple_choice , toppings);
        toppingsEditor.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        toppingsEditor.setAdapter(adapterTopping);

        addToOrderButton = (Button) findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(new View.OnClickListener() { //When the button is clicked the method will run.
            public void onClick(View v){

                tempList.removeAll(Collections.singleton(null));
                finalPizza = makePizza(tempList);
                System.out.println(finalPizza.toString());

/*
                Arrays.fill(message, null);
                for(int i = 0; i<tempList.size(); i++){
                    if(tempList.get(i).name().equals(null)){
                    }
                    else{
                        message[i] = tempList.get(i).name();
                    }
                }
                for(int i =0; i<message.length; i++){
                    if(message[i] == null){

                    }
                    else{
                        System.out.println(message[i]);
                    }

                }
                */

                //    Toast toast=Toast.makeText(getActivity(),message, Toast.LENGTH_LONG);
                //    toast.show();

            }
        });


        toppingsEditor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                temp = (Topping) (toppingsEditor.getItemAtPosition(position));
                if(tempList.contains(temp)){
                    tempList.remove(temp);
                }
                else{
                    tempList.add(temp);
                }

            }
        });
    }

    public void imageSet() {
        String uri="";
        if(pizza.equals(deluxe)){
            uri = "@drawable/deluxe";
            title.setText(deluxe);
        }
        else if(pizza.equals(hawaiian)){
            uri = "@drawable/hawaiian";
            title.setText(hawaiian);
        }
        else if(pizza.equals(pepperoni)){
            uri = "@drawable/pepperoni";
            title.setText(pepperoni);
        }
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        image.setImageDrawable(res);
    }

    public Pizza makePizza(ArrayList<Topping> toppings) {
        Pizza currentPie = null;
        Size tempSize = (Size) spinner.getSelectedItem();
        if(pizza.equals(deluxe)){
            currentPie = new Deluxe();
            currentPie.setToppings(toppings);
            currentPie.setSize(tempSize);
        }
        else if(pizza.equals(hawaiian)){
            currentPie = new Hawaiian();
            currentPie.setToppings(toppings);
            currentPie.setSize(tempSize);
        }
        else if(pizza.equals(pepperoni)){
            currentPie = new Pepperoni();
            currentPie.setToppings(toppings);
            currentPie.setSize(tempSize);
        }

        return currentPie;
    }
}