/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class BodyProfilePageController implements Initializable {

    @FXML
    private LineChart<String, Double> bmiChart;
    @FXML
    private LineChart<String, Double> leanFatChart;
    @FXML
    private Label bWelcome;
    @FXML
    private Label bHeight;
    @FXML
    private Label bWeight;
    @FXML
    private Label bBMI;
    @FXML
    private Label bLeanFat;
    @FXML
    private Label bHeightDate;
    @FXML
    private Label bWeightDate;
    @FXML
    private Label bBMIDate;
    @FXML
    private Label bLeanDate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadBmiChart();
            loadLeanFatChart();
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(BodyProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void connect() throws SQLException {
       //create connection
       Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
       System.out.println("DataBase connected");
        
        //create statement	
        Statement st = conn.createStatement();
        
        //Welcome NAME
        String nameQuery = "SELECT firstname FROM User;";
        ResultSet nameResult = st.executeQuery(nameQuery);
        bWelcome.setText(nameResult.getString(1) + "'s Body Profile");
        
        //Height stats 
        String heightQuery = "SELECT height FROM User;";
        ResultSet heightResult = st.executeQuery(heightQuery);
        bHeight.setText(heightResult.getString(1) + " m");
        
        //Weight stats - 07/05/2018
        String weightQuery = "SELECT weight FROM BMI WHERE date = '7/5/2018';";
        ResultSet weightResult = st.executeQuery(weightQuery);
        bWeight.setText(weightResult.getString(1) + " kg");
        
        //BMI stats - 07/05/2018
        String bmiQuery = "SELECT bmi FROM BMI WHERE date = '7/5/2018';";
        ResultSet bmiResult = st.executeQuery(bmiQuery);
        bBMI.setText(bmiResult.getString(1));
        
        //Lean and Fat Mass Ratio stats - 07/05/2018
        String leanFatQuery = "SELECT ratio FROM Leanfatmass WHERE date = '7/5/2018';";
        ResultSet leanFatResult = st.executeQuery(leanFatQuery);
        bLeanFat.setText(leanFatResult.getString(1));
        
        //Latest dates for each stat
        String heightDateQuery = "SELECT Max(date) FROM BMI;";
        ResultSet heightDateResult = st.executeQuery(heightDateQuery);
        bHeightDate.setText(heightDateResult.getString(1));
        
        String weightDateQuery = "SELECT Max(date) FROM BMI;";
        ResultSet weightDateResult = st.executeQuery(weightDateQuery);
        bWeightDate.setText(weightDateResult.getString(1));
        
        String bmiDateQuery = "SELECT Max(date) FROM BMI;";
        ResultSet bmiDateResult = st.executeQuery(bmiDateQuery);
        bBMIDate.setText(bmiDateResult.getString(1));
        
        String leanDateQuery = "SELECT Max(date) FROM Leanfatmass;";
        ResultSet leanDateResult = st.executeQuery(leanDateQuery);
        bLeanDate.setText(leanDateResult.getString(1));
        
        st.close();
        conn.close();
    }
    
    //Load bmi line chart with database
    public void loadBmiChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, BMI FROM BMI WHERE date >= '29/4/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            bmiChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load lean fat mass ratio line chart with database 
    public void loadLeanFatChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, ratio FROM Leanfatmass WHERE date >= '29/4/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        
        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            leanFatChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

}
