package dk.BLL;

import dk.BE.Song;
import dk.DAL.DB.SongDAO_DB;
import dk.DAL.ISongDataAccess;

import java.io.IOException;
import java.util.List;

public class SongManager {

    private ISongDataAccess songDAO;

    public SongManager() throws IOException {
        songDAO = new SongDAO_DB();
    }

    public List<Song> getAllSongs(){
        return songDAO.getALlSongs();
    }
}
