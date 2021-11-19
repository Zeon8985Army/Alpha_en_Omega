package id.ac.ukdw.fti.rpl.bermudaTriangle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.GoogleMapView;
import id.ac.ukdw.fti.rpl.bermudaTriangle.MapComponentInitializedListener;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.DirectionsPane;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.GoogleMap;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.LatLong;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapOptions;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapTypeIdEnum;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.DirectionStatus;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.DirectionsRenderer;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.DirectionsRequest;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.DirectionsResult;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.DirectionsService;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.DirectionsServiceCallback;
import id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions.TravelModes;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DirectionsFXMLController
        implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsRenderer directionsRenderer = null;

    @FXML
    protected GoogleMapView mapView;

    @FXML
    protected TextField fromTextField;

    @FXML
    protected TextField toTextField;

    @FXML
    private void toTextFieldAction(ActionEvent event) {
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING, true);
        directionsRenderer = new DirectionsRenderer(true, mapView.getMap(), directionsPane);
        directionsService.getRoute(request, this, directionsRenderer);
    }

    @FXML
    private void clearDirections(ActionEvent event) {
        directionsRenderer.clearDirections();
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInitializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(47.606189, -122.335842)).zoomControl(true).zoom(12).overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

}