package application.fxml;
import static application.Resturant_management.Role;
import application.Foods;
import application.Resources;
import application.Users;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.management.relation.Role;

public class Log_inController implements Initializable,Serializable {


    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public static ArrayList<Users> users;
    FileInputStream fis;
    ObjectInputStream ois;
    private pagechenge page_change;
    @FXML
    private Button changepage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        page_change = new pagechenge();
        try {
            fis= new FileInputStream("useers.txt");
            ois = new ObjectInputStream(fis);
            users = (ArrayList<Users>) ois.readObject();
            
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
    }
    
    @FXML
    private void changepage(ActionEvent event) {
        for (Users user : users) {
            if (user.getName().equals(username.getText()) && user.getPassword().equals(password.getText()) ) {
                Role =user.getRole();
                System.out.println(Role);
                if (Role.equals("انباردار")) {
                    page_change.chengepage(changepage, "admin.fxml");
                }else
                page_change.chengepage(changepage, "order1.fxml");
            }
        }
    }

}
