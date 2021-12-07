package project4;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Store Order class is where we store the list of orders.
 * Adding and removing of orders is done here.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class StoreOrders implements Serializable {
	
	private ArrayList<Order> orders= new ArrayList<Order>();
	/**
	 * Default constructor 
	 */
	public StoreOrders() {
	}
	
	/**
	 * Creates the order and adds it to the overall list,
	 * @param phone number of user.
	 * @param pizza type that was created.
	 * @return true if added, false otherwise.
	 */
	public boolean add(String phone, Pizza pizza) {
		Order order=new Order(phone);
		Order temp;
		int index=orders.indexOf(order);
		if(index!=-1) {
			temp= orders.get(index);
			temp.addPizza(pizza);
			orders.set(index, temp);
		}
		else {
			temp =new Order(phone);
			temp.addPizza(pizza);
			orders.add(temp);
		}
		return true;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}

	/**
	 * Returns instance of list.
	 * @return orders list.
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * Returns instance of list.
	 * @param orders list.
	 */
	public void setOrders(ArrayList<Order> orders){
		this.orders=orders;
	}
	
}
