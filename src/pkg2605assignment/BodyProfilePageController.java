/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class BodyProfilePageController implements Initializable {

    //@FXML
    //private Label label;
    @FXML
    private LineChart<String, Double> bmiLineChart;
    @FXML
    private CategoryAxis bmiX;
//    @FXML
//    private NumberAxis bmiY;
//    @FXML
//    private Button bmiLoad;
//    private Connection dbConnect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bmiChart();
        } catch (SQLException ex) {
            Logger.getLogger(BodyProfilePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bmiChart() throws SQLException {
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement
        Statement st = conn.createStatement();

        //SQL query to select relevant columns
        String selectQuery = "SELECT date, BMI FROM BMI;";

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        try {
            ResultSet rs = st.executeQuery(selectQuery);
            //System.out.println(rs.getString(1)); 
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                System.out.println(rs.getString(1));
                System.out.println(rs.getDouble(2));

            }
            bmiLineChart.getData().add(series);
            //System.out.println("series added");
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }


/* Youtube Tutorial Way
    private void bmiLoadChart() throws SQLException{
        String selectQuery = "SELECT BMI, date FROM BMI";
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try{
            dbConnect = connection();
            ResultSet rs = dbConnect.createStatement().executeQuery(selectQuery);
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
                
            }
            bmiLineChart.getData().add(series);
        } catch (Exception e){
            
        }
    }
    
    
    
    //Database connection
    private Connection connection() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        return null;
    }
*/
}