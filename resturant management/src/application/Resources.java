package application;

import java.io.Serializable;


public class Resources implements Serializable{
    private String name;
    private int amount;

    public Resources(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return  name + " , مقدار=" + amount ;
    }
    
}
