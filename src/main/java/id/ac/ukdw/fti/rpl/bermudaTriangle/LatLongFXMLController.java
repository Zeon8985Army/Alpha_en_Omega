package id.ac.ukdw.fti.rpl.bermudaTriangle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.GoogleMapView;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.event.GMapMouseEvent;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.event.UIEventType;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.GoogleMap;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.LatLong;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapOptions;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapTypeIdEnum;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LatLongFXMLController implements Initializable {

    @FXML
    private Label latitudeLabel;

    @FXML
    private Label longitudeLabel;

    @FXML
    private GoogleMapView googleMapView;

    private GoogleMap map;

    private DecimalFormat formatter = new DecimalFormat("###.00000");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        googleMapView.addMapInitializedListener(() -> configureMap());
    }

    protected void configureMap() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331)).mapType(MapTypeIdEnum.ROADMAP).zoom(9);
        map = googleMapView.createMap(mapOptions, false);

        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            LatLong latLong = event.getLatLong();
            latitudeLabel.setText(formatter.format(latLong.getLatitude()));
            longitudeLabel.setText(formatter.format(latLong.getLongitude()));
        });

    }
}
