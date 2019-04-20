
package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomepageController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            holderPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
