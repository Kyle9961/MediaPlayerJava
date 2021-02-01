module com.kylegd.mediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires javafx.media;
    

    opens com.kylegd.mediaplayer to javafx.fxml;
    exports com.kylegd.mediaplayer;
}
