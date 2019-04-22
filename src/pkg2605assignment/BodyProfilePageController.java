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
        String nameQuery = "SELECT firstname FROM User WHERE username = '" + DashboardController.getName().toString() + "';";
        ResultSet nameResult = st.executeQuery(nameQuery);
        bWelcome.setText(nameResult.getString(1) + "'s Body Profile");

        //Height stats 
        String heightQuery = "SELECT height FROM User;";
        ResultSet heightResult = st.executeQuery(heightQuery);
        bHeight.setText(heightResult.getString(1) + " cm");

        //Weight stats - 07/05/2018
        String weightQuery = "SELECT weight FROM BMI WHERE date = '07/05/18';";
        ResultSet weightResult = st.executeQuery(weightQuery);
        bWeight.setText(weightResult.getString(1) + " kg");

        //BMI stats - 07/05/2018
        String bmiQuery = "SELECT bmi FROM BMI WHERE date = '07/05/18';";
        ResultSet bmiResult = st.executeQuery(bmiQuery);
        bBMI.setText(bmiResult.getString(1));

        //Lean and Fat Mass Ratio stats - 07/05/2018
        String leanFatQuery = "SELECT ratio FROM Leanfatmass WHERE date = '07/05/18';";
        ResultSet leanFatResult = st.executeQuery(leanFatQuery);
        bLeanFat.setText(leanFatResult.getString(1));

        //Latest dates for each stat
        String heightDateQuery = "SELECT date FROM BMI WHERE date = '07/05/18';";
        ResultSet heightDateResult = st.executeQuery(heightDateQuery);
        bHeightDate.setText(heightDateResult.getString(1));

        String weightDateQuery = "SELECT date FROM BMI WHERE date = '07/05/18';";
        ResultSet weightDateResult = st.executeQuery(weightDateQuery);
        bWeightDate.setText(weightDateResult.getString(1));

        String bmiDateQuery = "SELECT date FROM BMI WHERE date = '07/05/18';";
        ResultSet bmiDateResult = st.executeQuery(bmiDateQuery);
        bBMIDate.setText(bmiDateResult.getString(1));

        String leanDateQuery = "SELECT date FROM Leanfatmass WHERE date = '07/05/18';";
        ResultSet leanDateResult = st.executeQuery(leanDateQuery);
        bLeanDate.setText(leanDateResult.getString(1));

        st.close();
        conn.close();
    }

    //Load default (month) bmi line chart with database
    public void loadBmiChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, BMI FROM BMI "
                + "WHERE substr(date,4,2) = '05';";

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

    //Load Year filter for bmi chart
    @FXML
    public void buttonBMIYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, BMI FROM BMI "
                + "WHERE date LIKE '%18';";

        bmiChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            bmiChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    //Load Month filter for bmi chart
    @FXML
    public void buttonBMIMonth() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, BMI FROM BMI "
                + "WHERE substr(date,4,2) = '05';";

        bmiChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            bmiChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    //Load Week filter for bmi chart 
    @FXML
    public void buttonBMIWeek() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, BMI FROM BMI "
                + "WHERE date = '06/05/18' OR date = '07/05/18';";

        bmiChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            bmiChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    //Load deafult (month) lean fat mass ratio line chart with database 
    public void loadLeanFatChart() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, ratio FROM Leanfatmass "
                + "WHERE substr(date,4,2) = '05';";

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

    //Load Year filter for lean chart
    @FXML
    public void buttonLeanYear() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, ratio FROM Leanfatmass "
                + "WHERE date LIKE '%18';";

        leanFatChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            leanFatChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    //Load Month filter for lean chart
    @FXML
    public void buttonLeanMonth() throws SQLException {
        //walkRunChart.getData().clear();
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, ratio FROM Leanfatmass "
                + "WHERE substr(date,4,2) = '05';";

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        leanFatChart.getData().clear();
        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            leanFatChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

    //Load Week filter for lean chart 
    @FXML
    public void buttonLeanWeek() throws SQLException {

        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT dayofweek, ratio FROM Leanfatmass "
                + "WHERE date = '06/05/18' OR date = '07/05/18';";

        leanFatChart.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        //Populate chart
        try {
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            leanFatChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }

}
