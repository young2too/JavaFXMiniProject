package Login;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Application;

public class LoginMain extends Application
{
    public void start(final Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        final Parent root = (Parent)loader.load();
        final Scene scene = new Scene(root);
        final Controller ctrler = (Controller)loader.getController();
        ctrler.setRoot(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
}