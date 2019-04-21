/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class SettingsPage2Controller implements Initializable {

    @FXML
    private ComboBox languageComboBox;
    @FXML
    private Button btnDeactivate;
    //@FXML
    //private AnchorPane anchorPane;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        languageComboBox.getItems().clear();
        languageComboBox.getItems().addAll("Chinese (Simplified)",
                                "Chinese (Traditional)",
                                "Croatian",
                                "Czech",
                                "Danish",
                                "Dutch",
                                "English (Australia)",
                                "English (UK)",
                                "English (US)",
                                "Finnish",
                                "French",
                                "German",
                                "Greek",
                                "Hindi",
                                "Indonesian",
                                "Italian",
                                "Japanese",
                                "Korean",
                                "Malay",
                                "Norwegian",
                                "Polish",
                                "Portugese",
                                "Romanian",
                                "Russian",
                                "Slovak",
                                "Spanish",
                                "Swedish",
                                "Tagalog",
                                "Thai",
                                "Turkish",
                                "Ukrainian",
                                "Vietnamese");
                                            
            
                
    }

    @FXML
    private void buttonDeactivate(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeactivatePage.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e){
            
        }
    }
    
}
