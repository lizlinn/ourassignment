package pkg2605assignment;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class ActivityController implements Initializable {

    @FXML
    private BarChart<String, Double> walkRunChart;
    @FXML
    private BarChart<String, Double> stepsChart;
    @FXML
    private TextField sysInStepGoal;
    @FXML
    private ProgressBar stepProgress;
    @FXML
    private Label stepStatus;
    @FXML
    private Label stepGoalText;
    @FXML
    private Label errormessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadAerobicChart();
            loadStepsChart();
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    //Load default (month) walking/running (aerobics) bar chart with database
    public void loadAerobicChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, km from Aerobic "
                + "WHERE substr(date,4,2) = '05';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            walkRunChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load Year filter for aerobic chart
    @FXML
     public void buttonAerobicYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, km from Aerobic "
                + "WHERE date LIKE '%18';";
        
        walkRunChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            walkRunChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Month filter for aerobic chart
    @FXML
     public void buttonAerobicMonth() throws SQLException {
        //walkRunChart.getData().clear();
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, km from Aerobic "
                + "WHERE substr(date,4,2) = '05';";
        
        
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        walkRunChart.getData().clear();
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            walkRunChart.getData().clear();
            walkRunChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Week filter for aerobic chart
    @FXML
     public void buttonAerobicWeek() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, km from Aerobic "
                + "WHERE date = '06/05/18' OR date = '07/05/18';";
        
        walkRunChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            walkRunChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    //Load default (month) steps bar chart with database 
    public void loadStepsChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, steptotal FROM Steps "
                + "WHERE substr(date,4,2) = '05';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stepsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load year filter steps bar chart with database 
    @FXML
    public void buttonStepsYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, steptotal FROM Steps "
                + "WHERE date LIKE '%18';";
        
        stepsChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stepsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load month filter steps bar chart with database 
    @FXML
    public void buttonStepsMonth() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, steptotal FROM Steps "
                + "WHERE substr(date,4,2) = '05';";
        
        stepsChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stepsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
     //Load week filter steps bar chart with database 
    @FXML
    public void buttonStepsWeek() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, steptotal FROM Steps "
                + "WHERE date = '06/05/18' OR date = '07/05/18';";
        
        stepsChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stepsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    public void connect() throws SQLException {
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        System.out.println("Database connected for dashboard activity");

        //create statement	
        Statement st = conn.createStatement();

        //User name
        String nameQuery = "SELECT firstname FROM User;";
        ResultSet nameResult = st.executeQuery(nameQuery);
        stepGoalText.setText(nameResult.getString(1) + "'s Step Goal: ");

        //Step Goal Progress Bar
        String stepQuery = "SELECT steptotal FROM Steps WHERE date = '07/05/18';";
        ResultSet stepResult = st.executeQuery(stepQuery);
        double storeStepResult = stepResult.getDouble(1);

        String goalQuery = "SELECT stepgoal FROM Goal;";
        ResultSet goalResult = st.executeQuery(goalQuery);
        double storeGoalResult = goalResult.getDouble(1);

        stepStatus.setText((int) storeStepResult + " out of " + (int) storeGoalResult + " steps completed");
        stepProgress.setProgress((storeStepResult / storeGoalResult));

        //Colour ratings
        if (storeStepResult / storeGoalResult < 0.5) {
            stepProgress.setStyle("-fx-accent: #FF3B30;");
        } else if ((storeStepResult / storeGoalResult > 0.5) && (storeStepResult / storeGoalResult < 1.0)) {
            stepProgress.setStyle("-fx-accent: #FF9933;");
        } else {
            stepProgress.setStyle("-fx-accent: #4D9900;");
        }

        sysInStepGoal.setText(String.valueOf((int) goalResult.getDouble(1)));

        st.close();
        conn.close();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        String setStep = sysInStepGoal.getText();

        char ch = setStep.charAt(0);
        int ascii = (int) ch;

        //error
        if (ascii > 31 && (ascii < 48 || ascii > 57)) {

            errormessage.setText("Error! Please enter numbers only!");
        } else {
            double parseStep = Double.parseDouble(setStep);
            errormessage.setText("");

            //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

            //prepared statement	
            String preparedSt = "UPDATE Goal SET stepgoal = '" + parseStep + "';";
            PreparedStatement pst = conn.prepareStatement(preparedSt);
            pst.executeUpdate();

            connect();
            conn.close();
        }
    }
}
