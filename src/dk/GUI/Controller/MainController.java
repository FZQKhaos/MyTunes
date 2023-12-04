package dk.GUI.Controller;

import dk.BE.Playlist;
import dk.BE.Song;
import dk.GUI.Model.PlaylistModel;
import dk.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public TableColumn<Song, String> colTitle;

    @FXML
    public TableColumn<Song, String> colArtists;

    @FXML
    public TableColumn<Song, String> colGenre;

    @FXML
    public TableColumn<Song, Integer> colTime;

    @FXML
    public TableColumn<Playlist, String> colPName;

    @FXML
    public TableColumn<Playlist, Integer> colPSongs;

    @FXML
    public TableColumn<Playlist, Integer> colPTime;

    @FXML
    private Label currentSong;

    @FXML
    private TableView<Playlist> tblPlaylist;

    @FXML
    private TableView<?> tblSonginPlaylist;

    @FXML
    private TableView<Song> tblSongs;

    @FXML
    private TextField txtSongSearch;

    private SongModel songModel;

    private PlaylistModel playlistModel;

    public MainController(){
        try {
            songModel = new SongModel();
            playlistModel = new PlaylistModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TableView Containing all Songs
        colArtists.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("TimeString"));
        // TableView Containing all Playlists
        colPName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPSongs.setCellValueFactory(new PropertyValueFactory<>("Songs"));
        colPTime.setCellValueFactory(new PropertyValueFactory<>("Time"));

        tblSongs.setItems(songModel.getObservableSongs());
        tblPlaylist.setItems(playlistModel.getObservablePlaylists());
    }

    @FXML
    public void onActionAddSongToPlaylist(ActionEvent event) {

    }

    @FXML
    public void onActionDeletePlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();

        if (selectedPlaylist != null){
            playlistModel.deletePlatlist(selectedPlaylist);
        }
    }

    @FXML
    public void onActionDeletePlaylistSong(ActionEvent event) {

    }

    @FXML
    public void onActionDeleteSong(ActionEvent event) {
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();

        if (selectedSong != null) {
            songModel.deleteSong(selectedSong);
        }
    }

    @FXML
    public void onActionDownArrow(ActionEvent event) {

    }

    @FXML
    public void onActionEditPlaylist(ActionEvent event) {

    }

    @FXML
    public void onActionEditSong(ActionEvent event) {

    }

    @FXML
    public void onActionLeftSkip(ActionEvent event) {

    }

    @FXML
    public void onActionNewPlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPlaylistWindow.fxml"));
        Parent root =  loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("New/Edit Playlist");

        stage.show();
    }

    @FXML
    public void onActionNewSong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewSongWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("New/Edit Song");

        stage.show();
    }

    @FXML
    public void onActionPlayPasue(ActionEvent event) {

    }

    @FXML
    public void onActionRightSkip(ActionEvent event) {

    }

    @FXML
    public void onActionUpArrow(ActionEvent event) {

    }

    @FXML
    public void onDragVolume(MouseEvent event) {

    }


}

