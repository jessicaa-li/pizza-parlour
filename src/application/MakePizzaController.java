package application;
/* Assignment 5
 * Jessica Li
 * 20179502
 * 19jal
 */

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MakePizzaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Pizza.Size> boxSize;

    @FXML
    private RadioButton buttonYesVeg;

    @FXML
    private ToggleGroup vegetarianGroup;

    @FXML
    private RadioButton buttonNoVeg;

    @FXML
    private ChoiceBox<Pizza.Cheese> boxCheese;

    @FXML
    private RadioButton buttonNonePineapple;

    @FXML
    private ToggleGroup pineappleGroup;
    
    @FXML
    private RadioButton buttonSinglePineapple;

    @FXML
    private RadioButton buttonNonePepper;

    @FXML
    private ToggleGroup pepperGroup;

    @FXML
    private RadioButton buttonSinglePepper;

    @FXML
    private RadioButton buttonNoneHam;

    @FXML
    private ToggleGroup hamGroup;

    @FXML
    private RadioButton buttonSingleHam;

    @FXML
    private Button buttonPlaceOrder;

    @FXML
    private Button buttonHome;
    
    @FXML
    private TextField textInputQuantity;
    
    @FXML
    private Label labelCost;
    
    @FXML
    private Label labelCostQuantity;
    
    @FXML
    private Label labelCostQuantityNum;
    
    @FXML
    private TextArea textOrderDisplay;
    
    @FXML
    private Label labelTotalCost;
    
    @FXML
    private Label labelCustomize;

    // declaring lists for the choice boxes
    private ObservableList <Pizza.Size> sizeList = FXCollections.observableArrayList (
    		Arrays.asList(Pizza.Size.values()));
    
    private ObservableList <Pizza.Cheese> cheeseList = FXCollections.observableArrayList (
    		Arrays.asList(Pizza.Cheese.values()));
    
	private static DecimalFormat df = new DecimalFormat ("0.00");
    
	// declaring the pizza object and its default attributes to allow for a running total and
	// prevent the user from entering a null pizza
	private Pizza pizza = null;
	private Pizza.Size size = Pizza.Size.Small;
	private boolean vegetarian = false;
	private Pizza.Cheese cheese = Pizza.Cheese.Single;
	private Pizza.Topping pineapple = Pizza.Topping.None;
	private Pizza.Topping greenPepper = Pizza.Topping.None;
	private Pizza.Topping ham = Pizza.Topping.None;
	private int counter = 1;
	private LineItem item = null;
	private ArrayList<LineItem> orders = new ArrayList<>();
	private double total = 0.0;
    
    @FXML
    void initialize() throws IllegalPizza {
		assert boxSize != null : "fx:id=\"boxSize\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonYesVeg != null : "fx:id=\"buttonYesVeg\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert vegetarianGroup != null : "fx:id=\"vegetarianGroup\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonNoVeg != null : "fx:id=\"buttonNoVeg\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert boxCheese != null : "fx:id=\"boxCheese\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonNonePineapple != null : "fx:id=\"buttonNonePineapple\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert pineappleGroup != null : "fx:id=\"pineappleGroup\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonSinglePineapple != null : "fx:id=\"buttonSinglePineapple\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonNonePepper != null : "fx:id=\"buttonNonePepper\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert pepperGroup != null : "fx:id=\"pepperGroup\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonSinglePepper != null : "fx:id=\"buttonSinglePepper\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonNoneHam != null : "fx:id=\"buttonNoneHam\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert hamGroup != null : "fx:id=\"hamGroup\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonSingleHam != null : "fx:id=\"buttonSingleHam\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonPlaceOrder != null : "fx:id=\"buttonPlaceOrder\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
		assert buttonHome != null : "fx:id=\"buttonHome\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert textInputQuantity != null : "fx:id=\"textInputQuantity\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert labelCost != null : "fx:id=\"labelCost\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert labelCostQuantity != null : "fx:id=\"labelCostQuantity\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert labelCostQuantityNum != null : "fx:id=\"labelCostQuantityNum\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert textOrderDisplay != null : "fx:id=\"textOrderDisplay\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert labelTotalCost != null : "fx:id=\"labelTotalCost\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        assert labelCustomize != null : "fx:id=\"labelCustomize\" was not injected: check your FXML file 'MakePizzaFXML.fxml'.";
        
        // sets initial/default values so the user cannot order a null Pizza
        pizza = new Pizza(size, vegetarian, cheese, pineapple, greenPepper, ham);
        item = new LineItem (1, pizza);
        labelCost.setText("$" + df.format(pizza.getCost()));
        labelCostQuantity.setText("$" + df.format(item.getCost()));
        
        // adds values to choice boxes
        boxSize.setItems(sizeList);
        boxSize.setValue(Pizza.Size.Small);
        boxCheese.setItems(cheeseList);
        boxCheese.setValue(Pizza.Cheese.Single);
        
        // executes every time a change is made in the Size choice box
        boxSize.valueProperty().addListener((observableValue, oldVal, newVal) -> {
        	try {
        		// sets and updates Size in order to calculate new cost
				pizza.setSize (boxSize.getValue());
				item.setPizza(pizza);
			} catch (IllegalPizza e) {
				e.printStackTrace();
			}
        	labelCost.setText("$" + df.format(pizza.getCost()));
        	labelCostQuantity.setText("$" + df.format(item.getCost()));
        });
        
        // executes every time one of the Vegetarian radio buttons is pressed
        vegetarianGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	// disables the ability for the user to add ham if vegetarian is selected
        	// sets and updates vegetarian option depending on what button is pressed
    		try {
    			if (buttonYesVeg.isSelected()) {
					pizza.setVegetarian (true);
					item.setPizza(pizza);
					buttonSingleHam.setDisable(true);
    			}
    			else {
    				pizza.setVegetarian (false);
					item.setPizza(pizza);
					buttonSingleHam.setDisable(false);
    			}
			} catch (IllegalPizza e) {
				e.printStackTrace();
			}
        });
        
        // executes every time a change is made in the Cheese choice box
        boxCheese.valueProperty().addListener((observableValue, oldVal, newVal) -> {
        	try {
        		// sets and updates Cheese in order to calculate new cost
				pizza.setCheese(boxCheese.getValue());
				item.setPizza(pizza);
			} catch (IllegalPizza e) {
				e.printStackTrace();
			}
        	labelCost.setText("$" + df.format(pizza.getCost()));
        	labelCostQuantity.setText("$" + df.format(item.getCost()));
        });
        
        // executes every time one of the Pineapple radio buttons is pressed
        pineappleGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	try {
        		// sets and updates Pineapple in order to calculate new cost
				pizza.setPineapple (Pizza.Topping.valueOf(((RadioButton) pineappleGroup.getSelectedToggle()).getText()));
				item.setPizza(pizza);
			} catch (IllegalPizza e) {
				e.printStackTrace();
			}
        	labelCost.setText("$" + df.format(pizza.getCost()));
        	labelCostQuantity.setText("$" + df.format(item.getCost()));
        });
        
        // executes every time one of the Pepper radio buttons is pressed
        pepperGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	try {
        		// sets and updates Pepper in order to calculate new cost
				pizza.setPepper (Pizza.Topping.valueOf(((RadioButton) pepperGroup.getSelectedToggle()).getText()));
				item.setPizza(pizza);
			} catch (IllegalPizza e) {
				e.printStackTrace();
			}
        	labelCost.setText("$" + df.format(pizza.getCost()));
        	labelCostQuantity.setText("$" + df.format(item.getCost()));
        });
        
        // executes every time one of the Ham radio buttons is pressed
        hamGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
        	try {
        		// disables the ability for the user to add ham if vegetarian is selected
        		if (buttonSingleHam.isSelected()) {
        			buttonYesVeg.setDisable(true);
        			buttonNoVeg.setSelected(true);
        		}
        		
        		else {
        			buttonYesVeg.setDisable(false);
        			buttonNoVeg.setSelected(false);
        		}
        		// sets and updates Ham in order to calculate new cost
				pizza.setHam (Pizza.Topping.valueOf(((RadioButton) hamGroup.getSelectedToggle()).getText()));
				item.setPizza(pizza);
			} catch (IllegalPizza e) {
				e.printStackTrace();
			}
        	labelCost.setText("$" + df.format(pizza.getCost()));
        	labelCostQuantity.setText("$" + df.format(item.getCost()));
        });
        
        // code to only allow integer inputs for the quantity
        textInputQuantity.textProperty().addListener ((observableValue, oldText, newText) -> {
        	if (newText != null && !newText.isEmpty()) {
        		try {
        			@SuppressWarnings("unused")
        			int aVal = Integer.parseInt(newText);
        			// makes sure that the user orders only between 1 and 100 pizzas
        			if (aVal < 1 || aVal > 100) {
        				((StringProperty)observableValue).setValue(oldText);
                	}
        			else {
        				labelCostQuantityNum.setText("Cost for " + newText + " Pizza(s):");
        				item.setPizza(pizza);
        				item.setNumber(aVal);
        				labelCostQuantity.setText("$" + df.format(item.getCost()));
        			}
        		} catch (NumberFormatException | IllegalPizza e) {
        			((StringProperty)observableValue).setValue(oldText);
        		}
        	}
        });
    }
    
    @FXML
    void btnOnClick(ActionEvent event) throws IOException, IllegalPizza {  
    	// adds the order to the LineItem when the user clicks to place their order
    	orders.add(item);
    	textOrderDisplay.appendText("\n" + counter + ") " + item.toString());
    	
    	// don't need to include cost after discounts if they order only 1 pizza
    	if (item.number > 1) {
    		textOrderDisplay.appendText("\n\t Cost after discounts: $" + df.format(item.getCost()));
    	}
    	
    	// add cost to total and display
    	total += item.getCost();
    	labelTotalCost.setText("Total Cost: $" + df.format(total));
    	counter++;
    }
}