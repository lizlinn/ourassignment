package pkg2605assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("MyFitness Heart Logo - Red (#FF3B30).png")));
       
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}
