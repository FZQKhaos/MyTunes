package dk.GUI.Controller;





import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


import dk.BE.Song;
import dk.GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewSongController implements Initializable {

    @FXML
    public Button btnCancel;
    @FXML
    public TextField txtTitle, txtArtist, txtTime, txtFile;

    public MenuButton btnGenre;

    private MainController mainController;

    private SongModel songModel;
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            songModel = new SongModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onActionChoose(ActionEvent event) throws InvalidDataException, UnsupportedTagException, IOException {
        Stage stage = new Stage();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File("Songs"));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.m4a"));

            File file = fileChooser.showOpenDialog(stage);

            if (file != null){

                Mp3File mp3file = new Mp3File(file.getAbsolutePath());


                if (mp3file.hasId3v2Tag()) {
                    ID3v2 metaData = mp3file.getId3v2Tag();

                    txtTime.setText(String.valueOf(mp3file.getLengthInSeconds()));
                    txtArtist.setText(metaData.getArtist());
                    txtTitle.setText(metaData.getTitle());


                }
                txtFile.setText((file.getName()));
            }
    }



    public void onActionSave(ActionEvent event) throws Exception {
        //public TextField txtTitle, txtArtist, txtTime, txtFile;
        //public Song(String title, String artistName, int time, String genreName)
        //Song song = new Song(txtTitle.getText(), txtArtist.getText(), txtTime.getText(), );
        songModel.createSong(txtTitle.getText(), txtArtist.getText(), txtTime.getText(), "genre", txtFile.getText());
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        int time = Integer.parseInt(txtTime.getText());
        String file = txtFile.getText();

        //når der oprettes ny sang og tilføjes til tabellen i MainController
        Song newSong = new Song(title,artist,time,file,btnGenre.getText());
        mainController.addSongToTable(newSong); //opdatering af tableView i MainController

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void onActionCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void OnActionGenre(ActionEvent event) {

        MenuItem selectedGenre = (MenuItem) event.getSource();
            String genreName = selectedGenre.getText();
            btnGenre.setText(genreName);
        }




}

