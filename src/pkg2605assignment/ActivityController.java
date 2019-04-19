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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ActivityController implements Initializable {

    @FXML
    private AnchorPane dashboardHolderPane;
    @FXML
    private TextField sysInStepGoal;
    @FXML
    private ProgressBar stepProgress;
    @FXML
    private Label stepStatus;
    @FXML
    private Label stepGoalText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connect();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void connect() throws SQLException {
       //create connection
       Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
       System.out.println("DataBase connected");
        
        //create statement	
        Statement st = conn.createStatement();
        
        //User name
        String nameQuery = "SELECT firstname FROM User;";
        ResultSet nameResult = st.executeQuery(nameQuery);
        stepGoalText.setText(nameResult.getString(1) + "'s Step Goal: ");
        
        //Step Goal Progress Bar
        String stepQuery = "SELECT steptotal FROM Steps WHERE date = '12/5/2018';";
        ResultSet stepResult = st.executeQuery(stepQuery);
        double storeStepResult = stepResult.getDouble(1);
        
        String goalQuery = "SELECT stepgoal FROM Goal;";
        ResultSet goalResult = st.executeQuery(goalQuery);
        double storeGoalResult = goalResult.getDouble(1);
       
        stepStatus.setText((int) storeStepResult + " out of " + (int) storeGoalResult + " steps completed");
        stepProgress.setProgress((storeStepResult/storeGoalResult));
        stepProgress.setStyle("-fx-accent: #FF3B30;");
        
        st.close();
        conn.close();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        String setStep = sysInStepGoal.getText();
        double parseStep = Double.parseDouble(setStep);
        
        //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");
        
        //prepared statement	
        String preparedSt = "UPDATE Goal SET stepgoal = '"+parseStep+"';";
        PreparedStatement pst = conn.prepareStatement(preparedSt);
        pst.executeUpdate();
        
        connect();
    }
    
}
