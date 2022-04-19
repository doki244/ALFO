package application.fxml;

import application.Foods;
import application.Resources;
import static application.Resturant_management.Role;

import application.Resturant_management;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Order1Controller implements Initializable {

    
    public static ArrayList<Foods> foods;
    public static boolean from_order=false;
    @FXML
    private VBox rootPane;
    @FXML
    private VBox buttonbox;
    @FXML
    private SplitPane rootSplit;
    @FXML
    private StackPane st;
    @FXML
    private Button about;
    @FXML
    private Button management;
    @FXML
    private Button tracking;
    @FXML
    public ListView<?> order_list;
    @FXML
    private Button exit;
    @FXML
    private ListView<?> menu;
    @FXML
    private MenuItem exit_button;

    private pagechenge page_change;
    ObservableList Food_oblist;
    ObservableList order_oblist;
    @FXML
    private Button order;
    @FXML
    private Button club;
    public static ArrayList<Foods> order_array;
    FileInputStream fis;
    ObjectInputStream ois;
    @FXML
    private Button sabt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        page_change = new pagechenge();
        if (Role.equals("کاربر")) {
            //management.setVisible(false);
            buttonbox.getChildren().remove(management);
            //management.setDisable(false);

        }
        order_array = new ArrayList<>();
        Food_oblist = FXCollections.observableArrayList();
        order_oblist = FXCollections.observableArrayList();
        try {
            fis= new FileInputStream("foods.txt");
            ois = new ObjectInputStream(fis);
            foods = (ArrayList<Foods>) ois.readObject();
            
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        update_foodlist();
    }

    @FXML
    private void Confirmation(ActionEvent event) {
        from_order=true;
        page_change.chengepage(sabt, "club.fxml");

    }

    @FXML
    private void remove_btn(ActionEvent event) {
        ObservableList selected = order_list.getSelectionModel().getSelectedIndices();
        for (Object o : selected) {
            order_array.remove(order_array.get((int) o));
        }
        update_orderlist();
    }

    @FXML
    private void exit(ActionEvent event){
        Runtime.getRuntime().halt(0);
    }

    @FXML
    private void add_btn(ActionEvent event) {
        ObservableList selected = menu.getSelectionModel().getSelectedIndices();
        for (Object o : selected) {
            order_array.add(foods.get((int) o));
        }
        update_orderlist();

    }

    @FXML
    private void management_btn(ActionEvent event) {
        page_change.chengepage(management, "admin.fxml");
    }

    @FXML
    private void about_btn(ActionEvent event) {
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout= new JFXDialogLayout();
        JFXButton button = new JFXButton("Close");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(st, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseevent )-> dialog.close()
        );
        dialogLayout.setBody(new Text("Help & Support:\nAmir HassanEbrahimi: amirebrahimi244@gmail.com\nAli Moshrefi: ip6217@yahoo.com"));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent even)-> rootSplit.setEffect(null)
        );
        rootSplit.setEffect(blur);
    }

    @FXML
    private void tracking_btn(ActionEvent event) { page_change.chengepage(tracking, "tracking.fxml");
    }

    private void update_foodlist() {
        Food_oblist.clear();
        //users.add(a);
        for (Foods o : foods) {
            Food_oblist.add(o.toString());
        }
        menu.setItems(Food_oblist);
    }

    private void update_orderlist() {
        order_oblist.clear();
        //users.add(a);
        for (Foods o : order_array) {
            order_oblist.add(o.toString());
        }
        order_list.setItems(order_oblist);
    }
    @FXML
    private void club_btn(ActionEvent event) {
        from_order=false;
        page_change.chengepage(club, "club.fxml");
    }

}
