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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private BarChart<String, String> mindfulnessweek;

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

    public static void printBarchart() throws SQLException {
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:assignmentdata.db");
       
        //create statement
        Statement st = conn.createStatement();
        
        //System.out.println("Connected");
        
        String selectQuery = "SELECT ActivityDay, MindfulMinutes FROM Mental wellbeing;";

        ResultSet rs = st.executeQuery(selectQuery);
        
   
        
        while (rs.next()) {
            ObservableList<BarChart> barChartData = FXCollections.observableArrayList();
            
            for (int i = 1; 1 < rs.getMetaData().getColumnCount(); i++) {
                
                barChartData.add(i);
            }
            
            
             
        }
                
        mindfulnessweek.setItems(barChartData);
        
        
        
        st.close();
        conn.close();

    }

}