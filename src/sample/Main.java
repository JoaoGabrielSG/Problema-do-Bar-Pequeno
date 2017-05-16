package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import barproblem.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Bar bar = new Bar(5);



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1500, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {launch(args);}
}
