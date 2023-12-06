package dk.GUI.Controller;

import dk.BE.Playlist;
import dk.BE.Song;
import dk.GUI.Model.PlaylistModel;
import dk.GUI.Model.SongModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Button btnPlayPause;
    @FXML
    public Slider volumeSlider;
    @FXML
    public Button btnLeftSkip, btnRightSkip;


    @FXML
    private TableColumn<Song, String> colTitle;

    @FXML
    private TableColumn<Song, String> colArtists;

    @FXML
    private TableColumn<Song, String> colGenre;

    @FXML
    private TableColumn<Song, Integer> colTime;

    @FXML
    private TableColumn<Playlist, String> colPName;

    @FXML
    private TableColumn<Playlist, Integer> colPSongs;

    @FXML
    private TableColumn<Playlist, Integer> colPTime;

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
    private MediaPlayer mediaPlayer;

    private StringProperty currentSongDetails = new SimpleStringProperty();

    private int currentSongIndex = 0;


    public MainController() {
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

        // Searchbar
        txtSongSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        this.mediaPlayer = new MediaPlayer();
        mediaPlayer.volumeBar(volumeSlider);
        volumeSlider.setValue(100);

        //apfspiller tideligere og næste sang i tableView
        btnLeftSkip.setOnAction(event -> playPreviousSong());
        btnRightSkip.setOnAction(event -> playNextSong());

        currentSong.textProperty().bind(currentSongDetails);
    }

    @FXML
    public void onActionAddSongToPlaylist(ActionEvent event) {

    }

    @FXML
    public void onActionDeletePlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();

        if (selectedPlaylist != null) {
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
    public void onActionEditSong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewSongWindow.fxml"));
        Parent root = loader.load();

        NewSongController newSongController = loader.getController();
        newSongController.setMainController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("New/Edit Song");
        stage.show();

       /* NewPlaylistController newPlaylistController = loader.getController();
        newPlaylistController.setMainController(this); //en reference til MainController

        stage.setTitle("New/Edit Playlist");
        stage.show();*/

    }

    @FXML
    public void onActionNewSong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewSongWindow.fxml"));
        Parent root = loader.load();

        NewSongController newSongController = loader.getController();
        newSongController.setMainController(this); //en reference til MainController

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("New/Edit Song");

        stage.show();
    }




    @FXML
    public void onActionLeftSkip(ActionEvent event) {
        playPreviousSong();
    }

    @FXML
    public void onActionNewPlaylist(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPlaylistWindow.fxml"));
        Parent root =  loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        NewPlaylistController newPlaylistController = loader.getController();
        newPlaylistController.setMainController(this); //en reference til MainController

        stage.setTitle("New/Edit Playlist");

        stage.show();
    }


    @FXML
    public void onActionPlayPause(ActionEvent event) {
            Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();
            if (selectedSong != null) {
                if (mediaPlayer.isPlaying()) {
                    btnPlayPause.setText("▶");
                    mediaPlayer.pauseMusic();
                } else {
                    playSong(selectedSong);
                }
            }
        }

    private void playSong (Song song){
        currentSongIndex = songModel.getObservableSongs().indexOf(song);
        if (song != null && mediaPlayer != null){
            System.out.println("Playing: " + song.getFilePath());
            mediaPlayer.playMusic(song.getFilePath());
            currentSongDetails.set("Currently Playing: " + song.getTitle() + " - " + song.getArtist());
            btnPlayPause.setText("⏸");
        }
    }

    private void playPreviousSong(){
        if (!songModel.getObservableSongs().isEmpty() && currentSongIndex > 0) {
            currentSongIndex--;
            playSong(songModel.getObservableSongs().get(currentSongIndex));
        }
    }

    private void playNextSong() {
        if (!songModel.getObservableSongs().isEmpty() && currentSongIndex < songModel.getObservableSongs().size() - 1) {
            currentSongIndex++;
            playSong(songModel.getObservableSongs().get(currentSongIndex));
        }
    }

    @FXML
    public void onActionRightSkip(ActionEvent event) {
        playNextSong();
    }

    @FXML
    public void onActionUpArrow(ActionEvent event) {

    }

    //Tilføjelse af en sang til TableView
    public void addSongToTable(Song song){ //den metode tilføjer sang til TableView
        tblSongs.getItems().add(song);
    }

    public void addPlaylistToTable(Playlist playlist){
        tblPlaylist.getItems().add(playlist);
    }

}

