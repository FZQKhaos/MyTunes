package dk.GUI.Model;

import dk.BE.Playlist;
import dk.BE.Song;
import dk.BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class PlaylistModel {

    private ObservableList<Playlist> playlistsToBeViewed;

    private ObservableList<Song> songsInPlaylistToBeViewed;

    private PlaylistManager playlistManager;

    public PlaylistModel() throws IOException {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());

    }

    public ObservableList<Playlist> getObservablePlaylists(){
        return playlistsToBeViewed;
    }

    public ObservableList<Song> getObservableSongsInPlaylist(Playlist playlist) throws Exception{
        int playlistId = playlist.getId();
        return FXCollections.observableArrayList(playlistManager.getSongsForPlaylist(playlistId));
    }

    public void loadSongsForPlaylist(int playlistId) {
        List<Song> songsList = null;

        if (songsInPlaylistToBeViewed == null){
            songsInPlaylistToBeViewed = FXCollections.observableArrayList();
        }
        try {
            songsList = playlistManager.getSongsForPlaylist(playlistId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        songsInPlaylistToBeViewed.clear();
        songsInPlaylistToBeViewed.addAll(songsList);
    }


    public void createPlaylist(String name){
        Playlist playlist = new Playlist(name);
        playlistManager.createPlaylist(playlist);
        playlistsToBeViewed.add(playlist);
    }

    public void deletePlatlist(Playlist selectedPlaylist){
        playlistManager.deletePlaylist(selectedPlaylist);
        playlistsToBeViewed.remove(selectedPlaylist);
    }

    public void addSongsToPlaylist(Song song, Playlist playlist){
        playlistManager.addSongsToPlaylist(song, playlist);
    }

    public void updatePlaylist(Playlist playlist, String newName) {
        playlistManager.editPlaylist(playlist, newName);
    }

    public void deletePlaylistSong(Playlist selectedPlaylist, Song selectedSonginPlaylist) {
        songsInPlaylistToBeViewed = FXCollections.observableArrayList();
        /*
        if (songsInPlaylistToBeViewed == null){
            return;
            //System.out.println("PlaylistModel1");
        }
        */
        playlistManager.deletePlaylistSong(selectedPlaylist, selectedSonginPlaylist);
        songsInPlaylistToBeViewed.remove(selectedSonginPlaylist);
    }
}
