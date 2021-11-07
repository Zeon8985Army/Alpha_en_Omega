package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Places;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Verse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_VerseWindow_Controller implements Initializable{
    private ObservableList<Verse> verses = FXCollections.observableArrayList();
    private ArrayList<String> listKitabArrayAll = new ArrayList<>();
    
    @FXML
    private AnchorPane searchAnchor;

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem BtnGoToSearch;
    @FXML
    private MenuItem BtnGoToVerse;
    @FXML
    private Text ayatLengkap;

    @FXML
    private TextArea textAyat;
    @FXML
    private ListView<String> listKitab;
    @FXML
    private ComboBox<String> listChapter;
    @FXML
    private ComboBox<String> listSection;
    @FXML
    private Text chapterSelected;
    @FXML
    private Text sectionSelected;
    @FXML
    private Text backLog;

    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.show();
    }
    @FXML
    void goToVerse(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("verseWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textAyat.setWrapText(true);
        verses = Database.instance.getAllVerse();
        ArrayList<String> listKitabArray = new ArrayList<>();
        for (Verse verse : verses) {
            if (listKitabArray.contains(verse.getBook())) {
                continue;
            } else {
                listKitab.getItems().add(verse.getBook());
                listKitabArray.add(verse.getBook());
            }
        }
        listKitabArrayAll = listKitabArray;
    }

    // Method untuk select"
    @FXML
    void selectKitab(MouseEvent event) {
        listChapter.getItems().clear();
        listSection.getItems().clear();

        chapterSelected.setText("");
        sectionSelected.setText("");

        String selectedKitab = listKitab.getSelectionModel().getSelectedItem();

        ArrayList<String> listChapterArray = new ArrayList<>();

        for (Verse verse : verses) {
            if (listChapterArray.contains(verse.getChapter())) {
                continue;
            }
            if (verse.getBook().equals(selectedKitab)) {
                listChapter.getItems().add(verse.getChapter());
                listChapterArray.add(verse.getChapter());
            }
        }
        chapterSelected.setText(selectedKitab+".1");
    }

    @FXML
    void selectChapter(ActionEvent event) {
        listSection.getItems().clear();
        chapterSelected.setText("");
        String selectedChapter = listChapter.getSelectionModel().getSelectedItem();

        sectionSelected.setText("1");
        ArrayList<String> listSectionArray = new ArrayList<>();
        ArrayList<String> verseInSection = new ArrayList<>();

        for (Verse verse : verses) {
            if (listSectionArray.contains(verse.getVerseNum())) {
                continue;
            }
            if (verse.getChapter().equals(selectedChapter)) {
                listSection.getItems().add(verse.getVerseNum());
                listSectionArray.add(verse.getVerseNum());
            }
        }
    }

    @FXML
    void selectSection(ActionEvent event) {
        sectionSelected.setText("");
        String selectedChapter = listChapter.getSelectionModel().getSelectedItem();
        String selectedSection = listSection.getSelectionModel().getSelectedItem();

        for (Verse verse : verses) {
            if (verse.getVerse().equals(selectedChapter + "." + selectedSection)) {
                textAyat.setText(verse.getVerseText());
                break;
            }
        }
        ayatLengkap.setText(selectedChapter + "." + selectedSection);
    }

}
