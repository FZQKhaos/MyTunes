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

            String sql = "SELECT * FROM dbo.Songs";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                int songLength = rs.getInt("Time");
                String genre = rs.getString("Genre");
                int id = rs.getInt("Id");
                String filePath = rs.getString("FilePath");

                Song song = new Song(title, id, artist, songLength, genre, filePath);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song createSong(Song song) {
        String sql = "INSERT INTO dbo.Songs (Title, Artist, Time, Filepath, Genre) VALUES (?,?,?,?, ?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setInt(3, song.getTime());
            stmt.setString(4, song.getFilePath());
            stmt.setString(5, song.getGenre());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            Song createdSong = new Song(song.getTitle(), id, song.getArtist(), 45, song.getGenre(), song.getFilePath());

            return createdSong;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSong(Song song) {
        String sql = "DELETE FROM dbo.Songs WHERE Id = (?);";

        try (Connection conn = databaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            // Bind parameters
            stmt.setInt(1, song.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
