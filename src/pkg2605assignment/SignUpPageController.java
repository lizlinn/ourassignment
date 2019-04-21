/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2605assignment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author michellexu
 */
public class SignUpPageController implements Initializable {

    //List for ComboBox
    ObservableList<String> genderList = FXCollections.observableArrayList("Female",
            "Male", "Other", "Prefer not to say");

    //Gender ComboBox
    @FXML
    private ComboBox genderComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //These items are for configuring the ComboBox
        genderComboBox.setItems(genderList);

    }

    @FXML
    private void signIn(MouseEvent event) throws SQLException, IOException {
        String user, pass;

        user = username.getText();
        pass = password.getText();

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
    }

}
