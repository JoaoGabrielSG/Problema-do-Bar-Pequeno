package sample;

import java.awt.*;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.animation.Animation;
import javafx.util.Duration;
import javax.swing.plaf.RootPaneUI;

public class Controller{

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button myButton;

    @FXML
    private javafx.scene.control.TextField name;

    @FXML
    private javafx.scene.control.TextField tempoCasa;

    @FXML
    private javafx.scene.control.TextField tempoBar;

    public Animations animation = new Animations() {

        @Override
        public void goLine(ClientThread client) {
            int clientsInBar = ClientThread.waiting;

            client.clienteSprite.setTranslateX(BarPositions.values()[clientsInBar].x);
            client.clienteSprite.setTranslateY(BarPositions.values()[clientsInBar].y);
        }

        @Override
        public void goBar(ClientThread client) {
            System.out.print("Comecando a beber:" + client.getName() + client.clienteSprite.getTranslateX() + "\n");

            int clientsInBar = ClientThread.eating;

            System.out.print(clientsInBar + "\n");

            client.clienteSprite.setTranslateX(BarPositions.values()[clientsInBar].x);
            client.clienteSprite.setTranslateY(BarPositions.values()[clientsInBar].y);


            System.out.print(client.getName() + BarPositions.values()[clientsInBar].x + "\n");
        }

        @Override
        public void goHome(ClientThread client) {

//            System.out.print("Esta indo para casa:" + client.getName() + "\n");

            client.clienteSprite.setTranslateX(300.0);
        }
    };

    @FXML
    public void initialize() {

        myButton.setOnAction((ActionEvent event) -> {
            ClientThread cliente = new ClientThread(name.getText(), Integer.parseInt(tempoBar.getText()), Integer.parseInt(tempoCasa.getText()), animation);

            cliente.start();

            anchor.getChildren().add(cliente.clienteSprite);
        });
    }
}
