package dk.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewSongController implements Initializable {

    @FXML
    public Button btnCancel;
    @FXML
    public TextField txtTitle, txtArtist, txtTime, txtFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionChoose(ActionEvent event) {
        Stage stage = new Stage();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Audio Files", ".wav", ".mp3"));
                    //new FileChooser.ExtensionFilter("PDF Files", ".pdf"));

            File file = fileChooser.showOpenDialog(stage);

            if (file != null){
                txtFile.setText(file.getAbsolutePath());
            }

    }

    public void onActionSave(ActionEvent event) {
    }

    public void onActionCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }


}
