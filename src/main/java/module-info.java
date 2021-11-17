module bermudaTriangle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens id.ac.ukdw.fti.rpl.bermudaTriangle to javafx.fxml;

    exports id.ac.ukdw.fti.rpl.bermudaTriangle.database;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.modal;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle;

    requires javafx.base;
    requires javafx.graphics;

    requires jdk.jsobject;
    requires java.logging;
    requires java.desktop;
    requires javafx.web;
    requires javafx.swing;


    requires org.slf4j;
    requires java.xml.bind;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.javascript;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.event;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.service.directions;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.service.elevation;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.service.geocoding;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.shapes;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.util;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.zoom;
}
