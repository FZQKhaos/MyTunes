package dk.GUI.Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;

public class MediaPlayer {



    private javafx.scene.media.MediaPlayer mediaPlayer;
    private String folder = "Songs" + File.separator;
    private boolean isPlaying = false;
    private String curretSongFilePath = "";

    private Label lblSongTimer;
    private ProgressBar pbSongTimer;


    public MediaPlayer() {
    }

    public MediaPlayer(Label lblSongTimer, ProgressBar pbSongTimer) {
        this.lblSongTimer = lblSongTimer;
        this.pbSongTimer = pbSongTimer;
    }

    /**
     * Skift filePath i DB til kun at være navn.mp3
     * Eksempel
     * C:\Users\Brugernavn\Desktop\Mappe1\Mappe2\7 Years.mp3 - Forkert
     * 7 Years.mp3 - rigtigt
     */


    public void playMusic(String filePath){

        File mediaFile = new File(folder + filePath);

        if (mediaFile.exists()) {
            if (mediaPlayer != null) {
                if (isPlaying) {
                    mediaPlayer.pause();
                }
            }

            Media media = new Media(mediaFile.toURI().toString());
            if (mediaPlayer == null || !filePath.equals(curretSongFilePath)) {
                mediaPlayer = new javafx.scene.media.MediaPlayer(media);
            }

            mediaPlayer.play();
            isPlaying = true;
            curretSongFilePath = filePath;
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        }
    }

    public void pauseMusic(){
        if (mediaPlayer != null){
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public void duration(Label lblSongTimer){
        ReadOnlyObjectProperty<Duration> currentTime = mediaPlayer.currentTimeProperty();

        currentTime.addListener((observable, oldValue, newValue) -> {
            Duration pbCurrentDuration = mediaPlayer.getCurrentTime();
            Duration pbTotalDuration = mediaPlayer.getTotalDuration();
            if (pbTotalDuration != null && pbTotalDuration.toSeconds() > 0){
                double pbProgress = pbCurrentDuration.toSeconds() / pbTotalDuration.toSeconds();
                pbSongTimer.setProgress(pbProgress);
            }
        });

        lblSongTimer.textProperty().bind(Bindings.createStringBinding(() ->
                getTimeString(mediaPlayer.getCurrentTime()), currentTime));
    }

    private String getTimeString(Duration duration) {

        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;

        String formatedTime = String.format("%d:%02d", minutes, seconds);
        return formatedTime;
    }

    public void volumeBar(Slider volumeSlider){
        volumeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (mediaPlayer != null){
                mediaPlayer.setVolume(newValue.doubleValue() / 100.0);
            }
        });
    }
}
