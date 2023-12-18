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
    public Slider volumeSlider;

    @FXML
    public Button btnPlayPause, btnLeftSkip, btnRightSkip, btnEditPlaylist;


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
    private ProgressBar pbSongTimer;

    @FXML
    private Label currentSong, lblSongTimer;

    @FXML
    private TableView<Playlist> tblPlaylist;

    @FXML
    private TableView<Song> tblSonginPlaylist;

    @FXML
    private TableColumn colTitleSiP;

    @FXML
    private TableView<Song> tblSongs;

    @FXML
    private TextField txtSongSearch;

    private SongModel songModel;

    private PlaylistModel playlistModel;

    private MediaPlayer mediaPlayer;

    private NewPlaylistController playlistController;

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

        colTitleSiP.setCellValueFactory(new PropertyValueFactory<>("Title"));


        tblSongs.setItems(songModel.getObservableSongs());
        tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        tblSonginPlaylist.setItems(tblSonginPlaylist.getItems());

        tblPlaylist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue != null){
                try {
                    tblSonginPlaylist.setItems(playlistModel.getObservableSongsInPlaylist(newValue));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Searchbar
        txtSongSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // MediaPlayer
        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer = new MediaPlayer(lblSongTimer, pbSongTimer);
        mediaPlayer.volumeBar(volumeSlider);
        volumeSlider.setValue(100);

        // Skip knapperne
        btnLeftSkip.setOnAction(event -> playPreviousSong());
        btnRightSkip.setOnAction(event -> playNextSong());

        currentSong.textProperty().bind(currentSongDetails);

    }

    public void setPlaylistController (NewPlaylistController controller){
        playlistController = controller;
    }

    @FXML
    public void onActionAddSongToPlaylist(ActionEvent event) {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();

        if (selectedPlaylist == null || selectedSong == null){
            alertBox("Could not add song to Playlist", "You did not select a song or playlist");
        }

        if (selectedPlaylist != null && selectedSong != null){
            try {
                playlistModel.addSongsToPlaylist(selectedSong, selectedPlaylist);
                playlistModel.loadSongsForPlaylist(selectedPlaylist.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void alertBox(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        Song selectedSonginPlaylist = tblSonginPlaylist.getSelectionModel().getSelectedItem();

        if (selectedSonginPlaylist != null && selectedPlaylist != null) {
            playlistModel.deletePlaylistSong(selectedPlaylist, selectedSonginPlaylist);
        }
    }

    @FXML
    public void onActionDeleteSong(ActionEvent event) {
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();

        if (selectedSong != null) {
            songModel.deleteSong(selectedSong);
        }
    }

    @FXML
    public void onActionEditPlaylist(ActionEvent event) throws IOException {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();

        btnEditPlaylist.setDisable(true);

        if (selectedPlaylist != null){
            btnEditPlaylist.setDisable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPlaylistWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.setTitle("New/Edit Playlist");

            NewPlaylistController newPlaylistController = loader.getController();
            newPlaylistController.setMainController(this);
            newPlaylistController.setShouldEdit(true, selectedPlaylist);

            setPlaylistController(newPlaylistController);

            stage.show();

            String getSelectedPlaylistName = tblPlaylist.getSelectionModel().getSelectedItem().getName();
            playlistController.txtPlaylistName.setText(getSelectedPlaylistName);
        } else {
            btnEditPlaylist.setDisable(false);
        }
    }

    public void updatePlaylistTable(){
        tblPlaylist.refresh();
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
            Song selectedSongfromPlaylist = tblSonginPlaylist.getSelectionModel().getSelectedItem();
            if (selectedSong != null) {
                if (mediaPlayer.isPlaying()) {
                    btnPlayPause.setText("▶");
                    mediaPlayer.pauseMusic();
                } else {
                    playSong(selectedSong);
                    btnPlayPause.setText("⏸");
                }
            }
        if (selectedSongfromPlaylist != null) {
            if (mediaPlayer.isPlaying()) {
                btnPlayPause.setText("▶");
                mediaPlayer.pauseMusic();
            } else{
                playSong(selectedSongfromPlaylist);
                btnPlayPause.setText("⏸");
            }
        }

    }



    private void playSong (Song song){
        currentSongIndex = songModel.getObservableSongs().indexOf(song);
        if (song != null && mediaPlayer != null){
            mediaPlayer.playMusic(song.getFilePath());
            mediaPlayer.duration(lblSongTimer);
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


    //Tilføjelse af en sang til TableView
    public void addSongToTable(Song song){ //den metode tilføjer sang til TableView
        tblSongs.getItems().add(song);
    }

    public void addPlaylistToTable(Playlist playlist){
        tblPlaylist.getItems().add(playlist);
    }



}

