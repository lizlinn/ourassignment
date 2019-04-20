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
        Height.setText(heightResult.getString(1) + " m");
        
        //Weight stats - 07/05/2018
        String weightQuery = "SELECT weight FROM BMI WHERE date = '7/5/2018';";
        ResultSet weightResult = st.executeQuery(weightQuery);
        Weight.setText(weightResult.getString(1) + " kg");
        
        //Age stats - 07/05/2018
        String bmiQuery = "SELECT age FROM User;";
        ResultSet bmiResult = st.executeQuery(bmiQuery);
        Age.setText(bmiResult.getString(1) + " years");
        
        //GP Date
        String gpQuery = "SELECT Max(date) FROM Medical WHERE type = 'GP';";
        ResultSet gpResult = st.executeQuery(gpQuery);
        gpDate.setText(gpResult.getString(1));
        
        //Optom Date
        String optomQuery = "SELECT Max(date) FROM Medical WHERE type = 'Optometrist';";
        ResultSet optomResult = st.executeQuery(optomQuery);
        optomDate.setText(optomResult.getString(1));
        
        //Dentist Date
        String dentistQuery = "SELECT Max(date) FROM Medical WHERE type = 'Dentist';";
        ResultSet dentistResult = st.executeQuery(dentistQuery);
        dentistDate.setText(dentistResult.getString(1));
        
        //Other Date
        String otherQuery = "SELECT Max(date) FROM Medical WHERE type = 'Other';";
        ResultSet otherResult = st.executeQuery(otherQuery);
        otherDate.setText(otherResult.getString(1));
        
        //Today's date
        String dateQuery = "SELECT Max(date) FROM Sleep;";
        ResultSet dateResult = st.executeQuery(dateQuery);
        date.setText(dateResult.getString(1));
        
        st.close();
        conn.close();
    }  
    
   //Load walking/running (aerobics) bar chart with database
    public void loadAerobicChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, km from Aerobic WHERE date >= '12/4/2018';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                //System.out.println(rs.getString(1));
                //System.out.println(rs.getDouble(2));

            }
            walkRunChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
    
    //Load gym attendance bar chart with database
    public void loadGymChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, attendance from Gym WHERE date >= '12/4/2018';";

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
    
    //Load gym attendance bar chart with database
    public void loadStairsChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, flightsclimbed from Stairs WHERE date >= '12/4/2018';";

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

    //Load sleep line chart with database 
    public void loadSleepChart() throws SQLException {
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, hoursasleep FROM Sleep WHERE date >= '12/4/2018';";

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
