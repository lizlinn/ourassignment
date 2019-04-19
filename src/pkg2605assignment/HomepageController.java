
package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    

   
    
}
