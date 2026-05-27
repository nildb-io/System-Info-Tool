module com.example.actualjavafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires jdk.management;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.management;

    opens com.example.actualjavafxproject to javafx.fxml;
    exports com.example.actualjavafxproject;
    exports com.example.actualjavafxproject.beta_project;
    opens com.example.actualjavafxproject.beta_project to javafx.fxml;
    exports com.example.actualjavafxproject.demo_projects;
    opens com.example.actualjavafxproject.demo_projects to javafx.fxml;
}