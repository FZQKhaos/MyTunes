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


}
