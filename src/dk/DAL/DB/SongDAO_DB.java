package dk.DAL.DB;

import dk.BE.Song;
import dk.DAL.ISongDataAccess;

import java.io.IOException;
import java.sql.*;
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

           // String sql = "SELECT Songs.Id, Songs.Title, Songs.Time, A.ArtistName, G.GenreName " +
           //         "FROM Songs " +
           //         "JOIN dbo.Artist A on A.Id = Songs.ArtistId " +
           //         "JOIN dbo.Genre G on G.Id = Songs.GenreId;";
            String sql = "SELECT * FROM Songs";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                int songLength = rs.getInt("Time");
                String genre = rs.getString("Genre");
                int id = rs.getInt("Id");
                String filePath = rs.getString("FilePath");

                //Song song = new Song(title, artist, songLength, genre, id);
                Song song = new Song(title, artist, songLength, genre, id, filePath);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song createSong(Song song) throws Exception {
        String sql = "INSERT INTO dbo.Songs (Title, Artist, Time, Filepath, Genre) VALUES (?,?,?,?, ?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtistName());
            stmt.setInt(3, 34);
            stmt.setString(4, song.getFilePath());
            stmt.setString(5, song.getGenreName());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // public Song(String title, String artistName, int time, String genreName, int id, String filePath) {
            // Create movie object and send up the layers

            Song createdSong = new Song(song.getTitle(), song.getArtistName(), 45, song.getGenreName(), id, song.getFilePath());

            return createdSong;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create movie", ex);
        }
    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public void deleteSong(Song song) {

    }
}
