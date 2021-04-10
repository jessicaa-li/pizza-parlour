package application;

/* Assignment 5
 * Jessica Li
 * 20179502
 * 19jal
 */


/**
 * A class to create orders of multiple pizzas.
 * This class implements Comparable (for sorting) and Serializable (for filing).
 * @author Jessica Li
 *
 */
public class LineItem implements Comparable<LineItem>{
	
	private Pizza pizza;
	public int number;

	/**
	 * Full parameter constructor.
	 * @param number - The number of pizzas ordered.
	 * @param pizza - The type of pizza ordered.
	 * @throws IllegalPizza - If arguments are not legal.
	 */
	public LineItem (int number, Pizza pizza) throws IllegalPizza{
		setNumber (number);
		setPizza(pizza);
	}
	
	/**
	 * One parameter constructor.
	 * @param pizza - The type of pizza ordered.
	 * @throws IllegalPizza - If arguments are not legal.
	 */
	public LineItem (Pizza pizza) throws IllegalPizza {
		setNumber(1);
		setPizza (pizza);
	}
	
	/**
	 * Sets the pizza for the order
	 * @param pizza - The pizza ordered
	 * @throws IllegalPizza - If the pizza is not legal
	 */
	public void setPizza(Pizza pizza) throws IllegalPizza {
		if (pizza == null) {
			throw new IllegalPizza ("Illegal number: " + number);
		}
		else {
			this.pizza = pizza;
		}
	}
	
	/**
	 * Returns the type of pizza ordered
	 * @return The pizza ordered
	 */
	public Pizza getPizza () {
		return pizza;
	}
	
	/**
	 * Sets the number of pizzas ordered as int i.
	 * @param i - The number pizzas ordered
	 * @throws IllegalPizza - If number of pizzas ordered is not legal
	 */
	public void setNumber(int i) throws IllegalPizza {
		if (i < 1 || i > 100) {
			throw new IllegalPizza ("Illegal number: " + i);
		}
		number = i;
	}
	
	/**
	 * Returns the number of pizzas ordered
	 * @return The number of pizzas ordered
	 */
	public int getNumber () {
		return number;
	}
	
	/**
	 * Returns the cost of the pizzas
	 * @return The cost of the pizzas
	 */
	public double getCost () {
		double cost;
		cost = pizza.getCost();
		// applying discounts for 10-20 pizzas
		if (number >= 10 && number <= 20) {
			cost *= 0.9;
		}
		
		// applying discounts for 20+ pizzas
		if (number > 20) {
			cost *= 0.85;
		}
		cost *= number;
		return cost;
	}
	
	/**
	 * Returns the pizza order as a String
	 * @return The pizza order
	 */
	public String toString () {
		if (number < 10) {
			return " " + number + " " + pizza.toString();
		}
		return number + " " + pizza.toString();
	}
	
	/**
	 * Returns the difference when orders are compared.
	 * @return The difference between costs of the two pizza orders
	 */
	@Override
	public int compareTo(LineItem item) {
		return (int) (item.getCost()-this.getCost());
	}
}
