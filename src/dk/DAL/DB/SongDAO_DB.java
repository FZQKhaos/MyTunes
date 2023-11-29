package dk.DAL.DB;

import dk.BE.Song;
import dk.DAL.ISongDataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {


    private DatabaseConnector databaseConnector;

    public SongDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public List<Song> getALlSongs() {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()){

            String sql = "SELECT Songs.Id, Songs.Title, Songs.Time, A.ArtistName, G.GenreName FROM Songs JOIN dbo.Artist A on A.Id = Songs.ArtistId JOIN dbo.Genre G on G.Id = Songs.GenreId;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                String title = rs.getString("Title");
                String artist = rs.getString("ArtistName");
                int songLength = rs.getInt("Time");
                String genre = rs.getString("GenreName");
                int id = rs.getInt("Id");

                Song song = new Song(title, artist, songLength, genre, id);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song createSong(Song song) {
        return null;
    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public void deleteSong(Song song) {

    }
}
