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
/*
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Audio Files", ".wav", ".mp3"),

        File selectedFile = fileChooser.showOpenDialog();
        if (selectedFile != null) {
            mainStage.display(selectedFile);
        }*/
    }

    public void onActionSave(ActionEvent event) {
    }

    public void onActionCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
