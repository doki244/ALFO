package application.fxml;

import application.Foods;
import application.Orders;
import application.customer;

import static application.Resturant_management.Role;

import static application.fxml.Order1Controller.order_array;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.*;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class ClubController implements Initializable {

    @FXML
    private VBox rootPane;
    @FXML
    private StackPane st;
    @FXML
    private SplitPane rootSplit;
    @FXML
    private TextField phone;
    @FXML
    private TextField name;
    @FXML
    private ListView<?> order_list;
    @FXML
    private TextArea order_detail;
    @FXML
    private TextArea address;
    @FXML
    private Button finish;
    @FXML
    private Button management;
    @FXML
    private Button club;
    @FXML
    private Button about;
    @FXML
    private Button tracking;
    @FXML
    private Button order;
    private ArrayList<customer> customerList;
    FileInputStream fis;
    ObjectInputStream ois;
    ObservableList order_oblist;
    private customer i ;
    private customer c = null;
    @FXML
    private Label detail;
    private pagechenge page_change;
    String ww = "";
    @FXML
    private CheckBox register;
    @FXML
    private VBox vbox;
    @FXML
    private MenuItem exit_button;
    //private ArrayList<> customer
    static int traking;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i = null;
        if (Role.equals("کاربر")) {
            //management.setVisible(false);
            //management.setDisable(false);
            vbox.getChildren().remove(management);
        }
        finish.setVisible(true);
        page_change = new pagechenge();
        //register.setVisible(false);
        order_detail.setEditable(false);
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    phone.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        order_oblist = FXCollections.observableArrayList();
        if (Order1Controller.from_order) {
            detail.setVisible(false);
            order_detail.setVisible(false);
            //lable.setVisible(false);
            for (Foods o : order_array) {
                order_oblist.add(o.toString());
            }
            order_list.setItems(order_oblist);

        } else {
            finish.setVisible(false);
            register.setVisible(false);
        }
        name.setEditable(false);
        address.setEditable(false);
        register.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    name.setEditable(true);
                    address.setEditable(true);
                    finish.setText("سفارش و ثبت نام");
                } else {
                    finish.setText("سفارش");
                    name.setText("");
                    address.setText("");
                    name.setEditable(false);
                    address.setEditable(false);
                }
            }
        });
        order_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!Order1Controller.from_order && i != null) {
                    ObservableList selected = order_list.getSelectionModel().getSelectedIndices();
                    for (Object o : selected) {
                        order_detail.clear();
                        order_detail.setText(i.getOrderse().get((int) o).getditile());
                    }
                }
            }
        });
        try {
            fis = new FileInputStream("customer.txt");
            ois = new ObjectInputStream(fis);
            customerList = (ArrayList<customer>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (customer a : customerList) {
            if (a.getPhone().equals("0")) {
                c=a;
                break;
            }
        }
    }
    @FXML
    private void search_customer(ActionEvent event) {
        address.setText("");
        name.setText("");
        for (customer a : customerList) {
            if (a.getPhone().equals("09" + phone.getText())) {
                i = a;
                address.setText(a.getAddress());
                name.setText(a.getName());
                address.setEditable(false);
                name.setEditable(false);
                if (!Order1Controller.from_order) {
                    order_oblist.clear();
                    for (Orders o : a.getOrderse()) {
                        order_oblist.add(o.toString());
                    }
                    order_list.setItems(order_oblist);
                }
            }else{
                i=null;
            }
        }
        if (i == null) {
            //register.setVisible(true);
        }
    }
    @FXML
    private void management_btn(ActionEvent event) { page_change.chengepage(management, "admin.fxml");
    }
    @FXML
    private void tracking_btn(ActionEvent event) { page_change.chengepage(tracking , "tracking.fxml");
    }
    @FXML
    private void club_btn(ActionEvent event) {
        Order1Controller.from_order=false;
        page_change.chengepage(club, "club.fxml");
    }
    @FXML
    private void order_btn(ActionEvent event) {
        page_change.chengepage(order, "order1.fxml");
    }
    @FXML
    private void exit(ActionEvent event){
        Runtime.getRuntime().halt(0);
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
    private void finish(ActionEvent event) {
        String data = null;
        try {
            File myObj = new File("traking.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                //System.out.println(data);
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        traking= Integer.parseInt(data);
        System.out.println(traking);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        int flag = 0;
        for (customer a : customerList) {
            if (a.getPhone().equals("09" + phone.getText())) {
                flag=1;
            }
        }
        if (finish.getText().equals("سفارش و ثبت نام")) {
            if (i!=null || flag==1){
                BoxBlur blur = new BoxBlur(3,3,3);
                JFXDialogLayout dialogLayout= new JFXDialogLayout();
                JFXButton button = new JFXButton("Close");
                button.getStyleClass().add("dialog-button");
                JFXDialog dialog = new JFXDialog(st, dialogLayout, JFXDialog.DialogTransition.TOP);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseevent )-> dialog.close()
                );
                dialogLayout.setBody(new Text("مشتری قبلا ثبت نام کرده است"));
                dialogLayout.setActions(button);
                dialog.show();
                dialog.setOnDialogClosed((JFXDialogEvent even)-> rootSplit.setEffect(null)
                );
                rootSplit.setEffect(blur);

            }else {
                customer t = new customer(name.getText(), "09" + phone.getText(), address.getText());
                t.getOrderse().add(new Orders(order_array, traking, formatter.format(date)));
                customerList.add(t);
            }
        } else {
            flag=0;
            if (i==null){
                c.getOrderse().add(new Orders(order_array, traking, formatter.format(date)));
                i=c;
            }else
                i.getOrderse().add(new Orders(order_array, traking, formatter.format(date)));
        }
        if (flag==0){
            write_file(customerList, "customer");
            BoxBlur blur = new BoxBlur(3,3,3);
            JFXDialogLayout dialogLayout= new JFXDialogLayout();
            JFXButton button = new JFXButton("Close");
            button.getStyleClass().add("dialog-button");
            JFXDialog dialog = new JFXDialog(st, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseevent )-> dialog.close()
            );
            dialogLayout.setBody(new Text("ثبت شد \n کد رهگیری:"+traking));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent even)-> rootSplit.setEffect(null)
            );
            rootSplit.setEffect(blur);
            finish.setVisible(false);
            try {
                traking++;
                FileWriter fileWriter = new FileWriter("traking.txt");
                fileWriter.write(traking+"");
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean write_file(ArrayList arrayList, String file_name) {
        try {
            //Write arraylist to file.
            FileOutputStream fos = new FileOutputStream(file_name + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayList);
            oos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
