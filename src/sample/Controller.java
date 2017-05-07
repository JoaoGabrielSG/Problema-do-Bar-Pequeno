package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Controller{
    @FXML private Button myButton;

    @FXML
    public void initialize() {

    myButton.setDisable(true);

    }
}
