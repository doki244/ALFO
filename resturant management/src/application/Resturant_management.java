package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Resturant_management extends Application {

    public static String Role;
    @Override
    public void start(Stage primaryStage) {
        Parent parent;
        Scene a;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            a = new Scene(parent);
            primaryStage.setScene(a);
            primaryStage.setResizable(false);
            Image image = new Image("/Alfo_logo_app.png");
            primaryStage.getIcons().add(image);
            primaryStage.setTitle("Alfo");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception {


        launch(args);
    }

}
