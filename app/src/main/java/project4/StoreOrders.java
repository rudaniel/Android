package project4;

import java.io.File;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import javafx.stage.FileChooser.ExtensionFilter;

/**
 * The Store Order class is where we store the list of orders.
 * Adding and removing of orders is done here.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class StoreOrders {
	
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
			return true;
		}
		else {
			temp =new Order(phone);
			temp.addPizza(pizza);
			orders.add(temp);
			return true;
		}
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	/**
	 * Gets the orders that match the phone number.
	 * @param phone number of user.
	 * @return order if existing, null if not
	 */
	public Order getOrder(String phone) {
		int index=orders.indexOf(new Order(phone));
		if(index==-1) {
			return null;
		}
		return orders.get(index);
	}
	
	/**
	 * Removes order from the orders list.
	 * @param index to be removed.
	 * @return true when removed.
	 */
	public boolean removeOrder(int index) {
		orders.remove(index);
		return true;
	}
	
	/**
	 * Removes order from the orders list.
	 * @param phone number from user.
	 * @param pizza type of user.
	 * @return true if removed, false otherwise.
	 */
	public boolean remove(String phone, Pizza pizza) {
		Order order=new Order(phone);
		Order temp;
		int index=orders.indexOf(order);
		if(index!=-1) {
			temp= orders.get(index);
			temp.removePizza(pizza);
			orders.set(index, temp);
			return true;
		}
		return false;
	}
	
	/**
	 * Exports entire orders list into a text file.
	 * @return true if file if made.
	 */
//	public boolean export(){
//		try {
//		FileChooser chooser = new FileChooser();
//		chooser.setTitle("Open Target File for the Export");
//		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
//				new ExtensionFilter("All Files", "*.*"));
//		Stage stage = new Stage();
//		File targetFile = chooser.showSaveDialog(stage);
//		FileWriter fw = new FileWriter(targetFile, true);
//		PrintWriter pw = new PrintWriter(fw);
//		String order="Order: ";
//		for(int i = 0; i < orders.size(); i++) {
//			pw.println(order+orders.get(i));
//		}
//		pw.close();
//		return true;
//		}
//		catch(Exception e) {
//		return false;
//		}
//	}
	
	/**
	 * Returns instance of list.
	 * @return orders list.
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
}
