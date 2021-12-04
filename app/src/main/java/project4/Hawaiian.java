package project4;

import java.util.Arrays;

/**
 * The Hawaiian sub class is where we create a Pizza of type Hawaiian.
 * Subtotal of pizza is calculated here.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class Hawaiian extends Pizza {
	
	private double priceHawaiian;
	
	/**
	 * Setting up the default two toppings for a Hawaiian pizza.
	 */
	public Hawaiian() {
		super();
		toppings.addAll(Arrays.asList(Topping.Ham,Topping.Pineapple));
	}	
	
	/**
	 * Calculates the subtotal for the Hawaiian pizza based on size and topping count.
	 * @return subtotal of the pizza.
	 */
	public double price() {
		int toppingAmount = toppings.size();
		double regularPrice = 10.99;
		priceHawaiian = regularPrice;
		int medium = 2;
		int large = 4;
		int minToppings = 2;
		int maxToppings = 7;
		
		if(size.equals(Size.Small)) {
			if(toppingAmount == minToppings) {
				priceHawaiian = regularPrice;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				priceHawaiian = regularPrice + (extraToppings * (toppingAmount - minToppings));
			}
			
		}

		if(size.equals(Size.Medium)) {
			if(toppingAmount == minToppings || toppingAmount < minToppings) {
				priceHawaiian = regularPrice + medium;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				priceHawaiian = (regularPrice + medium) + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		if(size.equals(Size.Large) || toppingAmount < minToppings) {
			if(toppingAmount == minToppings) {
				priceHawaiian = regularPrice + large;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				priceHawaiian = (regularPrice + large) + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		
		String temp = df.format(priceHawaiian);	   
	    double finalPrice = Double.parseDouble(temp);
	    
		return finalPrice;	
	}
	
	/**
	 * Method extends subclasses and is used to print desired output.
	 * @return pizza description. 
	 */
	@Override
	public String toString() {
		return "Hawaiian Pizza :"+ super.toString();
	}
	
	/**
	 * Method extends subclasses and is used to check if the object is equal to current pizza instance.
	 * @return true if equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Hawaiian) {
			return super.equals(obj);
		}
		return false;
	}
	

}
