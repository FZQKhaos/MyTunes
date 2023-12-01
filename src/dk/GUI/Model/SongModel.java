package dk.GUI.Model;

import dk.BE.Song;
import dk.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class SongModel {

    private ObservableList<Song> songsToBeViewed;

    private SongManager songManager;

    public SongModel() throws IOException {
        songManager = new SongManager();
        songsToBeViewed = FXCollections.observableArrayList();
        songsToBeViewed.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getObservableSongs(){
        return songsToBeViewed;
    }

    public void createSong(String title, String artist, String time, String genre, String filePath) throws Exception {
        int newTime = 0;
        try {
            newTime = Integer.parseInt(time);
        } catch (Exception e) {
            System.out.println("Tiden er ikke korrekt");
            return;
            //throw new Exception("Tiden er ikke korrekt", e);
           // throws new IOException(e);
        }
        Song song = new Song(title, artist, newTime, genre, filePath);
        songManager.createSong(song);

    }

}
