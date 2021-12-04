package project4;

import java.util.Arrays;

/**
 * The Deluxe sub class is where we create a Pizza of type Deluxe.
 * Subtotal of pizza is calculated here.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class Deluxe extends Pizza {
	
	private double priceDeluxe;
	
	/**
	 * Setting up the default five toppings for a deluxe pizza.
	 */
	public Deluxe() {
		super();
		toppings.addAll(Arrays.asList(Topping.BlackOlives,Topping.Cheese,Topping.Chicken,Topping.GreenPepper,Topping.Mushroom));
	}	
	
	/**
	 * Calculates the subtotal for the Deluxe pizza based on size and topping count.
	 * @return subtotal of the pizza.
	 */
	@Override
	public double price() {
		
		int toppingAmount = toppings.size();
		double regularPrice = 12.99;
		priceDeluxe = regularPrice;
		int medium = 2;
		int large = 4;
		int minToppings = 5;
		int maxToppings = 7;
		
		if(size.equals(Size.Small)) {
			if(toppingAmount == minToppings) {
				priceDeluxe = regularPrice;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				priceDeluxe = regularPrice + (extraToppings * (toppingAmount - minToppings));
			}
			
		}

		if(size.equals(Size.Medium)) {
			if(toppingAmount == minToppings || toppingAmount < minToppings) {
				priceDeluxe = regularPrice + medium;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				priceDeluxe = (regularPrice + medium) + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		if(size.equals(Size.Large)) {
			if(toppingAmount == minToppings || toppingAmount < minToppings) {
				priceDeluxe = regularPrice + large;
			}
			if(toppingAmount > minToppings && toppingAmount <= maxToppings) {
				priceDeluxe = (regularPrice + large) + (extraToppings * (toppingAmount - minToppings));
			}
			
		}
		
		String temp = df.format(priceDeluxe);	   
	    double finalPrice = Double.parseDouble(temp);
	    
		return finalPrice;
	}
	
	/**
	 * Method extends subclasses and is used to print desired output.
	 * @return pizza description. 
	 */
	@Override
	public String toString() {
		return "Deluxe Pizza :"+ super.toString();
	}
	
	/**
	 * Method extends subclasses and is used to check if the object is equal to current pizza instance.
	 * @return true if equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Deluxe) {
			return super.equals(obj);
		}
		return false;
	}
	

	
}





















