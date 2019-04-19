/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label welcome;
    @FXML
    private Label restingHeartRate;
    @FXML
    private Label calories;
    @FXML
    private Label protein;
    @FXML
    private Label caloriesBurned;
    @FXML
    private Label lastMedicalCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
        welcome.setText("Welcome " + nameResult.getString(1) + ",");
        
        //Resting Heart Rate stats - 12/05/2018
        String heartRateQuery = "SELECT averagecount FROM Restingheartrate WHERE date = '12/5/2018';";
        ResultSet heartRateResult = st.executeQuery(heartRateQuery);
        System.out.println(heartRateResult);
        restingHeartRate.setText(heartRateResult.getString(1) + " BPM");
        
        //Nutrition stats - 12/05/2018
        String nutritionQuery = "SELECT protein, calories FROM Food WHERE date = '12/5/2018';";
        ResultSet nutritionResult = st.executeQuery(nutritionQuery);
        System.out.println(nutritionResult);
        calories.setText("Calories " + nutritionResult.getString(2));
        protein.setText("Protein " + nutritionResult.getString(1) + "g");
        
        //Calories Burned stats - 12/05/2018
        String caloriesBurnedQuery = "SELECT calories FROM Resistance WHERE date = '12/5/2018';";
        ResultSet caloriesBurnedResult = st.executeQuery(caloriesBurnedQuery);
        System.out.println(caloriesBurnedResult);
        caloriesBurned.setText(caloriesBurnedResult.getString(1) + " cal");
        
        //Last Medical Check stats - 12/05/2018
        String medicalCheckQuery = "SELECT date FROM Medical WHERE time > 1;";
        ResultSet medicalCheckResult = st.executeQuery(medicalCheckQuery);
        System.out.println(medicalCheckResult);
        lastMedicalCheck.setText(medicalCheckResult.getString(1));

        st.close();
        conn.close();
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
