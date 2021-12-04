package project4;

import java.util.ArrayList;

/**
 * The Order class is where we store the list of pizza orders based on user phone number.
 * Subtotal of pizza list is calculated here.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class Order {
	private String phone;
	private ArrayList<Pizza> pizzas= new ArrayList<Pizza>();
	
	/**
	 * Setter method to set phone number.
	 * @param phone phone number of user
	 */
	public Order(String phone) {
		this.phone=phone;
	}
	
	/**
	 * Adds given pizza to pizza list.
	 * @param pizza pizza to be added.
	 */
	public void addPizza(Pizza pizza) {
		pizzas.add(pizza);
	}
	
	/**
	 * Removes given pizza from pizza list.
	 * @param pizza pizza to be removed.
	 */
	public void removePizza(Pizza pizza) {
		pizzas.remove(pizza);
	}
	
	/**
	 * Gets the list of pizzas.
	 * @return pizzas list
	 */
	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}
	
	/**
	 * Gets the phone number.
	 * @return phone number.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Calculated the subtotal for all the pizzas in the list.
	 * @param pizzas list of pizzas.
	 * @return total price of all pizzas.
	 */
	public double subTotal(ArrayList<Pizza> pizzas) {
		double total = 0;
		for(int i =0; i < pizzas.size(); i++) {
			total += (pizzas.get(i).price());
		}
		return total;
	}
	
	/**
	 * Used to check if the object is equal to current pizza instance.
	 * @return true if equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) { 
		if(obj instanceof Order) {
			Order compare = (Order) obj;
			if(this.phone.equals(compare.phone)) {
				return true;
			}
		}
		return false;
	}

@Override
public String toString() {
	return phone+" ["+pizzas+"]";
}
}
