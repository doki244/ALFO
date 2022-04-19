package application.fxml;
import application.Orders;
import application.customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static application.Resturant_management.Role;

public class trackingController implements Initializable {

    @FXML
    private SplitPane rootSplit;
    @FXML
    private StackPane st;

    @FXML
    private JFXButton management;

    @FXML
    private JFXButton order;

    @FXML
    private JFXButton tracking;@FXML
    private VBox buttonbox;

    @FXML
    private JFXButton club;

    @FXML
    private TextArea trackdet;

    @FXML
    private JFXButton printbtn;

    @FXML
    private pagechenge page_change;

    @FXML
    private JFXTextField trackcode;

    @FXML
    private MenuItem exit_button;

    @FXML
    private JFXButton trackbtn;
    private ArrayList<customer> customerList;
    FileInputStream fis;
    ObjectInputStream ois;
    @FXML
    void club_btn(ActionEvent event) {
        Order1Controller.from_order=false;
        page_change.chengepage(club, "club.fxml");
    }
    @FXML
    private void management_btn(ActionEvent event) {
        page_change.chengepage(management, "admin.fxml");
    }
    @FXML
    private void order_btn(ActionEvent event) {
        page_change.chengepage(order, "order1.fxml");
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
    void tracking_btn(ActionEvent event) {
        page_change.chengepage(order, "tracking.fxml");
    }
    @FXML
    void tracksearch(ActionEvent event) {
        for (customer t:customerList) {
            for (Orders p:t.getOrderse()) {
                if (p.getTracking_num()==Integer.parseInt(trackcode.getText().toString())){
                    trackdet.setText(p.toString()+"\n"+p.getditile());
                }
            }
        }
    }
    @FXML
    private void exit(ActionEvent event){
        Runtime.getRuntime().halt(0);
    }

        @Override
    public void initialize(URL url, ResourceBundle rb) {
            if (Role.equals("کاربر")) {
                //management.setVisible(false);
                buttonbox.getChildren().remove(management);
                //management.setDisable(false);

            }
        trackdet.setEditable(false);
        try {
            fis = new FileInputStream("customer.txt");
            ois = new ObjectInputStream(fis);
            customerList = (ArrayList<customer>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        page_change = new pagechenge();
    }
}