package dk.BLL;

import dk.BE.Playlist;
import dk.BE.Song;
import dk.DAL.DB.PlaylistDAO_DB;
import dk.DAL.IPlaylistDataAccess;

import java.io.IOException;
import java.util.List;

public class PlaylistManager {

    private IPlaylistDataAccess playListDAO;

    private PlaylistDAO_DB playListDAO_DB;

    public PlaylistManager() throws IOException {
        playListDAO = new PlaylistDAO_DB();
        this.playListDAO_DB = new PlaylistDAO_DB();
    }

    public List<Playlist> getAllPlaylists() throws IOException {
        return playListDAO.getAllPlaylists();
    }

    public void createPlaylist(Playlist playlist) {
        playListDAO.createPlaylist(playlist);
    }

    public void deletePlaylist(Playlist selectedPlaylist) {
        playListDAO.deletePlaylist(selectedPlaylist);
    }

    public void addSongsToPlaylist(Song song, Playlist playlist){
        playListDAO_DB.addSongsToPlaylist(song, playlist);
    }

    public List<Song> getSongsForPlaylist(int playlistId) {
        return playListDAO_DB.getAllSongsInPlaylist(playlistId);
    }

    public void editPlaylist(Playlist playlist, String newName) {
        playListDAO.updatePlaylist(playlist, newName);
    }

    public void deletePlaylistSong(Playlist selectedPlaylist, Song selectedSonginPlaylist) {
        playListDAO_DB.deleteSongFromPlaylist(selectedPlaylist, selectedSonginPlaylist);
    }
}
