package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable{
    private ArrayList<Foods> foods;
    private String time;
    private int tracking_num;

    public Orders(ArrayList<Foods> foods, int tracking_num, String time) {
        this.foods = foods;
        this.time = time;
        this.tracking_num = tracking_num;
    }

    public ArrayList<Foods> getFoods() {
        return foods;
    }

    public int getTracking_num() {
        return tracking_num;
    }

    @Override
    public String toString() {

        return time+" code:" + tracking_num ;//time
    }
    public String getditile(){
        String foo="";
        for (Foods f:foods) {
            foo+=f.toString();
        }
        return foo;
    }
    
}
