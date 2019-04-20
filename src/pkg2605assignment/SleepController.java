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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author t-genest
 */
public class SleepController implements Initializable {

@FXML
private LineChart<String, Double> sleepChart;

@FXML
    private Label sleepGoalText;

    @FXML
    private TextField sysInSleepGoal;

    @FXML
    private ProgressBar sleepProgress;

    @FXML
    private Label sleepStatus;

    @FXML
    private Label errormessage;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        loadSleepChart();
        connect();
    } catch (SQLException ex) {
        Logger.getLogger(SleepController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
    
    //Load sleep line chart with database 
    public void loadSleepChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, hoursasleep FROM Sleep WHERE date >= '1/4/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        
        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            sleepChart.getData().add(series);
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
       sleepGoalText.setText(nameResult.getString(1) + "'s Sleep Goal (hours): ");

        //Mindfull Goal Progress Bar
        String sleepQuery = "SELECT hoursasleep FROM Sleep WHERE date = '7/5/2018';";
        ResultSet sleepResult = st.executeQuery(sleepQuery);
        double storeSleepResult = sleepResult.getDouble(1);

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
    void handleButtonAction(ActionEvent event) throws SQLException {
        
        

    }
}
