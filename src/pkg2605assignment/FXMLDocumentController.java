package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

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
    //@FXML
    //private Button btnSignOut;
    @FXML
    private Button btnNeedHelp;
    
    AnchorPane dashboard, bodyProfile, healthRecords, settings, needHelp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {
             dashboard = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
             bodyProfile = FXMLLoader.load(getClass().getResource("BodyProfilePage.fxml"));
             healthRecords = FXMLLoader.load(getClass().getResource("HealthRecordsPage.fxml"));
             settings = FXMLLoader.load(getClass().getResource("SettingsPage2.fxml"));
             //signOut = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
             needHelp = FXMLLoader.load(getClass().getResource("NeedHelpPage.fxml"));
            setNode(dashboard);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void switchDashboard(ActionEvent event) {
        setNode(dashboard);
    }

    @FXML
    private void switchBodyProfile(ActionEvent event) {
        setNode(bodyProfile);
    }

    @FXML
    private void switchHealthRecords(ActionEvent event) {
        setNode(healthRecords);
    }

    @FXML
    private void switchSettings(ActionEvent event) {
        setNode(settings);
    }

    //@FXML
    //private void switchSignOut(ActionEvent event) {
      //  setNode(signOut);
    //}

    @FXML
    private void switchNeedHelp(ActionEvent event) {
        setNode(needHelp);
    }

}