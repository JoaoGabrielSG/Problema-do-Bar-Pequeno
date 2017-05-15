package sample;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javax.swing.plaf.RootPaneUI;

public class Controller{
    @FXML
    private Button myButton;

    @FXML
    private Button test;

    @FXML
    public void initialize() {

        ClientThread cliente = new ClientThread("italo", 5, 5);

        ClientThread cliente2 = new ClientThread("yuri", 5, 5);

        myButton.setOnAction((ActionEvent event) -> {
            cliente.start();
        });

        test.setOnAction((ActionEvent event) -> {
            cliente2.start();
        });
    }
}
