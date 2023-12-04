package dk.BLL;

import dk.BE.Playlist;
import dk.DAL.DB.PlaylistDAO_DB;
import dk.DAL.IPlaylistDataAccess;

import java.io.IOException;
import java.util.List;

public class PlaylistManager {

    private IPlaylistDataAccess playListDAO;

    public PlaylistManager() throws IOException {
        playListDAO = new PlaylistDAO_DB();
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
}
