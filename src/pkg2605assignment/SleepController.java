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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class SleepController implements Initializable {

@FXML
private LineChart<String, Double> sleepChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        loadSleepChart();
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
            }
            sleepChart.getData().add(series);
        } catch (Exception e) {

        }

        st.close();
        conn.close();
    }
}