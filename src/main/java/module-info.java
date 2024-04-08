module com.tpk.javafxcutoftext {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.tpk.javafxcutoftext to javafx.fxml;
    exports com.tpk.javafxcutoftext;
    exports com.tpk.javafxcutoftext.controller;
    opens com.tpk.javafxcutoftext.controller to javafx.fxml;
}