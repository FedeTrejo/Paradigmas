package nemo;

import java.util.ArrayList;

public class Nemo {
    public static ArrayList<Integer> coords = new ArrayList<Integer>(2);
    public static int depth;
    public static String direction;

    public Nemo(){
        coords.add(0);
        coords.add(0);
        depth = 0;
        direction = "north";
    }

    public static Integer getCoordX() {
        return coords.get(0);
    }
    public static Integer getCoordY() {
        return coords.get(1);
    }

    public Nemo sendCommand(String command){
        return this;
    }

}