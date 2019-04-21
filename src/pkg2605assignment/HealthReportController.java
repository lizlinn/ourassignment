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
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class HealthReportController implements Initializable {
    @FXML
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
    @FXML
    private Label date;
    
    @FXML
    private BarChart<String, Double> walkRunChart;
    @FXML
    private BarChart<String, Double> gymChart;
    @FXML
    private BarChart<String, Double> stairsChart;
    @FXML
    private LineChart<String, Double> sleepChart;
    /*
    @FXML
    private TableView<medicalTable> medicalTable;
    @FXML
    private TableColumn<medicalTable, String> colDate;
    @FXML
    private TableColumn<medicalTable, String> colType;
    @FXML
    private TableColumn<medicalTable, String> colMeds;
    */
    
    //public ObservableList<medicalTable> oList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadStats();
            loadAerobicChart();
            loadGymChart();
            loadStairsChart();
            loadSleepChart();
        } catch (SQLException ex) {
            Logger.getLogger(HealthReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void loadStats() throws SQLException {
       //create connection
       Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
       //System.out.println("DataBase connected");
        
        //create statement	
        Statement st = conn.createStatement();
        
        //Welcome
        String welcomeQuery = "SELECT firstname FROM User;";
        ResultSet welcomeResult = st.executeQuery(welcomeQuery);
        Welcome.setText(welcomeResult.getString(1) + "'s Health Report");
        
        
        //NAME
        String nameQuery = "SELECT firstname, lastname FROM User;";
        ResultSet nameResult = st.executeQuery(nameQuery);
        Name.setText(nameResult.getString(1) + " " + nameResult.getString(2));
        
        //Height stats 
        String heightQuery = "SELECT height FROM User;";
        ResultSet heightResult = st.executeQuery(heightQuery);
        Height.setText(heightResult.getString(1) + " cm");
        
        //Weight stats - 07/05/2018
        String weightQuery = "SELECT weight FROM BMI WHERE date = '07/05/18';";
        ResultSet weightResult = st.executeQuery(weightQuery);
        Weight.setText(weightResult.getString(1) + " kg");
        
        //Age stats - 07/05/2018
        String bmiQuery = "SELECT age FROM User;";
        ResultSet bmiResult = st.executeQuery(bmiQuery);
        Age.setText(bmiResult.getString(1) + " years");
        
        //GP Date
        String gpQuery = "SELECT date FROM Medical WHERE type = 'GP';";
        ResultSet gpResult = st.executeQuery(gpQuery);
        gpDate.setText(gpResult.getString(1));
        
        //Optom Date
        String optomQuery = "SELECT date FROM Medical WHERE type = 'Optometrist';";
        ResultSet optomResult = st.executeQuery(optomQuery);
        optomDate.setText(optomResult.getString(1));
        
        //Dentist Date
        String dentistQuery = "SELECT date FROM Medical WHERE type = 'Dentist';";
        ResultSet dentistResult = st.executeQuery(dentistQuery);
        dentistDate.setText(dentistResult.getString(1));
        
        //Other Date
        String otherQuery = "SELECT date FROM Medical WHERE type = 'Other';";
        ResultSet otherResult = st.executeQuery(otherQuery);
        otherDate.setText(otherResult.getString(1));
        
        //Today's date
        String dateQuery = "SELECT date FROM Sleep WHERE date = '07/05/18';";
        ResultSet dateResult = st.executeQuery(dateQuery);
        date.setText(dateResult.getString(1));
        
        st.close();
        conn.close();
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
            //walkRunChart.getData().clear();
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

    
    //Load deafult (month) gym attendance bar chart with database
    public void loadGymChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, attendance from Gym "
                + "WHERE substr(date,4,2) = '05';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                //System.out.println(rs.getString(1));
                //System.out.println(rs.getDouble(2));

            }
            gymChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load Year filter for gym chart
    @FXML
     public void buttonGymYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, attendance from Gym "
                + "WHERE date LIKE '%18';";
        
        gymChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            gymChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Month filter for gym chart
    @FXML
     public void buttonGymMonth() throws SQLException {
        //walkRunChart.getData().clear();
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, attendance from Gym "
                + "WHERE substr(date,4,2) = '05';";
        
        
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        gymChart.getData().clear();
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            gymChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Week filter for gym chart
    @FXML
     public void buttonGymWeek() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, attendance from Gym "
                + "WHERE date = '06/05/18' OR date = '07/05/18';";
        
        gymChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            gymChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    
    //Load default (month) stairs bar chart with database
    public void loadStairsChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, flightsclimbed from Stairs "
                + "WHERE substr(date,4,2) = '05';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                //System.out.println(rs.getString(1));
                //System.out.println(rs.getDouble(2));

            }
            stairsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load Year filter for stairs chart
    @FXML
     public void buttonStairsYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, flightsclimbed from Stairs"
                + "WHERE date LIKE '%18';";
        
        stairsChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stairsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Month filter for stiars chart
    @FXML
     public void buttonStairsMonth() throws SQLException {
        //walkRunChart.getData().clear();
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, flightsclimbed from Stairs"
                + "WHERE substr(date,4,2) = '05';";
        
        
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        stairsChart.getData().clear();
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stairsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
     
    //Load Week filter for gym chart
    @FXML
    public void buttonStairsWeek() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, flightsclimbed from Stairs"
                + "WHERE date = '06/05/18' OR date = '07/05/18';";
        
        stairsChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            stairsChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
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
    
 /* Table view attempt   
    //Load table view with database
    public void loadMedicalTable() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        System.out.println("connected for table");
        //create statement
        Statement st = conn.createStatement();
     
        oList = FXCollections.observableArrayList();
        
        //SQL query to select relevant columns
        String selectQuery = "SELECT date, type, medication from Medical "
                + "WHERE type IS NOT NULL;";

        //Populate table
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                oList.add(new medicalTable(rs.getString("date"), rs.getString("type"),
                rs.getString("medication")));
                System.out.println(rs.getString("date"));
                System.out.println(rs.getString("type"));
                System.out.println(rs.getString("medication"));
                
            colDate.setCellFactory(new PropertyValueFactory("date"));
            colType.setCellFactory(new PropertyValueFactory("type"));
            colMeds.setCellFactory(new PropertyValueFactory("meds"));

            }

            
            medicalTable.setItems(oList);

        } catch (Exception e) {

        }
            

            
        st.close();
        conn.close();
    }
*/  
 
}
