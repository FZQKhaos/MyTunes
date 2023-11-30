package dk.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewSongController {

    @FXML
    public Button btnCancel;

    public void onActionChoose(ActionEvent event) {
        Stage stage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", ".wav", ".mp3"));
        fileChooser.showOpenDialog(stage);
    }

    public void onActionSave(ActionEvent event) {
    }

    public void onActionCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
