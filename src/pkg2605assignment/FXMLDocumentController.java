package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label result;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private Label welcome1;
    
    public String storeUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void signIn(MouseEvent event) throws SQLException, IOException {
        String user, pass;

        user = username.getText();
        pass = password.getText();
        
        this.storeUser = user;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        Statement st = conn.createStatement();
        String loginQuery = "SELECT * FROM User WHERE username = '" + user + "' and password = '" + pass + "';";
       
        try {
            ResultSet rs = st.executeQuery(loginQuery);
            if (rs.next()) {
                result.setText("");
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                holderPane.getChildren().setAll(pane);

            } else {
                result.setText("Your Sign On details are incorrect. Please check your details and try again.");

            }
        } catch (Exception e) {
            result.setText("Your Sign On details are incorrect. Please check your details and try again.");
        }
        
        st.close();
        conn.close();
    }

    @FXML
    private void clickSignUp(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        holderPane.getChildren().setAll(pane);
    }
}
