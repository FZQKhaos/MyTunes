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
    private Button btnCancel;

    @FXML
    private TextField txtTitle, txtArtist, txtTime, txtFile;

    @FXML
    private MenuButton btnGenre;

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

    /**
     * File chooser, which is used to select songs from your computer.
     */
    public void onActionChoose(ActionEvent event) throws InvalidDataException, UnsupportedTagException, IOException {
        Stage stage = new Stage();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File("Songs"));
            fileChooser.getExtensionFilters().addAll(
                    //Determine which file extensions is allowed while inserting a file.
                    new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3"));

            File file = fileChooser.showOpenDialog(stage);

            if (file != null){

                Mp3File mp3file = new Mp3File(file.getAbsolutePath());

                //This is used with the "mp3agic" library. Which allows us to collect metadata from mp3 files.
                if (mp3file.hasId3v2Tag()) {
                    ID3v2 metaData = mp3file.getId3v2Tag();

                    txtTime.setText(String.valueOf(mp3file.getLengthInSeconds()));
                    txtArtist.setText(metaData.getArtist());
                    txtTitle.setText(metaData.getTitle());


                }
                txtFile.setText((file.getName()));
            }
    }

    //Save button to save the new songs
    public void onActionSave(ActionEvent event) throws Exception {
        //create a song and collect data from different user input field
        songModel.createSong(txtTitle.getText(), txtArtist.getText(), txtTime.getText(), btnGenre.getText(), txtFile.getText());

        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        int time = Integer.parseInt(txtTime.getText());
        String file = txtFile.getText();

        Song newSong = new Song(title,artist,time,file,btnGenre.getText());
        mainController.addSongToTable(newSong);

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }

    public void onActionCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void OnActionGenre(ActionEvent event) {
        //Takes the selected genre in the menu item, and
        MenuItem selectedGenre = (MenuItem) event.getSource();
            String genreName = selectedGenre.getText();
            btnGenre.setText(genreName);
        }
}

