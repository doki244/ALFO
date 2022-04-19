package application;

import java.io.Serializable;

public class mali implements Serializable {
    private String tozih;
    private int money;
    private String time;
    public mali(String tozih, int money,String time) {
        this.tozih = tozih;
        this.money = money;
        this.time=time;
    }
    public String getTozih() {
        return tozih;
    }
    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "-"+ money+" in "+time;
    }
}
