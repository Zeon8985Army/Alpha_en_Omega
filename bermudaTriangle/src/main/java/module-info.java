module bermudaTriangle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens id.ac.ukdw.fti.rpl.bermudaTriangle to javafx.fxml;

    exports id.ac.ukdw.fti.rpl.bermudaTriangle.database;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle.modal;
    exports id.ac.ukdw.fti.rpl.bermudaTriangle;
}
