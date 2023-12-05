package dk.GUI.Controller;

import javafx.scene.control.Slider;
import javafx.scene.media.Media;

import java.io.File;

public class MediaPlayer {

    private javafx.scene.media.MediaPlayer mediaPlayer;
    private String folder = "Song\\";
    private boolean isPlaying = false;
    private String curretSongFilePath = "";


    public void playMusic(String filePath) {
        File mediaFile = new File(folder, filePath);

        // Afspiller Sang
        if (mediaFile.exists()) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            Media media = new Media(mediaFile.toURI().toString());
            mediaPlayer = new javafx.scene.media.MediaPlayer(media);
            mediaPlayer.play();
            isPlaying = true;
            curretSongFilePath = filePath;
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            isPlaying = false;
        }
    }

    //Skift filePath i DB til kun at være navn.mp3 kan være det hjælper på det med at afspille filen

    /*
    public void playMusic(String filePath){
        File mediaFile = new File(folder + filePath);

        // Afspiller Sang
        if (mediaFile.exists()){

            if (mediaPlayer != null && filePath.equals(curretSongFilePath) && !isPlaying){
                mediaPlayer.play();
                isPlaying = true;
            }
        } else {
            if (mediaPlayer != null){
                mediaPlayer.stop();
            }
        }

        Media media = new Media(mediaFile.toURI().toString());
        mediaPlayer = new javafx.scene.media.MediaPlayer(media);
        mediaPlayer.play();
        isPlaying = true;
        curretSongFilePath = filePath;
    }
    */

    public void pauseMusic(){
        if (mediaPlayer != null){
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public void volumeBar(Slider volumeSlider){
        volumeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (mediaPlayer != null){
                mediaPlayer.setVolume(newValue.doubleValue() / 100.0);
            }
        });
    }
}
