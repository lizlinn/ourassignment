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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

public class NutritionController implements Initializable {

    @FXML
    private AnchorPane dashboardHolderPane;
    @FXML
    private ProgressBar carbsBar;
    @FXML
    private ProgressBar proteinBar;
    @FXML
    private ProgressBar fatBar;
    @FXML
    private Label food;
    @FXML
    private Label exercise;
    @FXML
    private Label carbsStatus;
    @FXML
    private Label proteinStatus;
    @FXML
    private Label fatStatus;
    @FXML
    private ProgressIndicator ringProgressIndicator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(NutritionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    public void connect() throws SQLException {
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        //create statement	
        Statement st = conn.createStatement();
 
        //---PROGRESS BARS AND STATUS---
        double carbsGoal = 204.0;
        double proteinGoal = 82.0;
        double fatGoal = 54.0;
        
        String progressQuery = "SELECT protein, carbohydrates, fat, calories FROM Food WHERE date = '12/5/2018';";
        ResultSet progressResult = st.executeQuery(progressQuery);
        
        //Food status
        double storeFoodResult = progressResult.getDouble(4);
        String storeFoodResult2 = String.valueOf((int)storeFoodResult);
        food.setText(storeFoodResult2);
        
        //Carbs progress bar
        double storeCarbsResult = progressResult.getDouble(2);
        carbsStatus.setText(storeCarbsResult + " / " + carbsGoal + " g");
        carbsBar.setProgress((storeCarbsResult / carbsGoal));
        carbsBar.setStyle("-fx-accent: #F8E5E2;");
        
        //Protein progress bar
        double storeProteinResult = progressResult.getDouble(1);
        proteinStatus.setText(storeProteinResult + " / " + proteinGoal + " g");
        proteinBar.setProgress((storeProteinResult / proteinGoal));
        proteinBar.setStyle("-fx-accent: #F8E5E2;");

        //Fat progress bar
        double storeFatResult = progressResult.getDouble(3);
        fatStatus.setText(storeFatResult + " / " + fatGoal + " g");
        fatBar.setProgress((storeFatResult / fatGoal));
        fatBar.setStyle("-fx-accent: #F8E5E2;");

        //Exercise status
        String exerciseQuery = "SELECT calories FROM Resistance WHERE date = '12/5/2018';";
        ResultSet exerciseResult = st.executeQuery(exerciseQuery);
        double storeExerciseResult = exerciseResult.getDouble(1);
        String storeExerciseResult2 = String.valueOf((int)storeExerciseResult);
        exercise.setText(storeExerciseResult2);
        
        //Circle progress
        if(storeFoodResult < storeExerciseResult){
            ringProgressIndicator.setProgress(1);
        }
        else{
            ringProgressIndicator.setProgress((storeFoodResult - storeExerciseResult)/ storeFoodResult);
        }
        
        ringProgressIndicator.setStyle("-fx-accent: #FF3B30;");
        
        st.close();
        conn.close();
    } 
}