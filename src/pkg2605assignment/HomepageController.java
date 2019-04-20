
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomepageController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnBodyProfile;
    @FXML
    private Button btnHealthRecords;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnNeedHelp;
    @FXML
    private ImageView dashboardIcon;
    @FXML
    private Button hbtnActivity;
    @FXML
    private AnchorPane hdashboardHolderPane;
    @FXML
    private Button hbtnNutrition;
    @FXML
    private Button hbtnMindfulness;
    @FXML
    private Button hbtnSleep;
    @FXML
    private Label hwelcome;
    @FXML
    private Label hRestingHeartRate;
    @FXML
    private Label hCalories;
    @FXML
    private Label hProtein;
    @FXML
    private Label hCaloriesBurned;
    @FXML
    private Label hLastMedicalCheck;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
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
        hwelcome.setText("Welcome " + nameResult.getString(1) + ",");
        
        //Resting Heart Rate stats - 12/05/2018
        String heartRateQuery = "SELECT averagecount FROM Restingheartrate WHERE date = '7/5/2018';";
        ResultSet heartRateResult = st.executeQuery(heartRateQuery);
        hRestingHeartRate.setText(heartRateResult.getString(1) + " BPM");
        
        //Nutrition stats - 12/05/2018
        String nutritionQuery = "SELECT protein, calories FROM Food WHERE date = '7/5/2018';";
        ResultSet nutritionResult = st.executeQuery(nutritionQuery);
        hCalories.setText("Calories " + nutritionResult.getString(2));
        hProtein.setText("Protein " + nutritionResult.getString(1) + "g");
        
        //Calories Burned stats - 12/05/2018
        String caloriesBurnedQuery = "SELECT calories FROM Resistance WHERE date = '7/5/2018';";
        ResultSet caloriesBurnedResult = st.executeQuery(caloriesBurnedQuery);
        hCaloriesBurned.setText(caloriesBurnedResult.getString(1) + " cal");
        
        //Last Medical Check stats - 12/05/2018
        String medicalCheckQuery = "SELECT date FROM Medical WHERE time > 1;";
        ResultSet medicalCheckResult = st.executeQuery(medicalCheckQuery);
        hLastMedicalCheck.setText(medicalCheckResult.getString(1));
        
        st.close();
        conn.close();
    }
    
     @FXML
    private void switchDashboard(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        holderPane.getChildren().setAll(pane); 
    }

    @FXML
    private void switchBodyProfile(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BodyProfilePage.fxml"));
        holderPane.getChildren().setAll(pane);
    }

    @FXML
    private void switchHealthRecords(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HealthRecordsPage.fxml"));
        holderPane.getChildren().setAll(pane);
    }

    @FXML
    private void switchSettings(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SettingsPage2.fxml"));
        holderPane.getChildren().setAll(pane);
    }

    @FXML
    private void switchNeedHelp(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("NeedHelpPage.fxml"));
        holderPane.getChildren().setAll(pane);
    }

    @FXML
    private void hswitchActivity(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Activity.fxml"));
        hdashboardHolderPane.getChildren().setAll(pane);
    }

    @FXML
    private void hswitchNutrition(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Nutrition.fxml"));
        hdashboardHolderPane.getChildren().setAll(pane);
    }

    @FXML
    private void hswitchMindfulness(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Mindfulness.fxml"));
        hdashboardHolderPane.getChildren().setAll(pane);
    }

    @FXML
    private void hswitchSleep(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Sleep.fxml"));
        hdashboardHolderPane.getChildren().setAll(pane);
    }
    

   
    
}
