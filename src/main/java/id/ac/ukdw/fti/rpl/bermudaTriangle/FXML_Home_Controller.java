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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

// untuk map
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.GoogleMap;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.InfoWindow;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.InfoWindowOptions;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.LatLong;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapOptions;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapTypeIdEnum;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.Marker;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MarkerOptions;

public class FXML_Home_Controller implements Initializable, MapComponentInitializedListener {
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    private ObservableList<Places> places = FXCollections.observableArrayList();
    private ObservableList<Verse> verses = FXCollections.observableArrayList();

    // menampung 10 top verseCount di place
    private ObservableList<People> listTopPeople = FXCollections.observableArrayList();

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem BtnGoToSearch;

    @FXML
    private MenuItem Verse;

    @FXML
    private Slider slidder;

    @FXML
    private Text objectName;

    @FXML
    private TextArea description;

    @FXML
    private GoogleMapView mapID;

    @FXML
    private ListView<String> listVerse;

    @FXML
    private Button detailVerse;
    @FXML
    private Text backLog;

    // barchart
    @FXML
    private BarChart<String, Integer> barChart;

    // atribute non scene Builder
    private GoogleMap map;
    private String nameObject = "Bethlehem";
    private String typeObject = "place";

    // untuk ke detail
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapID.addMapInitializedListener(this);

        // bersihkan barchart
        if (nameObject == "Bethlehem") {
            this.showDetail("Bethlehem", "place");
        }
    }

    @Override
    public void mapInitialized() {
        description.setWrapText(true);
        places = Database.instance.getAllPlace();
        peoples = Database.instance.getAllPeople();
        double latitude = 0;
        double longtitude = 0;
        ArrayList<String> listVerseInView = new ArrayList<>();
        System.out.println(typeObject.equals("place"));

        if (typeObject.equals("place")) {
            // Database, getValue from Bethlehem
            String namePlace = "No Object";
            String peopleInHere = "No Data";
            String descriptionTextDict = "No Data";

            for (Places place : places) {
                if (place.getDisplayTitle().equals(nameObject)) {
                    if (place.getLatitude() != null && place.getLongitude() != null) {
                        latitude = Double.parseDouble(place.getLatitude());
                        longtitude = Double.parseDouble(place.getLongitude());
                    }
                    if (place.getDictText() != null) {
                        descriptionTextDict = place.getDictText();
                    }
                    namePlace = place.getDisplayTitle();

                    // People in hero (in string type)
                    String[] listPeopleBornHere = { "" };
                    String[] listPeopleDiedHere = { "" };
                    String[] listPeopleHasBeenHere = { "" };

                    if (place.getPeopleBorn() != null) {
                        if (place.getPeopleBorn().contains(",")) {
                            listPeopleBornHere = place.getPeopleBorn().split(",");
                        } else {
                            listPeopleBornHere = place.getPeopleBorn().split(" ");
                        }
                    }

                    if (place.getPeopleDied() != null) {
                        if (place.getPeopleDied().contains(",")) {
                            listPeopleDiedHere = place.getPeopleDied().split(",");
                        } else {
                            listPeopleDiedHere = place.getPeopleDied().split(" ");
                        }
                    }

                    if (place.getHasBeenHere() != null) {
                        if (place.getHasBeenHere().contains(",")) {
                            listPeopleHasBeenHere = place.getHasBeenHere().split(",");
                        } else {
                            listPeopleHasBeenHere = place.getHasBeenHere().split(" ");
                        }
                    }

                    ArrayList<String> listPeopleHereLookup = new ArrayList<>(Arrays.asList(listPeopleBornHere));
                    listPeopleHereLookup.addAll(Arrays.asList(listPeopleDiedHere));
                    listPeopleHereLookup.addAll(Arrays.asList(listPeopleHasBeenHere));

                    StringBuilder sb = new StringBuilder();
                    for (String name : listPeopleHereLookup) {
                        for (People people : peoples) {
                            if (people.getPersonLookup().equals(name)) {
                                sb.append(people.getDisplayTitle());
                                break;
                            }
                        }
                        if (name.equals("")) {
                            break;
                        }
                        sb.append(",");
                    }
                    peopleInHere = sb.toString();
                    if (peopleInHere.equals("")) {
                        peopleInHere = "No Data";
                    }

                    // Verse
                    if (place.getVerses() != null) {
                        if (place.getVerses().contains(",")) {
                            String[] listVerseTampung = place.getVerses().split(",");
                            for (String verse : listVerseTampung) {
                                if (!listVerseInView.contains(verse)) {
                                    listVerse.getItems().add(verse);
                                }
                                listVerseInView.add(verse);
                            }
                        } else {
                            listVerse.getItems().add(place.getVerses());
                        }
                    }

                    break;
                }
            }

            // Map
            LatLong bethlehem = new LatLong(latitude, longtitude);

            MapOptions mapOptions = new MapOptions();
            mapOptions.center(new LatLong(latitude, longtitude))
                    .mapType(MapTypeIdEnum.TERRAIN)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(12);

            map = mapID.createMap(mapOptions);

            // Add markers to the map
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(bethlehem);

            Marker bethlehemMarker = new Marker(markerOptions1);

            map.addMarker(bethlehemMarker);

            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<span>" + namePlace + "</span>");

            InfoWindow bethlehemInfoWindow = new InfoWindow(infoWindowOptions);
            bethlehemInfoWindow.open(map, bethlehemMarker);

            // ObjectName
            objectName.setText(namePlace);

            // Description
            description.setText(descriptionTextDict + "\n\n" + "People in here : " + peopleInHere);

            // Verse --ada pada bagian database
        } else {
            String descriptionTextDict = "No Data";
            ArrayList<ArrayList<String>> listLatLongName = new ArrayList<>();

            // Object name
            objectName.setText(nameObject);

            // Database People
            for (People people : peoples) {
                if (people.getDisplayTitle().equals(nameObject)) {
                    if (people.getDictText() != null) {
                        descriptionTextDict = people.getDictText();
                    }
                    if (people.getGender() != null) {
                        descriptionTextDict = descriptionTextDict +
                                "\n\nGender : " + (people.getGender());
                    } else {
                        descriptionTextDict = descriptionTextDict +
                                "\n\nGender : " + "No Data";
                    }
                    if (people.getBirthYear() != null) {
                        descriptionTextDict = descriptionTextDict +
                                "\n\nBirth Year : " + (people.getBirthYear());
                    } else {
                        descriptionTextDict = descriptionTextDict +
                                "\n\nBirth Year : " + "No Data";
                    }

                    // description
                    description.setText(descriptionTextDict);

                    for (Places place : places) {
                        String[] listPeopleHassBenHere = { "" };
                        String[] listPeopleBornHere = { "" };
                        String[] listPeopleDiedHere = { "" };

                        if (place.getHasBeenHere() != null) {
                            if (place.getHasBeenHere().contains(",")) {
                                listPeopleHassBenHere = place.getHasBeenHere().split(",");
                            } else {
                                listPeopleHassBenHere = place.getHasBeenHere().split(" ");
                            }
                        }

                        if (place.getPeopleBorn() != null) {
                            if (place.getPeopleBorn().contains(",")) {
                                listPeopleBornHere = place.getPeopleBorn().split(",");
                            } else {
                                listPeopleBornHere = place.getPeopleBorn().split(" ");
                            }
                        }

                        if (place.getPeopleDied() != null) {
                            if (place.getPeopleDied().contains(",")) {
                                listPeopleDiedHere = place.getPeopleDied().split(",");
                            } else {
                                listPeopleDiedHere = place.getPeopleDied().split(" ");
                            }
                        }

                        ArrayList<String> listPeopleHere = new ArrayList<>(Arrays.asList(listPeopleHassBenHere));
                        listPeopleHere.addAll(Arrays.asList(listPeopleBornHere));
                        listPeopleHere.addAll(Arrays.asList(listPeopleDiedHere));

                        // MASIH TERDAPAT BUG MINOR, JIKA LOKASI ADA TAPI LANG LONG TIDA ADA
                        // MAAKA AKAN MEMBUAT PROSES SELANJUTNYA ERROR
                        // SLUSI SEMENTARA Descripsi dan penamaan objek diletakan di atas dari MAPPING
                        if (listPeopleHere.contains(people.getPersonLookup())) {
                            // mencegah tidak ada lokasi sama dimasukan 2 kali
                            for (ArrayList<String> ArrayDetail : listLatLongName) {
                                if (ArrayDetail.get(2).equals(place.getDisplayTitle())) {
                                    continue;
                                }
                            }
                            ArrayList<String> listDetail = new ArrayList<>();
                            listDetail.add(place.getLatitude());
                            listDetail.add(place.getLongitude());
                            listDetail.add(place.getDisplayTitle());

                            listLatLongName.add(listDetail);
                        }
                    }

                    if (people.getVerses() != null) {
                        if (people.getVerses().contains(",")) {
                            String[] listVerseTampung = people.getVerses().split(",");
                            for (String verse : listVerseTampung) {
                                if (!listVerseInView.contains(verse)) {
                                    listVerse.getItems().add(verse);
                                }
                                listVerseInView.add(verse);
                            }
                        } else {
                            listVerse.getItems().add(people.getVerses());
                        }
                    }

                    break;
                } else {
                    continue;
                }
            }
            // Map
            ArrayList<LatLong> latLongArr = new ArrayList<>();
            ArrayList<Marker> markerArr = new ArrayList<>();
            ArrayList<InfoWindow> infoWindowArr = new ArrayList<>();
            Integer lenghtPlace = listLatLongName.size();

            // Multiple Marked position
            for (ArrayList<String> ArrayDetail : listLatLongName) {
                latitude = Double.parseDouble(ArrayDetail.get(0));
                longtitude = Double.parseDouble(ArrayDetail.get(1));

                latLongArr.add(new LatLong(latitude, longtitude));

            }

            Integer pointer = 0;
            for (LatLong arrOjb : latLongArr) {
                String namePlace = listLatLongName.get(pointer).get(2);
                // name Place

                // Add markers to the map
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(arrOjb).label(namePlace);

                markerArr.add(new Marker(markerOptions1));
                pointer = pointer + 1;
            }

            MapOptions mapOptions = new MapOptions();
            mapOptions.center(new LatLong(latitude, longtitude))
                    .mapType(MapTypeIdEnum.TERRAIN)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(5);

            map = mapID.createMap(mapOptions);

            Integer pointer2 = 0;
            for (Marker marker : markerArr) {
                map.addMarker(marker);

                if (pointer2 == lenghtPlace - 1) {
                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                    String namePlace = listLatLongName.get(pointer2).get(2);

                    // ada label putih besar
                    // infoWindowOptions.content("<span>" + namePlace + "</span>");
                    // InfoWindow window = new InfoWindow(infoWindowOptions);

                    InfoWindow window = new InfoWindow();
                    window.open(map);
                    break;
                }

                pointer2 = pointer2 + 1;
            }

        }
    }

    // MenuBar Method
    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(bounds.getWidth());
        app_stage.setHeight(bounds.getHeight());
        app_stage.show();
    }

    @FXML
    void goToVerse(ActionEvent event) throws IOException {
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

    @FXML
    void checkSlider(MouseEvent event) {
        description.setStyle("-fx-font-size: " + slidder.getValue());
        listVerse.setStyle("-fx-font-size: " + slidder.getValue());

    }

    @FXML
    void showVerse(ActionEvent event) throws IOException {
        String selectedVerse = listVerse.getSelectionModel().getSelectedItem();

        if (selectedVerse != null) {
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("verseWindow.fxml"));
            root = loader.load();

            FXML_VerseWindow_Controller verseWindow = loader.getController();

            verseWindow.showDetailVerse(selectedVerse);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.show();
        } else {
            backLog.setText("Please Select verse before go to detail verse...");
        }
    }

    public void showDetail(String nameObjSearch, String typeObjectPara) {
        nameObject = nameObjSearch;
        typeObject = typeObjectPara;

        this.secondaryChart();
    }

    public void secondaryChart() {
        barChart.getData().clear();
        barChart.layout();
        // secondary Chart
        // jika detail informasi yang dipilih adalah Place :
        // perbandingan jumlah orang di place itu dengan place lain
        // jumlah ayat tentang place itu dibanding place lain

        // jika detail yang dipilih people:
        // perbandingan jumlah place di people itu dengan people lain
        // jumlah ayat tentang people itu dibanding people lain

        // data" nya diambil dulu dan disimpan di 2 variabel ini
        places = Database.instance.getAllPlace();
        peoples = Database.instance.getAllPeople();

        // atribut penampung 10 nilai tertinggi verse
        listTopPeople = Database.instance.getAllPeopleTopVerseCount();

        // untuk chart 10 top tertinggi verse
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("Top 10");
        series2.setName("Current Object");
        for (People peopleTop : listTopPeople) {

            // dikasih if, supaya yang null ndk tertampil
            if (peopleTop.getVerseCount() != null) {
                series1.getData().add(new XYChart.Data(peopleTop.getDisplayTitle(), Integer.parseInt(peopleTop.getVerseCount())));
            }
        }
        barChart.getData().addAll(series1);

        // untuk chart verse object saat ini
        if (typeObject == "place") {
            for (Places place : places) {
                if (place.getDisplayTitle().equals(nameObject)) {

                    // START - AREA LUKAS ----

                    // END -- AREA LUKAS ---

                    if (place.getVerses() != null) {
                        if (place.getVerses().contains(",")) {
                            String[] listVerseTampung = place.getVerses().split(",");
                            series2.getData().add(new XYChart.Data(nameObject, listVerseTampung.length));
                        } else {
                            series2.getData().add(new XYChart.Data(nameObject, 1));
                        }
                    }

                    break;
                } else {
                    continue;
                }
            }
        } else {
            for (People people : peoples) {
                if (people.getDisplayTitle().equals(nameObject)) {

                    // START - AREA LUKAS ----
                    for (Places place : places) {
                        String[] listPeopleHassBenHere = { "" };
                        String[] listPeopleBornHere = { "" };
                        String[] listPeopleDiedHere = { "" };

                        if (place.getHasBeenHere() != null) {
                            if (place.getHasBeenHere().contains(",")) {
                                listPeopleHassBenHere = place.getHasBeenHere().split(",");
                            } else {
                                listPeopleHassBenHere = place.getHasBeenHere().split(" ");
                            }
                        }

                        if (place.getPeopleBorn() != null) {
                            if (place.getPeopleBorn().contains(",")) {
                                listPeopleBornHere = place.getPeopleBorn().split(",");
                            } else {
                                listPeopleBornHere = place.getPeopleBorn().split(" ");
                            }
                        }

                        if (place.getPeopleDied() != null) {
                            if (place.getPeopleDied().contains(",")) {
                                listPeopleDiedHere = place.getPeopleDied().split(",");
                            } else {
                                listPeopleDiedHere = place.getPeopleDied().split(" ");
                            }
                        }

                        ArrayList<String> listPeopleHere = new ArrayList<>(Arrays.asList(listPeopleHassBenHere));
                        listPeopleHere.addAll(Arrays.asList(listPeopleBornHere));
                        listPeopleHere.addAll(Arrays.asList(listPeopleDiedHere));

                    }
                    // END -- AREA LUKAS ---

                    if (people.getVerses() != null) {
                        if (people.getVerses().contains(",")) {
                            String[] listVerseTampung = people.getVerses().split(",");
                            series2.getData().add(new XYChart.Data(nameObject, listVerseTampung.length));
                        } else {
                            series2.getData().add(new XYChart.Data(nameObject, 1));
                        }
                    }

                    break;
                } else {
                    continue;
                }
            }
        }
        barChart.getData().addAll(series2);

    }
}
