package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    // timer
    Timer timer;

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        Parent root = FXMLLoader.load(getClass().getResource("noInternet.fxml"));

        
        if (this.checkInternet()) {
            root = FXMLLoader.load(getClass().getResource("detailObject.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Alpha en Omega");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        }else{
            Scene scene = new Scene(root);
            stage.setTitle("Alpha en Omega");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        }
        
    }

    public static void main(String[] args) {

        launch(args);
        
    }

    // Check internet is connect
    public static boolean checkInternet() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}