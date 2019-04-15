/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class SignUpPageController implements Initializable {

@FXML private ComboBox comboBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //These items are for configuring the ComboBox
        comboBox.getItems().addAll("Female", "Male", "Other", "Prefer not to say");

        
    }    
    
}
