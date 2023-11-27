package dk.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label lbl;

    @FXML
    private void clickbtn(ActionEvent actionEvent) {
        lbl.setText("Dav du!");
    }
}
