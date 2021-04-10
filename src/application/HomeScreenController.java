package application;
/* Assignment 5
 * Jessica Li
 * 20179502
 * 19jal
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeScreenController {

	 @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOrderPizza;

    @FXML
    private Label labelTitle;
            
    @FXML
    void initialize() {
        assert buttonOrderPizza != null : "fx:id=\"buttonOrderPizza\" was not injected: check your FXML file 'HomeScreenFXML.fxml'.";
        assert labelTitle != null : "fx:id=\"labelTitle\" was not injected: check your FXML file 'HomeScreenFXML.fxml'.";
    }
    
    @FXML
    void clickOrderPizza(ActionEvent event) throws IOException {
    	// goes to a new scene when the button is pressed to order a pizza
    	Parent placeOrder = FXMLLoader.load(getClass().getResource("MakePizzaFXML.fxml"));
		Scene placeOrderScene = new Scene (placeOrder);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(placeOrderScene);
		window.show();
    }
}
