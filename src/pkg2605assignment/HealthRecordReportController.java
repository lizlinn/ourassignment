/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class HealthRecordReportController implements Initializable {

    @FXML
    private BarChart<String, Double> walkRunChart;
    @FXML
    private BarChart<String, Double> gymChart;
    @FXML
    private BarChart<String, Double> stairsChart;
    @FXML
    private LineChart<String, Double> sleepChart;
    private Label Welcome;
    @FXML
    private Label Name;
    @FXML
    private Label Height;
    @FXML
    private Label Weight;
    @FXML
    private Label Age;
    @FXML
    private Label gpDate;
    @FXML
    private Label optomDate;
    @FXML
    private Label dentistDate;
    @FXML
    private Label otherDate;
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 


    
}
