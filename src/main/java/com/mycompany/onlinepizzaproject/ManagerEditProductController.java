package com.mycompany.onlinepizzaproject;

import com.mycompany.onlinepizzaproject.backend.API;
import com.mycompany.onlinepizzaproject.backend.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagerEditProductController {
	@FXML private MainController mainController;
	@FXML private ListView<String> product_edit_list_view;
	@FXML private TextField product_edit_price_textfield;
	@FXML private TextField product_edit_quantity_textfield;
	
	private Stage stage;
	private Product product;

    @FXML private void initialize(){
        mainController = MainController.getMainControllerInstance();
        product = mainController.getProductToChange();
        ObservableList<String> productLines = FXCollections.observableArrayList();
        productLines.add("Product name: " + product.getName());
        productLines.add("Category: " + product.getCategory());
        productLines.add("Price: " + product.getPrice());
        productLines.add("Quantity: " + product.getStock());
        product_edit_list_view.setItems(productLines);
    }

    @FXML
    private void editProduct(ActionEvent event){
    	String price = product_edit_price_textfield.getText();
    	String quantity = product_edit_quantity_textfield.getText();
    	if(!price.trim().isEmpty() && !quantity.trim().isEmpty()){
    		
        	//change both price and quantity. Use product. 
    		API.updateProductPriceAndStock(product, Integer.valueOf(price), Integer.valueOf(quantity));
    		
        }else if(!price.trim().isEmpty()) {
        	
        	//change price. Use product.
        	API.updateProductPrice(product, Integer.valueOf(price));
        	
        }else if(!quantity.trim().isEmpty()) {
        	
        	//change quantity. Use product.
        	API.updateProductStock(product, Integer.valueOf(quantity));
        	
        }else {
        	//both empty. do nothing
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

}
