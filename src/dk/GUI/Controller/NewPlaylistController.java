package dk.GUI.Controller;

import dk.BE.Playlist;
import dk.GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistController implements Initializable{

    @FXML
    public TextField txtPlaylistName;
    @FXML
    public Button btnSave, btnCancel;

    private PlaylistModel playlistModel;

    private MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            playlistModel = new PlaylistModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void onActionSavePlaylist(ActionEvent event) {
        //get data from ui
        if (txtPlaylistName.getText().isEmpty()){
            return;
        }
        String name = txtPlaylistName.getText();
        int time = 0;
        int songs = 0;
        playlistModel.createPlaylist(name, time, songs);

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();

        Playlist playlist = new Playlist(name, time, songs);
        mainController.addPlaylistToTable(playlist);
    }

    public void onActionCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
