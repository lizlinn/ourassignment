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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardHolderPane;
    @FXML
    private Label welcome;
    @FXML
    private Label restingHeartRate;
    @FXML
    private Label calories;
    @FXML
    private Label protein;
    @FXML
    private Label caloriesBurned;
    @FXML
    private Label lastMedicalCheck;
    @FXML
    private Button btnActivity;
    @FXML
    private Button btnNutrition;
    @FXML
    private Button btnMindfulness;
    @FXML
    private Button btnSleep;
    
    public static String storeNameResult;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            name();
            heart();
            nutrition();
            calories();
            medical();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getName() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        Statement st = conn.createStatement();
        String getNameQuery = "SELECT loggeduser FROM Login;";
        
        ResultSet getNameResult = st.executeQuery(getNameQuery);
        storeNameResult = getNameResult.getString(1);
        
        st.close();
        conn.close();
        
        return storeNameResult;
    }
    
    public void name() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //Welcome NAME
        Statement namest = conn.createStatement();
        
        String nameQuery = "SELECT firstname FROM User WHERE username = '" + DashboardController.getName().toString() + "';";

        try {
            ResultSet nameResult = namest.executeQuery(nameQuery);
            while (nameResult.next()) {
                welcome.setText("Welcome " + nameResult.getString(1) + ",");
            }
        } catch (Exception e) {
        }

        namest.close();
        conn.close();
    }

    public void heart() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //Resting Heart Rate stats - 12/05/2018
        Statement heartst = conn.createStatement();
        String heartRateQuery = "SELECT averagecount FROM Restingheartrate WHERE date = '07/05/18';";

        try {
            ResultSet heartRateResult = heartst.executeQuery(heartRateQuery);

            while (heartRateResult.next()) {
                restingHeartRate.setText(heartRateResult.getString(1) + " BPM");
            }
        } catch (Exception e) {
        }

        heartst.close();
        conn.close();
    }

    public void nutrition() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //Nutrition stats - 12/05/2018
        Statement nutritionst = conn.createStatement();
        String nutritionQuery = "SELECT protein, calories FROM Food WHERE date = '07/05/18';";

        try {
            ResultSet nutritionResult = nutritionst.executeQuery(nutritionQuery);

            while (nutritionResult.next()) {
                calories.setText("Calories " + nutritionResult.getString(2));
                protein.setText("Protein " + nutritionResult.getString(1) + "g");
            }
        } catch (Exception e) {
        }

        nutritionst.close();
        conn.close();
    }

    public void calories() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //Calories Burned stats - 12/05/2018
        Statement caloriesst = conn.createStatement();
        String caloriesBurnedQuery = "SELECT calories FROM Resistance WHERE date = '07/05/18';";

        try {
            ResultSet caloriesBurnedResult = caloriesst.executeQuery(caloriesBurnedQuery);

            while (caloriesBurnedResult.next()) {
                caloriesBurned.setText(caloriesBurnedResult.getString(1) + " cal");
            }
        } catch (Exception e) {
        }

        caloriesst.close();
        conn.close();
    }

    public void medical() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //Last Medical Check stats - 12/05/2018
        Statement medicalst = conn.createStatement();
        String medicalCheckQuery = "SELECT date FROM Medical WHERE time > 1;";

        try {
            ResultSet medicalCheckResult = medicalst.executeQuery(medicalCheckQuery);

            while (medicalCheckResult.next()) {
                lastMedicalCheck.setText(medicalCheckResult.getString(1));
            }
        } catch (Exception e) {
        }

        medicalst.close();
        conn.close();
    }

    @FXML
    private void switchActivity(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Activity.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
        btnActivity.setStyle("-fx-background-color: #E0BAB9");
        btnNutrition.setStyle("-fx-background-color: #F8E5E2");
        btnMindfulness.setStyle("-fx-background-color: #F8E5E2");
        btnSleep.setStyle("-fx-background-color: #F8E5E2");
    }

    @FXML
    private void switchNutrition(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Nutrition.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
        btnActivity.setStyle("-fx-background-color: #F8E5E2");
        btnNutrition.setStyle("-fx-background-color: #E0BAB9");
        btnMindfulness.setStyle("-fx-background-color: #F8E5E2");
        btnSleep.setStyle("-fx-background-color: #F8E5E2");
    }

    @FXML
    private void switchMindfulness(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Mindfulness.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
        btnActivity.setStyle("-fx-background-color: #F8E5E2");
        btnNutrition.setStyle("-fx-background-color: #F8E5E2");
        btnMindfulness.setStyle("-fx-background-color: #E0BAB9");
        btnSleep.setStyle("-fx-background-color: #F8E5E2");
    }

    @FXML
    private void switchSleep(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Sleep.fxml"));
        dashboardHolderPane.getChildren().setAll(pane);
        btnActivity.setStyle("-fx-background-color: #F8E5E2");
        btnNutrition.setStyle("-fx-background-color: #F8E5E2");
        btnMindfulness.setStyle("-fx-background-color: #F8E5E2");
        btnSleep.setStyle("-fx-background-color: #E0BAB9");
    }

}
