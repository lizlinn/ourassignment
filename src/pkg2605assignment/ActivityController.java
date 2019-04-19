/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


/**
 * FXML Controller class
 *
 * @author t-genest
 */
public class ActivityController implements Initializable {

@FXML
private BarChart<String, Double> walkRunChart;
@FXML
private BarChart<String, Double> stepsChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        loadAerobicChart();
        loadStepsChart();
    } catch (SQLException ex) {
        Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }   
    
    //Load walking/running (aerobics) bar chart with database
    public void loadAerobicChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, km from Aerobic WHERE date >= '6/5/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            walkRunChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load steps bar chart with database 
    public void loadStepsChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, steptotal FROM Steps WHERE date >= '6/5/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        
        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            stepsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
}
