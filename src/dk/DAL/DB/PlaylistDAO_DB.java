package dk.DAL.DB;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.BE.Playlist;
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
        Statement stmt = conn.createStatement()){

            String sql = "SELECT * FROM dbo.Playlist;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String name = rs.getString("Name");
                int songs = rs.getInt("Songs");
                int time = rs.getInt("Time");
                int id = rs.getInt("Id");

                Playlist playlist = new Playlist(name, time, songs, id);
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
        String sql = "INSERT INTO dbo.Playlist (Name, Songs, Time) VALUES (?, ?, ?);";

        try (Connection conn = databaseConnector.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind parameters
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, playlist.getSongs());
            stmt.setInt(3, playlist.getTime());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()){
                id = rs.getInt(1);
            }

            Playlist createdPlaylist = new Playlist(playlist.getName(), playlist.getSongs(), playlist.getTime());

            return createdPlaylist;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePlaylist(Playlist playlist) {

    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        String sql = "DELETE FROM dbo.Playlist WHERE Id = (?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            // Bind parameters
            stmt.setInt(1, playlist.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
