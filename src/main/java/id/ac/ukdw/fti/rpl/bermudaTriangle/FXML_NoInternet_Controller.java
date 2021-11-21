package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class FXML_NoInternet_Controller implements Initializable{

    
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem BtnGoToSearch;

    @FXML
    private MenuItem Verse;

    @FXML
    private Text objectName;

    @FXML
    private Button btnToOffline;

    @FXML
    void goToOfflineMode(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        Parent searchWindow = FXMLLoader.load(getClass().getResource("verseWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(bounds.getWidth());
        app_stage.setHeight(bounds.getHeight());
        app_stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
