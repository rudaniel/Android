package project4;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * The Pizza abstract class will create the overall design for all the pizza types.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public abstract class Pizza {

	protected ArrayList <Topping> toppings = new ArrayList<Topping>();
	protected Size size;
	static final double tax = 1.06625;
	static final double extraToppings = 1.49;
	static final DecimalFormat df = new  DecimalFormat(".00") ;
	
	/**
	 * Every class that extends this the Pizza abstract class must have this method.
	 * @return price.
	 */
	public abstract double price();
	
	/**
	 * Default size small when needed.
	 */
	public Pizza() {
		this.size= Size.Small;
	}
	
	/**
	 * Setter method to change size.
	 * @param size size of pizza.
	 */
	public void setSize(Size size) {
		this.size=size; 
	}
	
	/**
	 * Getter method to get toppings list.
	 * @return toppigs of pizza.
	 */
	public ArrayList<Topping> getToppings() {
		return toppings;
	}
	
	/**
	 * Setter method to set toppings list.
	 * @param toppings the toppings of the pizza that need to be updated.
	 */
	public void setToppings(ArrayList<Topping> toppings) {
		this.toppings=toppings;
	}
	
	/**
	 * Method extends subclasses and is used to print desired output.
	 * @return pizza description. 
	 */
	@Override
	public String toString() {
		String temp = df.format(price()*tax);	
		double total=Double.parseDouble(temp);
		return toppings+", "+size+", "+"$"+total;
	}
	
	/**
	 * Method extends subclasses and is used to check if the object is equal to current pizza instance.
	 * @return true if equal, false otherwise.
	 * @param obj The pizza to be compared.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pizza) {
			Pizza compare= (Pizza) obj;
			if (compare.size.equals(this.size)) {
				if (compare.toppings.equals(this.toppings)) {
					return true;
				}
			}
		}
		return false;
	}
	

}
