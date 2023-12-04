package dk.DAL;

import dk.BE.Playlist;

import java.util.List;

public interface IPlaylistDataAccess {

    public List<Playlist> getAllPlaylists();

    public Playlist createPlaylist(Playlist playlist);

    public void updatePlaylist(Playlist playlist);

    public void deletePlaylist(Playlist playlist);

}
