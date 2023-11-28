package dk.DAL;

import dk.BE.Song;

import java.util.List;

public interface ISongDataAccess {

    public List<Song> getALlSongs();

    public Song createSong(Song song);

    public void updateSong(Song song);

    public void deleteSong(Song song);
}
