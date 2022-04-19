package application.fxml;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class pagechenge {

    public void chengepage(Button button, String add) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage;
                if (event.getSource() == button) {                        
                    stage = (Stage) button.getScene().getWindow();
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource(add));
                        Scene newScene = new Scene(parent);
                        stage.setScene(newScene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void firstpage(Stage primaryStage){
        Parent parent;
        Scene a;
        try {
            parent = FXMLLoader.load(getClass().getResource("login.fxml"));
            a = new Scene(parent);
            primaryStage.setScene(a);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
