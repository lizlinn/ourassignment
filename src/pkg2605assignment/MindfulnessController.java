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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

/**
 * FXML Controller class
 *
 * @author elizabethlin
 */
public class MindfulnessController implements Initializable {
    
    @FXML
    private BarChart<?, ?> mindfulnessweek;

    @FXML
    private CategoryAxis weekdate;

    @FXML
    private NumberAxis weekminutes;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
