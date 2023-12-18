module gr2310.ourplace.ui {
    requires transitive gr2310.ourplace.core;
    requires gr2310.ourplace.rest;
    requires gr2310.ourplace.client;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens gr2310.ourplace.ui to javafx.fxml;

    exports gr2310.ourplace.ui;
}
