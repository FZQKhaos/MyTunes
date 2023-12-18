package dk.DAL.DB;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.BE.Playlist;
import dk.BE.Song;
import dk.DAL.IPlaylistDataAccess;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {

    private DatabaseConnector databaseConnector;

    public PlaylistDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();

    }

    @Override
    public List<Playlist> getAllPlaylists() {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Playlist;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("Name");
                int id = rs.getInt("Id");

                Playlist playlist = new Playlist(name, id);
                allPlaylists.add(playlist);
            }
            return allPlaylists;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        String sql = "INSERT INTO dbo.Playlist (Name) VALUES (?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind parameters
            stmt.setString(1, playlist.getName());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            Playlist createdPlaylist = new Playlist(playlist.getName());

            return createdPlaylist;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePlaylist(Playlist playlist, String newName) {

        String sql = "UPDATE dbo.Playlist SET name = (?) WHERE [id] = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, newName);
            stmt.setInt(2, playlist.getId());

            stmt.executeUpdate();

            playlist.setName(newName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        String sql = "DELETE FROM dbo.Playlist WHERE Id = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters
            stmt.setInt(1, playlist.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addSongsToPlaylist(Song song, Playlist playlist) {

        String sql = "INSERT INTO dbo.PlaylistSongs (SongsId, PlaylistId) VALUES (?, ?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, song.getId());
            stmt.setInt(2, playlist.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SecurityException("Kan sku ik du");
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Song> getAllSongsInPlaylist(int playlistId) {
        List<Song> allSongsinPlaylist = new ArrayList<>();

        String sql = "SELECT s.*, PlaylistId\n" +
                "FROM dbo.PlaylistSongs\n" +
                "JOIN dbo.Songs S on S.Id = PlaylistSongs.SongsId\n" +
                "WHERE PlaylistId = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playlistId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("Title");
                int id = rs.getInt("Id");
                String artist = rs.getString("Artist");
                int time = rs.getInt("Time");
                String genre = rs.getString("Genre");
                String filePath = rs.getString("filePath");

                Song song = new Song(title, id, artist, time, genre, filePath);
                allSongsinPlaylist.add(song);
            }
            return allSongsinPlaylist;
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSongFromPlaylist(Playlist playlist, Song song) {
        String sql = "DELETE FROM dbo.PlaylistSongs WHERE SongsId = (?) and PlaylistId = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters
            stmt.setInt(1, song.getId());
            stmt.setInt(2, playlist.getId());


            // Run the specified SQL statement
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}