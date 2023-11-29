package dk.GUI.Controller;

import dk.BE.Song;
import dk.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private Label currentSong;

    @FXML
    private TableView<?> tblPlaylist;

    @FXML
    private TableView<?> tblSonginPlaylist;

    @FXML
    private TableView<Song> tblSongs;

    @FXML
    private TextField txtSongSearch;

    private SongModel songModel;

    public MainController(){
        try {
            songModel = new SongModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colArtists.setCellValueFactory(new PropertyValueFactory<>("ArtistName"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("GenreName"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Time"));

        tblSongs.setItems(songModel.getObservableSongs());
    }

    @FXML
    public void onActionAddSongToPlatlist(ActionEvent event) {

    }

    @FXML
    public void onActionDeletePlaylist(ActionEvent event) {

    }

    @FXML
    public void onActionDeletePlaylistSong(ActionEvent event) {

    }

    @FXML
    public void onActionDeleteSong(ActionEvent event) {

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
    public void onActionNewPlaylist(ActionEvent event) {

    }

    @FXML
    public void onActionNewSong(ActionEvent event) {

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

