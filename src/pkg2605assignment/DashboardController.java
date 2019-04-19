/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author t-genest
 */
public class DashboardController implements Initializable {

    @FXML
    private Button btnActivity;
    @FXML
    private Button btnNutrition;
    @FXML
    private Button btnMindfulness;
    @FXML
    private Button btnSleep;
    @FXML
    private AnchorPane dashboardHolderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchActivity(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Activity.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
    }

    @FXML
    private void switchNutrition(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Nutrition.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
    }

    @FXML
    private void switchMindfulness(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Mindfulness.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
    }

    @FXML
    private void switchSleep(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Sleep.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
    }
    
}
