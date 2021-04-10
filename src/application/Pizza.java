package application;
/* Assignment 5
 * Jessica Li
 * 20179502
 * 19jal
 */

import java.text.DecimalFormat;

/**
 * A class to create a pizza with a certain size, a certain amount of cheese, and specific toppings.
 * This class implements Serializable for filing and DecimalFormat for cost rounding.
 * @author Jessica Li
 *
 */
public class Pizza {

	private static DecimalFormat df = new DecimalFormat ("0.00");

	public enum Size {Small, Medium, Large};
	public enum Cheese {Single, Double, Triple};
	public enum Topping {None, Single};
	
	private Size size;
	private Cheese cheese;
	private Topping ham;
	private Topping pineapple;
	private Topping pepper;
	private boolean vegetarian;
	
	/**
	 * Full parameter constructor.
	 * @param size - The size of the pizza.
	 * @param vegetarian - If the pizza is vegetarian or not.
	 * @param cheese - The amount of cheese on the pizza.
	 * @param pineapple - The amount of pineapple on the pizza.
	 * @param pepper - The amount of pepper on the pizza.
	 * @param ham - The amount of ham on the pizza.
	 * @throws IllegalPizza - If arguments are not legal.
	 */
	public Pizza (Size size,  boolean vegetarian, Cheese cheese, Topping pineapple, Topping pepper, Topping ham) throws IllegalPizza {
		setSize (size);
		setCheese (cheese);
		setHam (ham);
		setPineapple (pineapple);
		setPepper (pepper);
		setVegetarian (vegetarian);
	}
	
	/**
	 * Five parameter constructor.
	 * @param size - The size of the pizza.
	 * @param cheese - The amount of cheese on the pizza.
	 * @param pineapple - The amount of pineapple on the pizza.
	 * @param pepper - The amount of pepper on the pizza.
	 * @param ham - The amount of ham on the pizza.
	 * @throws IllegalPizza - If arguments are not legal.
	 */
	public Pizza (Size size, Cheese cheese, Topping pineapple, Topping pepper, Topping ham) throws IllegalPizza{
		setSize (size);
		setCheese (cheese);
		setHam (ham);
		setPineapple (pineapple);
		setPepper (pepper);
		setVegetarian (false);
	}
	
	/**
	 * Default constructor.
	 */
	public Pizza () {
		size = Size.Small;
		cheese = Cheese.Single;
		ham = Topping.Single;
		vegetarian = false;	
	}
	
	/**
	 * Sets the size of the pizza
	 * @param size - The size of the pizza
	 * @throws IllegalPizza If the size is not valid
	 */
	public void setSize(Size size) throws IllegalPizza{
		if (size == null|| (size != Size.Small && size != Size.Medium &&
				size != Size.Large)) {
			throw new IllegalPizza ("Illegal size: size is not valid");
		}
		else {
			this.size = size;
		}
	}
	
	/**
	 * Returns the size of the pizza
	 * @return The size of the pizza
	 */
	public Size getSize () {
		return size;
	}
	
	/**
	 * Sets the amount of cheese on the pizza
	 * @param cheese - The amount of cheese on the pizza
	 * @throws IllegalPizza If the amount of cheese is not valid
	 */
	public void setCheese(Cheese cheese) throws IllegalPizza {
		if (cheese == null|| (cheese != Cheese.Single && cheese != Cheese.Double
				&& cheese != Cheese.Triple)) {
			throw new IllegalPizza ("Illegal cheese: cheese amount is not valid");
		}
		else {
			this.cheese = cheese;
		}
	}
	
	/**
	 * Returns the amount of cheese on the pizza
	 * @return The cheese on the pizza
	 */
	public Cheese getCheese() {
		return cheese;
	}
	
	/**
	 * Sets the amount of ham on the pizza
	 * @param ham - The amount of ham on the pizza
	 * @throws IllegalPizza If the amount of ham is not valid
	 */
	public void setHam(Topping ham) throws IllegalPizza {
		if (ham == null|| (ham != Topping.Single && ham != Topping.None)) {
			throw new IllegalPizza ("Illegal ham: ham is not valid");
		}
		
		// cannot have ham on a vegetarian pizza
		if (vegetarian == true && ham != Topping.None) {
			throw new IllegalPizza ("Illegal ham: cannot have ham on a vegetarian pizza");
		}
		
		else {
			this.ham = ham;
		}
	}
	
	/**
	 * Returns the amount of ham on the pizza
	 * @return The ham on the pizza
	 */
	public Topping getHam() {
		return ham;
	}
	
	/**
	 * Sets the amount of pineapple on the pizza
	 * @param pineapple - The amount of pineapple on the pizza
	 * @throws IllegalPizza If the amount of pineapple is not valid
	 */
	public void setPineapple(Topping pineapple) throws IllegalPizza {
		if (pineapple == null|| (pineapple != Topping.Single && pineapple != Topping.None)) {
			throw new IllegalPizza ("Illegal pineapple: pineapple is not valid");
		}
		else {
			this.pineapple = pineapple;
		}
	}
	
	/**
	 * Returns the amount of pineapple on the pizza
	 * @return The pineapple on the pizza
	 */
	public Topping getPineapple() {
		return pineapple;
	}
	
	/**
	 * Sets the amount of pepper on the pizza
	 * @param pepper - The amount of pepper on the pizza
	 * @throws IllegalPizza If the amount of pepper is not valid
	 */
	public void setPepper(Topping pepper) throws IllegalPizza {
		if (pepper == null|| (pepper != Topping.Single && pepper != Topping.None)) {
			throw new IllegalPizza ("Illegal pepper: pepper is not valid");
		}
		else {
			this.pepper = pepper;
		}
	}
	
	/**
	 * Returns the amount of pepper on the pizza
	 * @return The pepper on the pizza
	 */
	public Topping getPepper() {
		return pepper;
	}
	
	/**
	 * Sets if the pizza is vegetarian or not
	 * @param vegetarian - A boolean in which true repreents a vegetarian pizza
	 * @throws IllegalPizza If the boolean is not valid
	 */
	public void setVegetarian(boolean vegetarian) throws IllegalPizza {
		if (vegetarian != true && vegetarian != false) {
			throw new IllegalPizza ("Illegal vegetarian: input is not valid");
		}
		
		// cannot have ham on a vegetarian pizza
		if (vegetarian == true && ham != Topping.None) {
			throw new IllegalPizza ("Illegal ham: cannot have ham on a vegetarian pizza");
		}
		
		else {
			this.vegetarian = vegetarian;
		}
	}
	
	/**
	 * Returns the boolean representing if the pizza is vegetarian or not
	 * @return The boolean true if the pizza is vegetarian
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}

	/**
	 * Returns the cost of the pizza
	 * @return The cost of the pizza
	 */
	public double getCost () {
		double cost = 0.0;
		if (cheese == Cheese.Double) {
			cost += 1.5;
		}
		
		if (cheese == Cheese.Triple) {
			cost += 3.0;
		}
		
		if (ham == Topping.Single) {
			cost += 1.5;
		}
		
		if (pineapple == Topping.Single) {
			cost += 1.5;
		}
		
		if (pepper == Topping.Single) {
			cost += 1.5;
		}
		
		if (size == Size.Small) {
			cost += 7.00;
		}
		
		if (size == Size.Medium) {
			cost += 9.00;
		}
		
		if (size == Size.Large){
			cost += 11.00;
		}
		return cost;
	}
	
	/**
	 * Returns the pizza order formatted as a String
	 * @return A string o the pizza ordered
	 */
	public String toString () {
		String veg = "",  pineapple = "", pepper = "", ham = "";
		String info;
		if (vegetarian == true) {
			veg = " vegetarian";
		}
		
		if (this.pineapple == Topping.Single) {
			pineapple = ", pineapple";
		}
		if (this.pepper == Topping.Single) {
			pepper = ", green pepper";
		}
		if (this.ham == Topping.Single) {
			ham = ", ham";
		}
		// concatenate all the info into one string
		info = size + veg + " pizza, " + cheese + " cheese" 
				+ pineapple + pepper + ham + ". Cost: $" + df.format(getCost()) + " each.";
		return info;
	}
	
	/**
	 * Returns a boolean comparing the equality of the pizzas
	 * @return A boolean with true representing equal pizzas
	 */
	@Override
	public boolean equals (Object p2){
		if (p2 instanceof Pizza) {
			Pizza checkPizza = (Pizza) p2;
			// check for equality in all the toppings
			if (size == checkPizza.getSize() && cheese == checkPizza.getCheese () && ham == checkPizza.getHam ()&& pineapple
					== checkPizza.getPineapple () && pepper == checkPizza.getPepper () && vegetarian == checkPizza.isVegetarian ()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a pizza with the same properties 
	 * @return a new copy of the same pizza
	 */
	@Override
	public Pizza clone () {
		Pizza pizza;
		try {
			pizza = new Pizza (size, vegetarian, cheese, pineapple, pepper, ham);
		} catch (IllegalPizza e) {
			return null;
		}
		return pizza; 
	}
}
