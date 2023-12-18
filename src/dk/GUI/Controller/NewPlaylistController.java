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
    private Button btnSave, btnCancel;

    private PlaylistModel playlistModel;

    private MainController mainController;
    private Playlist txtPlaylistToEdit;

    private boolean shouldEdit = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            playlistModel = new PlaylistModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void setShouldEdit(boolean shouldEdit, Playlist playlist) {
        this.shouldEdit = shouldEdit;
        txtPlaylistToEdit = playlist;
    }

    public void onActionSavePlaylist(ActionEvent event) {
        String name = txtPlaylistName.getText();

        //get data from ui
        if (txtPlaylistName.getText().isEmpty()){
            return;
        }

        Stage stage = (Stage) btnSave.getScene().getWindow();


    //To save and update the edited name of the playlist, if edit playlist btn is selected
        if (shouldEdit){
            playlistModel.updatePlaylist(txtPlaylistToEdit, name);
            mainController.updatePlaylistTable();
            stage.close();
            return;
        }

        playlistModel.createPlaylist(name);

        Playlist playlist = new Playlist(name);
        mainController.addPlaylistToTable(playlist);
        stage.close();
    }

    public void onActionCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
