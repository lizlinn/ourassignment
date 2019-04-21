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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SignUpPageController implements Initializable {

    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtlname;
    @FXML
    private PasswordField txtpword;
    @FXML
    private PasswordField txtconfirmpword;
    @FXML
    private TextField txtheight;
    @FXML
    private ComboBox genderComboBox;
    @FXML
    private TextField txtage;
    @FXML
    private Label result;
    @FXML
    private AnchorPane holderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //These items are for configuring the ComboBox
        //genderComboBox.setItems(genderList);

        genderComboBox.getItems().clear();

        genderComboBox.getItems().addAll(
                "Female",
                "Male",
                "Other",
                "Prefer not to say");
    }

    @FXML
    private void signUp(MouseEvent event) throws SQLException, IOException {
        String fname, lname, email, height, age, password, confirmPassword;
        String gender = new String();

        fname = txtfname.getText();
        lname = txtlname.getText();
        email = txtemail.getText();
        height = txtheight.getText();
        age = txtage.getText();
        boolean genderEmpty = (genderComboBox.getValue() == null);
        password = txtpword.getText();
        confirmPassword = txtconfirmpword.getText();

        System.out.println(gender);
        //check all fields are filled
        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || height.isEmpty() || age.isEmpty() || genderEmpty || password.isEmpty() || confirmPassword.isEmpty()) {
            result.setText("Please fill in all fields to proceed.");
        } else {
            //inserted here to prevent NulInvocation error in output as it will point to a null value since nothing is selected initially
            gender = genderComboBox.getSelectionModel().getSelectedItem().toString();

            //check password is same to confirm password
            if (!password.equals(confirmPassword)) {
                result.setText("Password does not match Confirm Password.");
            } else {
                //parse height and age to int
                int intHeight = Integer.parseInt(height);
                int intAge = Integer.parseInt(age);

                Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

                Statement st = conn.createStatement();

                //check for existing data (primary key = email)
                String userQuery = "SELECT username FROM User WHERE username = '" + email + "';";

                try {
                    ResultSet rs = st.executeQuery(userQuery);
                    if (rs.next()) {
                        //checks username doesn't already exist (e-mail = username -> primary key for duplication)
                        result.setText("This e-mail already exists. Click the back button to log into your account.");
                    } else {
                        success();
                    }
                } catch (Exception e) {
                }
                st.close();
                conn.close();
            }

        }

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        holderPane.getChildren().setAll(pane);
    }

    public void success() throws SQLException {
        String fname, lname, email, height, age, password, confirmPassword;
        String gender = new String();

        gender = genderComboBox.getSelectionModel().getSelectedItem().toString();
        fname = txtfname.getText();
        lname = txtlname.getText();
        email = txtemail.getText();
        height = txtheight.getText();
        age = txtage.getText();
        int intHeight = Integer.parseInt(height);
        int intAge = Integer.parseInt(age);
        password = txtpword.getText();
        confirmPassword = txtconfirmpword.getText();

        Connection conn = DriverManager.getConnection("jdbc:sqlite:fitnessdata.db");

        Statement st2 = conn.createStatement();
        String signInQuery = "INSERT INTO User (firstname, lastname, height, username, password, age, gender) "
                + "VALUES ('" + fname + "', '" + lname + "', '" + intHeight + "', '" + email + "', '" + password + "', '" + intAge + "', '" + gender + "');";
        try {
            ResultSet rs2 = st2.executeQuery(signInQuery);
            while (rs2.next()) {

            }
        } catch (Exception e) {
        }
        result.setText("Successful! Click the back button to log into your account.");
        st2.close();
        conn.close();
    }
}
