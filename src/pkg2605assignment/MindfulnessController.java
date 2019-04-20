/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author elizabethlin
 */
public class MindfulnessController implements Initializable {

    @FXML
    private BarChart<String, Double> mindfulChart;
    
    @FXML
    private Label mindfulGoalText;

    @FXML
    private TextField sysInMindfulGoal;

    @FXML
    private ProgressBar mindfulProgress;

    @FXML
    private Label mindfulStatus;
    
    @FXML
    private Label errormessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadMindfulChart();
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(MindfulnessController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Load mindfullness bar chart with database
    public void loadMindfulChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, mindfulminutes from Mentalwellbeing WHERE date >= '6/5/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            mindfulChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    public void connect() throws SQLException {
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        System.out.println("DataBase connected");

        //create statement	
        Statement st = conn.createStatement();

        //User name
        String nameQuery = "SELECT firstname FROM User;";
        ResultSet nameResult = st.executeQuery(nameQuery);
        mindfulGoalText.setText(nameResult.getString(1) + "'s Mindfulness Minutes Goal: ");

        //Mindfull Goal Progress Bar
        String mindfulQuery = "SELECT mindfulminutes FROM Mentalwellbeing WHERE date = '12/5/2018';";
        ResultSet mindfulResult = st.executeQuery(mindfulQuery);
        double storeMindfulResult = mindfulResult.getDouble(1);

        String goalMindQuery = "SELECT mindfulgoal FROM Goal;";
        ResultSet goalMindResult = st.executeQuery(goalMindQuery);
        double storeGoalMindResult = goalMindResult.getDouble(1);

        mindfulStatus.setText((int) storeMindfulResult + " out of " + (int) storeGoalMindResult + " minutes completed");
        mindfulProgress.setProgress((storeMindfulResult / storeGoalMindResult));
        mindfulProgress.setStyle("-fx-accent: #FF3B30;");

        st.close();
        conn.close();
    }

    @FXML
        private void handleButtonAction(ActionEvent event) throws SQLException {
        String setMind = sysInMindfulGoal.getText();
        
        char ch = setMind.charAt(0);
        int ascii = (int) ch;
        
        //error
        if (ascii > 31 && (ascii < 48 || ascii > 57)) {
            
            errormessage.setText("Error! Please enter numbers only!");
        }
        
        else{
        double parseMind = Double.parseDouble(setMind);
        
        errormessage.setText("");
       
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        
        
        
        

        //prepared statement	
        String preparedSt = "UPDATE Goal SET mindfulgoal = '" + parseMind + "';";
        PreparedStatement pst = conn.prepareStatement(preparedSt);
        pst.executeUpdate();

        connect();
        }
    }

}
