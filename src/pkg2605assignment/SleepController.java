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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

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

    //Load default (month) sleep line chart with database 
    public void loadSleepChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, hoursasleep FROM Sleep "
                + "WHERE substr(date,4,2) = '05';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate Chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            sleepChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load Year filter for sleep chart
    @FXML
     public void buttonSleepYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, hoursasleep FROM Sleep "
                + "WHERE date LIKE '%18';";
        
        sleepChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            sleepChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Month filter for sleep chart
    @FXML
     public void buttonSleepMonth() throws SQLException {
        //walkRunChart.getData().clear();
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, hoursasleep FROM Sleep "
                + "WHERE substr(date,4,2) = '05';";
        
        
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        sleepChart.getData().clear();
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            sleepChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Week filter for sleep chart 
    @FXML
     public void buttonSleepWeek() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, hoursasleep FROM Sleep "
                + "WHERE date = '06/05/18' OR date = '07/05/18';";
        
        sleepChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
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
        String nameQuery = "SELECT firstname FROM User WHERE username = '" + DashboardController.getName().toString() + "';";
        ResultSet nameResult = st.executeQuery(nameQuery);
        sleepGoalText.setText(nameResult.getString(1) + "'s Sleep Goal (hours): ");

        //Mindfull Goal Progress Bar
        String sleepQuery = "SELECT hoursasleep FROM Sleep WHERE date = '07/05/18';";
        ResultSet sleepResult = st.executeQuery(sleepQuery);
        double storeSleepResult = sleepResult.getDouble(1);

        String sleepMindQuery = "SELECT sleepgoal FROM Goal;";
        ResultSet sleepMindResult = st.executeQuery(sleepMindQuery);
        double storeGoalSleepResult = sleepMindResult.getDouble(1);

        sleepStatus.setText((int) storeSleepResult + " out of " + (int) storeGoalSleepResult + " hours");
        sleepProgress.setProgress((storeSleepResult / storeGoalSleepResult));
        
        //Colour ratings
        if (storeSleepResult / storeGoalSleepResult < 0.5) {
            sleepProgress.setStyle("-fx-accent: #FF3B30;");
        } else if ((storeSleepResult / storeGoalSleepResult > 0.5) && (storeSleepResult / storeGoalSleepResult < 1.0)) {
            sleepProgress.setStyle("-fx-accent: #FF9933;");
        } else {
            sleepProgress.setStyle("-fx-accent: #4D9900;");
        }
       
        st.close();
        conn.close();
    }

    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {

        String setSleep = sysInSleepGoal.getText();

        char ch = setSleep.charAt(0);
        int ascii = (int) ch;

        //error
        if (ascii > 31 && (ascii < 48 || ascii > 57)) {

            errormessage.setText("Error! Please enter numbers only!");
        } else {
            double parseSleep = Double.parseDouble(setSleep);

            errormessage.setText("");

            //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

            //prepared statement	
            String preparedSt = "UPDATE Goal SET sleepgoal = '" + parseSleep + "';";
            PreparedStatement pst = conn.prepareStatement(preparedSt);
            pst.executeUpdate();

            connect();
            conn.close();
        }

    }
}
