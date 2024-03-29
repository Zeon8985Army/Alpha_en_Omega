package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.Node;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Verse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
// untuk fullscrenn
import java.awt.*;

public class FXML_VerseWindow_Controller implements Initializable, Runnable {
    private ObservableList<Verse> verses = FXCollections.observableArrayList();
    private ArrayList<String> listKitabArrayAll = new ArrayList<>();

    @FXML
    private AnchorPane searchAnchor;

    // btn MenuBar
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem dashboard;
    @FXML
    private MenuItem BtnGoToSearch;
    @FXML
    private MenuItem BtnGoToVerse;
    @FXML
    private MenuItem BtnAboutUs;
    @FXML
    private MenuItem internetStatus;
    @FXML
    private Label labelInternetStatus;
    @FXML
    private TextArea textAyat;

    // btn listKItab
    @FXML
    private RadioButton btnNT;
    @FXML
    private RadioButton btnOT;
    @FXML
    private ListView<String> listKitab;
    @FXML
    private Button btnSortKitab;

    @FXML
    private Text ayatLengkap;

    // btn pasal dan ayat
    @FXML
    private ComboBox<String> listChapter;
    @FXML
    private ComboBox<String> listSection;
    @FXML
    private Text chapterSelected;
    @FXML
    private Text sectionSelected;

    @FXML
    private Slider slidder;

    // timer
    Timer timer;

    // untuk fullscrenn
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    // untuk menyimpan lokasi sebelumnya jika ada
    String nameObject="";
    String typeObject="";

    // untuk ke detail
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }

    @FXML
    void goToVerse(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("verseWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();


        if (!nameObject.equals("") || !typeObject.equals("")) {
            if (typeObject.equals("people")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detailObject.fxml"));
                root = loader.load();

                FXML_Home_Controller detailObject = loader.getController();
                detailObject.showDetail(nameObject, "people");

                stage = (Stage) menuBar.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(size.getWidth());
                stage.setHeight(size.getHeight());

                stage.show();
            } else if (typeObject.equals("place")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detailObject.fxml"));
                root = loader.load();

                FXML_Home_Controller detailObject = loader.getController();
                detailObject.showDetail(nameObject, "place");

                stage = (Stage) menuBar.getScene().getWindow();
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(size.getWidth());
                stage.setHeight(size.getHeight());
                stage.show();
            }
        }else{
            Parent searchWindow = FXMLLoader.load(getClass().getResource("detailObject.fxml"));
            Scene searchPage = new Scene(searchWindow);
            Stage app_stage = (Stage) menuBar.getScene().getWindow();
            app_stage.setScene(searchPage);
            app_stage.setX(bounds.getMinX());
            app_stage.setY(bounds.getMinY());
            app_stage.setWidth(size.getWidth());
            app_stage.setHeight(size.getHeight());
            app_stage.show();
        }
        
    }
    @FXML
    void goToAboutUs(ActionEvent event) throws IOException{
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Group
        ToggleGroup tastementGroup = new ToggleGroup();
        btnNT.setToggleGroup(tastementGroup);
        btnOT.setToggleGroup(tastementGroup);

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
        if (checkInternet()) {
            BtnGoToSearch.setDisable(false);
            dashboard.setDisable(false);
            onlineMethod();
        } else {
            BtnGoToSearch.setDisable(true);
            dashboard.setDisable(true);
            offlineMethod();
        }

        // check Internet dinamic gagal
        // try {
        // Timer timer = new Timer(true);
        // timer.schedule(new FXML_VerseWindow_Controller(), 0, 1000);
        // } catch (Exception e) {
        // System.out.println("Error ketangkap");
        // }

        // ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        // executor.scheduleAtFixedRate(new FXML_VerseWindow_Controller(), 0, 3,
        // TimeUnit.SECONDS);

    }

    // Method untuk select"
    @FXML
    void selectKitab(MouseEvent event) {
        ArrayList<String> verseInSection = new ArrayList<>();

        listChapter.getItems().clear();
        listSection.getItems().clear();

        chapterSelected.setText("");
        sectionSelected.setText("");

        String selectedKitab = listKitab.getSelectionModel().getSelectedItem();

        ArrayList<String> listChapterArray = new ArrayList<>();
        ArrayList<String> listSectionArray = new ArrayList<>();

        for (Verse verse : verses) {
            if (verse.getChapter().equals(selectedKitab + ".1")) {
                verseInSection.add(verse.getVerse() + "\n" + verse.getVerseText() + "\n\n");
            }
            if (listChapterArray.contains(verse.getChapter())) {
                continue;
            }
            if (verse.getBook().equals(selectedKitab)) {
                listChapter.getItems().add(verse.getChapter());
                listChapterArray.add(verse.getChapter());
            }

        }

        for (Verse verse : verses) {
            if (listSectionArray.contains(verse.getVerseNum())) {
                continue;
            }
            if (verse.getChapter().equals(selectedKitab + ".1")) {
                listSection.getItems().add(verse.getVerseNum());
                listSectionArray.add(verse.getVerseNum());
            }
        }
        listChapter.getSelectionModel().select(selectedKitab + ".1");
        ayatLengkap.setText(selectedKitab + "..." + "...");

        textAyat.setText(verseInSection.toString().replace("[", "").replace("]", "").replace(",", ""));
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
                verseInSection.add(verse.getVerse() + "\n" + verse.getVerseText() + "\n\n");
            }
        }
        ayatLengkap.setText(selectedChapter + "...");

        textAyat.setText(verseInSection.toString().replace("[", "").replace("]", "").replace(",", ""));
    }

    @FXML
    void selectSection(ActionEvent event) {
        sectionSelected.setText("");
        String selectedChapter = listChapter.getSelectionModel().getSelectedItem();
        String selectedSection = listSection.getSelectionModel().getSelectedItem();

        if (selectedChapter == null && selectedSection == null) {
            ayatLengkap.setText("Please choose the verse...");
        } else {
            ayatLengkap.setText(selectedChapter + "." + selectedSection);
            for (Verse verse : verses) {
                if (verse.getVerse().equals(selectedChapter + "." + selectedSection)) {
                    textAyat.setText(verse.getVerseText());
                    break;
                }
            }
        }
    }

    // method kitab"
    @FXML
    void ntKitabDisplay(ActionEvent event) {
        ArrayList<String> listKitabList = new ArrayList<>();
        int startIter = listKitabArrayAll.indexOf("Mal");

        for (int i = startIter + 1; i < listKitabArrayAll.size(); i++) {
            listKitabList.add(listKitabArrayAll.get(i));
        }

        listKitab.getItems().clear();
        listKitab.getItems().addAll(listKitabList);
        btnSortKitab.setText("⇵");
    }

    @FXML
    void otKitabDisplay(ActionEvent event) {
        ArrayList<String> listKitabList = new ArrayList<>();
        for (String kitab : listKitabArrayAll) {
            if (!kitab.equals("Mal")) {
                listKitabList.add(kitab);
            } else {
                listKitabList.add(kitab);
                break;
            }
        }
        listKitab.getItems().clear();
        listKitab.getItems().addAll(listKitabList);
        btnSortKitab.setText("⇵");
    }

    @FXML
    void sortKitab(ActionEvent event) {
        ArrayList<String> listKitabAList = new ArrayList<>();

        if (btnSortKitab.getText().equals("⇵") || btnSortKitab.getText().equals("↑")) {
            for (String kitab : listKitab.getItems()) {
                listKitabAList.add(kitab);
            }
            Collections.sort(listKitabAList);

            listKitab.getItems().clear();
            listKitab.getItems().addAll(listKitabAList);
            btnSortKitab.setText("↓");
        } else {
            for (String kitab : listKitab.getItems()) {
                listKitabAList.add(kitab);
            }
            Collections.reverse(listKitabAList);

            listKitab.getItems().clear();
            listKitab.getItems().addAll(listKitabAList);
            btnSortKitab.setText("↑");
        }
    }

    @FXML
    void checkSlider(MouseEvent event) {
        textAyat.setStyle("-fx-font-size: " + slidder.getValue());
        listKitab.setStyle("-fx-font-size: " + slidder.getValue());
        ayatLengkap.setStyle("-fx-font-size: " + slidder.getValue());
    }

    public void showDetailVerse(String ayat,String namaObjekPara, String typeObjekPara) {
        // pemberian nilai nama objek yang lama dan tipenya
        nameObject = namaObjekPara;
        typeObject = typeObjekPara;

        String[] arrayArat = ayat.split("\\.");
        listKitab.getSelectionModel().select(arrayArat[0]);

        chapterSelected.setText("");
        sectionSelected.setText("");

        ArrayList<String> listChapterArray = new ArrayList<>();

        for (Verse verse : verses) {
            if (listChapterArray.contains(verse.getChapter())) {
                continue;
            }
            if (verse.getBook().equals(arrayArat[0])) {
                listChapter.getItems().add(verse.getChapter());
                listChapterArray.add(verse.getChapter());
            }
        }

        listChapter.getSelectionModel().select(arrayArat[0] + "." + arrayArat[1]);

        ArrayList<String> listSectionArray = new ArrayList<>();

        for (Verse verse : verses) {
            if (listSectionArray.contains(verse.getVerseNum())) {
                continue;
            }
            if (verse.getChapter().equals(arrayArat[0] + "." + arrayArat[1])) {
                listSection.getItems().add(verse.getVerseNum());
                listSectionArray.add(verse.getVerseNum());
            }
        }

        listSection.getSelectionModel().select(arrayArat[2]);
        ayatLengkap.setText(arrayArat[0] + "." + arrayArat[1] + "." + arrayArat[2]);

        for (Verse verse : verses) {
            if (verse.getVerse().equals(ayat)) {
                textAyat.setText(verse.getVerseText());
                break;
            }
        }
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

    // looping method untuk pengecekan internet dengan timer yang ada di Main gagal
    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (!checkInternet()) {
            // onlineMethod();
            System.out.println("Test 2");
        } else {
            labelInternetStatus.setText("⚫ Online");
            System.out.println("Test Online");
            // offlineMethod();
        }
    }

    private void onlineMethod() {
        labelInternetStatus.setText("⚫ Online");
        labelInternetStatus.setStyle("-fx-text-fill: green;");
    }

    private void offlineMethod() {
        labelInternetStatus.setText("⚫ Offline");
        labelInternetStatus.setStyle("-fx-text-fill: red;");
    }
}
