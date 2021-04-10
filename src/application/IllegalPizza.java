package application;

/* Assignment 5
 * Jessica Li
 * 20179502
 * 19jal
 */
/**
 * This class throws an exception for an illegal pizza.
 * @author Jessica Li
 *
 */
public class IllegalPizza extends Exception{
	public IllegalPizza (String message) {
		super (message);
	}
}
