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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import project4.Deluxe;
import project4.Hawaiian;
import project4.Order;
import project4.Pepperoni;
import project4.Pizza;
import project4.PizzaMaker;
import project4.Size;
import project4.Topping;

/**
 * Controls the menu ordering page.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class CustomizeActivity extends AppCompatActivity {
    ArrayAdapter<Size> sizes;
    ArrayList<Size> s= new ArrayList<>(Arrays.asList(Size.values()));
    Spinner spinner;
    String pizza;
    ImageView image;
    TextView title;

    int counter=0;
    ListView toppingsEditor;
    ArrayList<Topping> toppings =  new ArrayList<Topping>();
    ArrayList<Topping> additional =  new ArrayList<Topping>();
    ArrayList<Topping> originalToppings =  new ArrayList<Topping>();
    ArrayAdapter<Topping> adapterTopping;
    Button addToOrderButton;
   // Topping temp =  null;
    ArrayList<Topping> tempList =  new ArrayList<Topping>();
    Pizza finalPizza;
    Intent i=null;
    Order order=null;
    TextView livePrice;

    /**
     * Controls all of the functions and buttons on the page.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_customize);
        make();


        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
              order();
            }
        });
        toppingsEditor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toppingsManager(position);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                finalPizza.setSize((Size)spinner.getSelectedItem());
                calculatePrice();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String message = "Nothing Selected";
                Toast toast = Toast.makeText(CustomizeActivity.this, message, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    /**
     * Removes or adds toppings to selected toppings list based on if its already contained in it.
     */
    private void toppingsManager(int position) {
        boolean deselect=false;
        int max=7;
        Topping temp=(Topping) toppingsEditor.getItemAtPosition(position);
        if(originalToppings.contains(temp)) {
            originalToppings.remove(temp);
            counter--;
            calculatePrice();
        }
        else{
            if (counter == max) {
                String message = "Maximum Number of Toppings is 7!";
                toppingsEditor.setItemChecked(position, deselect);
                Toast toast = Toast.makeText(CustomizeActivity.this, message, Toast.LENGTH_LONG);
                toast.show();
            } else {
                originalToppings.add(temp);
                counter++;
                calculatePrice();
            }
        }
    }

    /**
     * Calculates price of pizza based on selected toppings.
     */
    private void calculatePrice() {
        finalPizza.setToppings(originalToppings);
        livePrice.setText(String.valueOf(finalPizza.price()));
    }

    /**
     * Connects buttons to xml and gets intent.
     */
    public void make() {
        i=getIntent();
        pizza= i.getExtras().getString("pizza");
        finalPizza=PizzaMaker.createPizza(pizza);
        order=(Order) i.getSerializableExtra("Order");
        sizes=new ArrayAdapter<>(this, R.layout.spinner_item , Arrays.asList(Size.values()));
        title=(TextView) findViewById(R.id.textView4);
        spinner= (Spinner) findViewById(R.id.spinner);
        image= (ImageView) findViewById(R.id.imageView5);
        livePrice = (TextView) findViewById(R.id.livePrice);
        imageSet();
        spinner.setAdapter(sizes);
        toppingsEditor = (ListView) findViewById(R.id.toppingsEditor);
        originalToppings=finalPizza.getToppings();
        additional= new ArrayList<>(Arrays.asList(Topping.values()));
        additional.removeAll(originalToppings);
        toppings.addAll(originalToppings);
        toppings.addAll(additional);
        adapterTopping = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_multiple_choice , toppings);
        toppingsEditor.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        toppingsEditor.setAdapter(adapterTopping);
        boolean selected=true;
        counter=originalToppings.size();
        for(int i=0; i<counter;i++){
            toppingsEditor.setItemChecked(i,selected);
        }
        addToOrderButton = (Button) findViewById(R.id.addToOrderButton);
        livePrice.setText(String.valueOf(finalPizza.price()));
    }

    /**
     * Passes the pizza to the other fragment.
     */
    public void order() {
        tempList.removeAll(Collections.singleton(null));
        //finalPizza = makePizza(tempList);
        order.addPizza(finalPizza);
        Intent send= new Intent();
        send.putExtra("Order", order);
        setResult(RESULT_OK, send);
        finish();
    }

    /**
     * Based on type of pizza that is being made the picture will represent that.
     */
    public void imageSet() {
        int start=0;
        int end=1;
        String uri="@drawable/"+pizza.substring(start,end).toLowerCase()+pizza.substring(end);
        title.setText(pizza);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        image.setImageDrawable(res);
    }
}