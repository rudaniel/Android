package project4;

/**
 * The Pizza Maker class makes an instance of pizza depending on type.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class PizzaMaker {
	
	/**
	 * The Pizza abstract class will create the overall design for all the pizza types.
	 * @param flavor typer of pizza,
	 * @return instance of pizza.
	 */
	public static Pizza createPizza(String flavor) {
		String deluxe="Deluxe";
		String hawaiian="Hawaiian";
		String pepperoni="Pepperoni";
		Pizza pizza=null;
		if(flavor.equals(deluxe)) {
			pizza= new Deluxe();
		}
		else if(flavor.equals(hawaiian)) {
			pizza= new Hawaiian();
		}
		else if(flavor.equals(pepperoni)) {
			pizza= new Pepperoni();
		}
		return pizza;
	}
}
