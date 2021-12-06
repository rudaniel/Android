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

import project4.Deluxe;
import project4.Hawaiian;
import project4.Order;
import project4.Pepperoni;
import project4.Pizza;
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

        if(pizza.equals(deluxe)){
            toppingsEditor.setItemChecked(, true);
        }
        else if(pizza.equals(hawaiian)){

        }
        else if(pizza.equals(pepperoni)){

        }

        addToOrderButton.setOnClickListener(new View.OnClickListener() { //When the button is clicked the method will run.
            public void onClick(View v){
              order();
            }
        });
        toppingsEditor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topping(position);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pizza temp = makePizza(tempList);
                livePrice.setText(String.valueOf(temp.price()));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Pizza temp = makePizza(tempList);
                livePrice.setText(String.valueOf(temp.price()));
            }
        });
    }

    /**
     * Connects buttons to xml and gets intent.
     */
    public void make() {
        i=getIntent();
        pizza= i.getExtras().getString("pizza");
        order=(Order) i.getSerializableExtra("Order");
        sizes=new ArrayAdapter<>(this, R.layout.spinner_item , Arrays.asList(Size.values()));
        title=(TextView) findViewById(R.id.textView4);
        spinner= (Spinner) findViewById(R.id.spinner);
        image= (ImageView) findViewById(R.id.imageView5);
        livePrice = (TextView) findViewById(R.id.livePrice);
        imageSet();
        spinner.setAdapter(sizes);
        toppingsEditor = (ListView) findViewById(R.id.toppingsEditor);
        Collections.addAll(toppings, project4.Topping.Pineapple, project4.Topping.Ham, project4.Topping.Cheese, project4.Topping.Sausage, project4.Topping.Chicken, project4.Topping.Beef, project4.Topping.Pepperoni, project4.Topping.GreenPepper, project4.Topping.Onion, project4.Topping.Mushroom, project4.Topping.BlackOlives);
        adapterTopping = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_multiple_choice , toppings);
        toppingsEditor.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        toppingsEditor.setAdapter(adapterTopping);
        addToOrderButton = (Button) findViewById(R.id.addToOrderButton);
    }

    /**
     * Stores the toppings.
     */
    public void topping(int position) {
        temp = (Topping) (toppingsEditor.getItemAtPosition(position));
        if(tempList.contains(temp)){
            tempList.remove(temp);
        }
        else{
            tempList.add(temp);
        }
        Pizza temp = makePizza(tempList);
        livePrice.setText(String.valueOf(temp.price()));
    }

    /**
     * Passes the pizza to the other fragment.
     */
    public void order() {
        tempList.removeAll(Collections.singleton(null));
        finalPizza = makePizza(tempList);
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

    /**
     * Creates the pizza with inputted toppings.
     */
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