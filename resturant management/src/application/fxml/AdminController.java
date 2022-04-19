package application.fxml;

import application.*;

import static application.Resturant_management.Role;
import static application.fxml.Log_inController.users;
import static application.fxml.Order1Controller.foods;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BinaryOperator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.html.ImageView;

public class AdminController implements Initializable {

    private ArrayList<Resources> Resources;
    @FXML
    private Label income;
    @FXML
    private VBox rootPane;
    @FXML
    private SplitPane rootSplit;
    @FXML
    private StackPane st;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private Button order;
    @FXML
    private Button club;
    @FXML
    private Button about;
    @FXML
    private Button management;
    @FXML
    private Button tracking;
    @FXML
    private TextField name_user;
    @FXML
    private ComboBox<?> role;
    @FXML
    private ListView<?> list_user;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextArea tozih;////////////////////////////
    @FXML
    private TextField fee;///////////////////////////////////
    @FXML
    private ListView<?> list_cash;///////////////////////////////
    @FXML
    private TextArea riz_daramad;//////////////////////////////////
    @FXML
    private ListView<?> Sources;
    @FXML
    private TextField name_resource;
    @FXML
    private TextField amount_resource;
    @FXML
    private TextField food_price;
    @FXML
    private TextField name_food;
    private pagechenge page_change;
    String ww = "";
    ObservableList<String> Choice_list;
    @FXML
    private Tab user_tab;
    @FXML
    private Tab mali_tab;
    @FXML
    private Tab food_tab;
    int income_mon = 0;
    int out_mon = 0;
    int ttl = 0;
    ObservableList user_oblist;
    ObservableList Resource_oblist;
    ObservableList Food_oblist;
    ObservableList cash_oblist;
    ObservableList out_oblist;
    @FXML
    private ListView<?> food_listV;
    @FXML
    private MenuItem aboutt;
    @FXML
    private Button add_food;
    @FXML
    private Button add_resource;
    @FXML
    private CheckBox edit_out;
    @FXML
    private ListView list_out; ////////////////////////////////
    @FXML
    private Label out_total_text;
    @FXML
    private Label ttl_income;
    @FXML
    private Label out_total;////////////////////////////////////
    @FXML
    private MenuItem exit_button;
    @FXML
    private MenuItem report_btn;
    @FXML
    private MenuItem user_chg;
    @FXML
    private MenuItem reset_btn;
    @FXML
    private Button out_button;
    FileInputStream fis;
    ObjectInputStream ois;
    private ArrayList<Orders> orderlist;
    private ArrayList<mali> outmoney_list;
    private ArrayList<customer> customerList;
    SimpleDateFormat formatter;
    Date date;
    static String report="";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //value = FXCollections.observableArrayList();
        riz_daramad.setEditable(false);
        page_change = new pagechenge();
        Resources = new ArrayList<>();
        orderlist = new ArrayList<>();
        outmoney_list = new ArrayList<>();
        //foods = new ArrayList<>();
        Choice_list = (ObservableList<String>) role.getItems();
        Choice_list.add("انباردار");
        Choice_list.add("کاربر");
        Choice_list.add("مدیر");
        formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
        date = new Date();
        role.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                ww = Choice_list.get(new_value.intValue());
            }
        });
        if (Role.equals("انباردار")) {
            order.setVisible(false);
            club.setVisible(false);
            tracking.setVisible(false);
            food_tab.setDisable(true);
            mali_tab.setDisable(true);
            user_tab.setDisable(true);

        }
        user_oblist = FXCollections.observableArrayList();
        Resource_oblist = FXCollections.observableArrayList();
        Food_oblist = FXCollections.observableArrayList();
        cash_oblist = FXCollections.observableArrayList();
        out_oblist = FXCollections.observableArrayList();
        list_cash.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList cash_selected = list_cash.getSelectionModel().getSelectedIndices();
                for (Object o : cash_selected) {
                    riz_daramad.setText(orderlist.get((int) o).getditile());
                }
            }
        });
        list_out.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList cash_selected = list_out.getSelectionModel().getSelectedIndices();
                for (Object o : cash_selected) {
                    riz_daramad.setText(outmoney_list.get((int) o).getTozih());
                }
            }
        });
        if (!users.isEmpty()) {
            for (Users o : users) {
                user_oblist.add(o);
            }
        }
        food_listV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ObservableList food_selected = food_listV.getSelectionModel().getSelectedIndices();
                for (Object o : food_selected) {
                    food_price.setText(foods.get((int) o).getPrice() + "");
                    name_food.setText(foods.get((int) o).getName() + "");
                    add_food.setText("ویرایش");
                }
            }
        });
        Sources.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ObservableList Sources_selected = Sources.getSelectionModel().getSelectedIndices();
                for (Object o : Sources_selected) {
                    name_resource.setText(Resources.get((int) o).getName() + "");
                    amount_resource.setText(Resources.get((int) o).getAmount() + "");
                    add_resource.setText("ویرایش");
                }
            }
        });
        edit_out.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    out_button.setVisible(true);
                    tozih.setVisible(true);
                    list_out.setVisible(false);
                    fee.setVisible(true);
                    out_total.setVisible(false);
                    out_total_text.setVisible(false);
                } else {
                    out_button.setVisible(false);
                    tozih.setVisible(false);
                    list_out.setVisible(true);
                    fee.setVisible(false);
                    out_total.setVisible(true);
                    out_total_text.setVisible(true);
                }
            }
        });
        fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    fee.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        amount_resource.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    amount_resource.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        food_price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    food_price.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        try {
            fis = new FileInputStream("Resources.txt");
            ois = new ObjectInputStream(fis);
            Resources = (ArrayList<Resources>) ois.readObject();
            ois.close();
            fis = new FileInputStream("customer.txt");
            ois = new ObjectInputStream(fis);
            customerList = (ArrayList<customer>) ois.readObject();
            ois.close();
            fis = new FileInputStream("out_money.txt");
            ois = new ObjectInputStream(fis);
            outmoney_list = (ArrayList<mali>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        update_Resourcelist();
        for (mali t:outmoney_list) {
            out_mon+=t.getMoney();
        }
        int count_food= 0;
        out_total.setText(""+out_mon);
        for (customer t:customerList) {
            for (Orders p : t.getOrderse()) {
                orderlist.add(p);
                for (Foods e : p.getFoods()) {
                    count_food++;
                    income_mon+=e.getPrice();
                }
            }
        }
        int t = customerList.size()-1;
        report ="";
        report+="تعداد کل مشتریان: "+t;
        report+="\n"+"تعداد سفارشات: "+orderlist.size();
        report+="\n"+"تعداد غذای فروخته شده: "+count_food;
        System.out.println(report);
        ttl_income.setText(income_mon-out_mon+"");
        if (!Role.equals("انباردار")) {
            update_userlist();
            update_foodlist();
            update_cashlist();
            update_outmoney();
        }
    }
    @FXML
    private void sabt(ActionEvent event) {
        try {
            Users a = null;
            if (!(name_user.getText().isEmpty()) && !(username.getText().isEmpty()) && !(password.getText().isEmpty()) && !(ww == "")) {
                a = new Users(name_user.getText(), username.getText(), password.getText(), ww);
            }
            if (a != null) {
                //user_oblist.clear();
                users.add(a);
                update_userlist();
//                for (Users o : users) {
//                    user_oblist.add(o.toString());
//                }
//                list_user.setItems(user_oblist);
            }
            //save to file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void remove_user(ActionEvent event) {
        ObservableList selected = list_user.getSelectionModel().getSelectedIndices();
        for (Object o : selected) {
            if ((int)o != 0) {
                System.out.println(users.remove((int) o).getName());
            }
        }
        update_userlist();
    }
    @FXML
    private void pay_edit(ActionEvent event) {///////////////////////////////////////////
        date.setTime(System.currentTimeMillis());
        mali m =new mali(tozih.getText(),Integer.parseInt(fee.getText()),formatter.format(date));
        outmoney_list.add(m);
        out_mon+=m.getMoney();
        update_outmoney();
    }
    @FXML
    private void exit(ActionEvent event){
        Runtime.getRuntime().halt(0);
    }
    @FXML
    private void add_resource(ActionEvent event) {
        try {
            if (add_resource.getText() == "ویرایش") {
                ObservableList Sources_selected = Sources.getSelectionModel().getSelectedIndices();
                for (Object o : Sources_selected) {
                    Resources.get((int) o).setName(name_resource.getText());
                    Resources.get((int) o).setAmount(Integer.parseInt(amount_resource.getText()));
                }
                update_Resourcelist();
            } else {
                Resources a = null;
                if (!(name_resource.getText().isEmpty()) && !(amount_resource.getText().isEmpty())) {
                    a = new Resources(name_resource.getText(), Integer.parseInt(amount_resource.getText()));
                }
                if (a != null) {
                    //Resource_oblist.clear();
                    Resources.add(a);
                    update_Resourcelist();
//                for (Resources o : Resources) {
//                    Resource_oblist.add(o.toString());
//                }
//                Sources.setItems(Resource_oblist);
                }
            }
            name_resource.clear();
            amount_resource.clear();
            add_resource.setText("اضافه");
            //save to file
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        dialogLayout.setBody(new Text("Help & Support:\nAmir HassanEbrahimi: amir.hebrahimi244@gmail.com\nAli Moshrefi: ip6217@yahoo.com"));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent even)-> rootSplit.setEffect(null)
        );
        rootSplit.setEffect(blur);
    }
    @FXML
    private void reset_btn(ActionEvent event) {
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout= new JFXDialogLayout();
        JFXButton button = new JFXButton("Yes");
        button.getStyleClass().add("dialog-button1");
        JFXDialog dialog = new JFXDialog(st, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseevent )-> reset());
        dialogLayout.setBody(new Text("آیا از انجام این کار اطمینان دارید؟"));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent even)-> rootSplit.setEffect(null)
        );
        rootSplit.setEffect(blur);
    }
    @FXML
    private void user_chg(ActionEvent event) {
        Stage primaryStage=(Stage) add_food.getScene().getWindow();
        page_change.firstpage(primaryStage);
    }
    @FXML
    private void report_btn(ActionEvent event) {
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout= new JFXDialogLayout();
        JFXButton button = new JFXButton("Close");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(st, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseevent )-> dialog.close()
        );
        dialogLayout.setBody(new Text(report));

        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent even)-> rootSplit.setEffect(null)
        );
        rootSplit.setEffect(blur);
    }
    @FXML
    private void remove_resource(ActionEvent event) {
        ObservableList selected = Sources.getSelectionModel().getSelectedIndices();
        for (Object o : selected) {
            System.out.println(Resources.remove((int) o).getName());
        }
        update_Resourcelist();
        name_resource.clear();
        amount_resource.clear();
        add_resource.setText("اضافه");
    }
    @FXML
    private void add_food(ActionEvent event) {
        try {
            if (add_food.getText() == "ویرایش") {
                ObservableList food_selected = food_listV.getSelectionModel().getSelectedIndices();
                for (Object o : food_selected) {
                    foods.get((int) o).setName(name_food.getText());
                    foods.get((int) o).setPrice(Integer.parseInt(food_price.getText()));
                }
                update_foodlist();
            } else {
                Foods a = null;
                if (!(name_food.getText().isEmpty()) && !(food_price.getText().isEmpty())) {
                    a = new Foods(name_food.getText(), Integer.parseInt(food_price.getText()));
                }
                if (a != null) {
                    foods.add(a);
                    update_foodlist();

                }
            }
            name_food.clear();
            food_price.clear();
            add_food.setText("اضافه");
            //save to file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void remove_food(ActionEvent event) {
        ObservableList selected = food_listV.getSelectionModel().getSelectedIndices();
        for (Object o : selected) {
            System.out.println(foods.remove((int) o).getName());
        }
        update_foodlist();
        name_food.clear();
        food_price.clear();
        add_food.setText("اضافه");
    }

    @FXML
    private void order_btn(ActionEvent event) {
        page_change.chengepage(order, "order1.fxml");
    }
    @FXML
    private void tracking_btn(ActionEvent event) {
        page_change.chengepage(tracking, "tracking.fxml");
    }
    @FXML
    private void club_btn(ActionEvent event) { Order1Controller.from_order=false;
    page_change.chengepage(club, "club.fxml");
    }
    @FXML

    private void update_userlist() {
        user_oblist.clear();
        for (Users o : users) {
            user_oblist.add(o.toString());
        }
        list_user.setItems(user_oblist);
    }
    private void update_Resourcelist() {
        Resource_oblist.clear();
        for (Resources o : Resources) {
            Resource_oblist.add(o.toString());
        }
        Sources.setItems(Resource_oblist);
    }
    private void update_cashlist() {
        cash_oblist.clear();

        for (Orders o : orderlist) {
            cash_oblist.add(o.toString());
        }
        income.setText(income_mon+"");
        list_cash.setItems(cash_oblist);
        ttl_income.setText(income_mon-out_mon+"");
    }
    private void update_outmoney() {
        out_oblist.clear();
        for (mali o : outmoney_list) {
            out_oblist.add(o.toString());
        }
        out_total.setText(out_mon+"");
        list_out.setItems(out_oblist);
        ttl_income.setText(income_mon-out_mon+"");
    }
    private void update_foodlist() {
        Food_oblist.clear();
        for (Foods o : foods) {
            Food_oblist.add(o.toString());
        }
        food_listV.setItems(Food_oblist);
    }

    @FXML
    private void save(ActionEvent event) {
        if (Role.equals("انباردار")){
            write_file(Resources, "Resources");
        }else {
            write_file(Resources, "Resources");
            write_file(users, "useers");
            write_file(foods, "foods");
            write_file(outmoney_list, "out_money");
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
    private void reset(){
        Resources = new ArrayList<>();
        users = new ArrayList<>();
        foods = new ArrayList<>();
        outmoney_list = new ArrayList<>();
        customerList = new ArrayList<>();
        users.add(new Users("admin","admin","admin","مدیر"));
        customerList.add(new customer("0","0","0"));////////////////////
        write_file(Resources, "Resources");
        write_file(users, "useers");
        write_file(foods, "foods");
        write_file(customerList, "customer");
        write_file(outmoney_list, "out_money");
        try {
            FileWriter  fileWriter = new FileWriter("traking.txt");
            fileWriter.write(1+"");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage=(Stage) add_food.getScene().getWindow();
        page_change.firstpage(primaryStage);

    }

}