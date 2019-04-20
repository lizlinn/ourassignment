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
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        //Set icons on stage (side pane)
        stage.getIcons().add(new Image(getClass().getResourceAsStream("MyFitness Heart Logo - White (#FFFFFF).png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Dashboard.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Body Profile.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Medical Report.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Settings.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Sign out.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Need Help.png")));
        
        stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
