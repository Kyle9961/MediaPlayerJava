module com.kylegd.mediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.base;
    requires org.controlsfx.controls;
    
    

    opens com.kylegd.mediaplayer to javafx.fxml;
    exports com.kylegd.mediaplayer;
}
