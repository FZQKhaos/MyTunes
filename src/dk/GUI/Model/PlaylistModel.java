package dk.GUI.Model;

import dk.BE.Playlist;
import dk.BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class PlaylistModel {

    private ObservableList<Playlist> playlistsToBeViewed;

    private PlaylistManager playlistManager;

    public PlaylistModel() throws IOException {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    public ObservableList<Playlist> getObservablePlaylists(){
        return playlistsToBeViewed;
    }


    public void createPlaylist(String name, int time, int songs){
        Playlist playlist = new Playlist(name, time, songs);
        playlistManager.createPlaylist(playlist);
        playlistsToBeViewed.add(playlist);
    }

    public void deletePlatlist(Playlist selectedPlaylist){
        playlistManager.deletePlaylist(selectedPlaylist);
        playlistsToBeViewed.remove(selectedPlaylist);
    }

}
