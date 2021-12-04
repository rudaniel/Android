package project4;

import java.util.Arrays;

/**
 * The Pepperoni sub class is where we create a Pizza of type Pepperoni.
 * Subtotal of pizza is calculated here.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class Pepperoni extends Pizza {

	private double pricePepperoni;
	
	/**
	 * Setting up the default one toppings for a Pepperoni pizza.
	 */
	public Pepperoni() {
		super();
		toppings.addAll(Arrays.asList(Topping.Pepperoni));
	}	
	
	/**
	 * Calculates the subtotal for the Pepperoni pizza based on size and topping count.
	 * @return subtotal of the pizza.
	 */
	public double price() {
		int toppingAmount = toppings.size();
		double regularPrice = 8.99;
		pricePepperoni = regularPrice;
		int medium = 2;
		int large = 4;
		int minToppings = 1;
		int maxToppings = 7;
		
		if(size.equals(Size.Small)) {
			if(toppingAmount == minToppings) {
				pricePepperoni = regularPrice;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				pricePepperoni = regularPrice + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		if(size.equals(Size.Medium)) {
			if(toppingAmount == minToppings || toppingAmount < minToppings) {
				pricePepperoni = regularPrice + medium;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				pricePepperoni = (regularPrice + medium) + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		if(size.equals(Size.Large)) {
			if(toppingAmount == minToppings || toppingAmount < minToppings) {
				pricePepperoni = regularPrice + large;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				pricePepperoni = (regularPrice + large) + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		
		String temp = df.format(pricePepperoni);	   
	    double finalPrice = Double.parseDouble(temp);
	    
		return finalPrice;
	}
	
	/**
	 * Method extends subclasses and is used to print desired output.
	 * @return pizza description. 
	 */
	@Override
	public String toString() {
		return "Pepperoni Pizza :"+ super.toString();
	}
	
	/**
	 * Method extends subclasses and is used to check if the object is equal to current pizza instance.
	 * @return true if equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pepperoni) {
			return super.equals(obj);
		}
		return false;
	}

	
}
