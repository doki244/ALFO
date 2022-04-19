package application;

import java.io.Serializable;
import java.util.ArrayList;

public class customer implements Serializable{

    private String name;
    private String phone;
    private String address;
    private ArrayList<Orders> orderse;

    public customer(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        orderse = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Orders> getOrderse() {
        return orderse;
    }

    @Override
    public String toString() {
        return "customer{" + "name=" + name + ", phone=" + phone + ", orderse=" + orderse + '}';
    }
}
