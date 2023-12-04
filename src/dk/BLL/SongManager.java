package dk.BLL;

import dk.BE.Song;
import dk.BLL.util.SongSearcher;
import dk.DAL.DB.SongDAO_DB;
import dk.DAL.ISongDataAccess;

import java.io.IOException;
import java.util.List;

public class SongManager {

    private ISongDataAccess songDAO;

    private SongSearcher songSearcher = new SongSearcher();

    public SongManager() throws IOException {
        songDAO = new SongDAO_DB();
    }

    public List<Song> searchSongs(String query){
        List<Song> allSongs = getAllSongs();
        List<Song> songResult = songSearcher.search(allSongs, query);
        return songResult;
    }

    public List<Song> getAllSongs(){
        return songDAO.getALlSongs();
    }

    public void createSong(Song song) throws Exception {
        songDAO.createSong(song);
    }

    public void deleteSong(Song selectedSong) {
        songDAO.deleteSong(selectedSong);
    }
}
