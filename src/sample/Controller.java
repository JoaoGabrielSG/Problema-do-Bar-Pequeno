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
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.animation.Animation;
import javafx.util.Duration;
import javax.swing.plaf.RootPaneUI;

public class Controller{
    @FXML
    private Button myButton;

    @FXML
    private Button test;

    @FXML
    private Circle italo;
    @FXML
    private Circle yuri;

    public Animations animation = new Animations() {
        @Override
        public void goBar(String name) {
            System.out.print("Comecando a beber:" + name + "\n");
            yuri.setTranslateX(100);
        }

        @Override
        public void goHome(String name) {

            System.out.print("Esta indo para casa:" + name + "\n");

            yuri.setTranslateX(200);
        }
    };

    @FXML
    public void initialize() {

        ClientThread cliente = new ClientThread("italo", 2000, 2000, animation);

        ClientThread cliente2 = new ClientThread("yuri", 500, 500, animation);

        myButton.setOnAction((ActionEvent event) -> {
            cliente.start();
        });

        test.setOnAction((ActionEvent event) -> {
            cliente2.start();
        });
    }

//    public void goBar(){
////        FadeTransition ft;
////        ft = new FadeTransition(Duration.millis(3000), yuri);
////        ft.setFromValue(1.0);
////        ft.setToValue(100);
////        ft.setCycleCount(Timeline.INDEFINITE);
////        ft.setAutoReverse(true);
////        ft.play();
//    }
}
