/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kylegd.mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ir0nZer0
 */
public class MediafxmlController implements Initializable {
    @FXML private MediaView mvView;
    @FXML private MediaPlayer mp;
    @FXML private Slider sliderProgress,sliderVol;
    
    public void chooseFile(ActionEvent event){
        FileChooser fc=new FileChooser();
        File file=fc.showOpenDialog(null);
        String path=file.toURI().toString();
        
        if(path!=null){
            Media media=new Media(path);
            mp=new MediaPlayer(media);
            mvView.setMediaPlayer(mp);
            
            DoubleProperty width=mvView.fitWidthProperty();
            DoubleProperty height=mvView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mvView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mvView.sceneProperty(), "height"));
            
            mp.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration OldValue, Duration newValue) -> {
                sliderProgress.setValue(newValue.toSeconds());
            });
            
            sliderProgress.setOnMousePressed((MouseEvent t) -> {
                mp.seek(Duration.seconds(sliderProgress.getValue()));
            });
            
            sliderProgress.setOnMouseDragged((MouseEvent t) -> {
                mp.seek(Duration.seconds(sliderProgress.getValue()));
            });
            
            mp.setOnReady(() -> {
                Duration total=media.getDuration();
                sliderProgress.setMax(total.toSeconds());
            });
            
            sliderVol.setValue(mp.getVolume()*100);
            sliderVol.valueProperty().addListener((Observable observable) -> {
                mp.setVolume(sliderVol.getValue()/100);
            });
            
            mp.play();
        }
        
    }
    
    public void play(ActionEvent event){
        mp.play();
        mp.setRate(1);
    }
    
    public void pause(ActionEvent event){
        mp.pause();
    }
    
    public void stop(ActionEvent event){
        mp.stop();
    }
    
    public void slowRate(ActionEvent event){
        mp.setRate(0.5);
    }
    
    public void fastRate(ActionEvent event){
        mp.setRate(2);
    }
    
    public void skipTen(ActionEvent event){
        mp.seek(mp.getCurrentTime().add(Duration.seconds(10)));
    }
    
    public void backTen(ActionEvent event){
        mp.seek(mp.getCurrentTime().add(Duration.seconds(-10)));
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
