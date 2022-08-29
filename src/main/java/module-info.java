module org.one.converter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires lombok;

    opens org.one.converter.controller to javafx.fxml;
    opens org.one.converter to javafx.fxml;
    //exports org.one.converter;
    //exports org.one.converter.controller;
}
