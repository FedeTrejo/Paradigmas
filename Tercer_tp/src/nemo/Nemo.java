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
    public static Integer getDepth() {
        return depth;
    }

    private static void moveLeft(){
        if (direction == "north"){
            direction = "west";
        }
        else if (direction == "west"){
            direction = "south";
        }
        else if (direction == "south"){
            direction = "east";
        }
        else if (direction == "east"){
            direction = "north";
        }
    }

    private static void moveRight(){
        if (direction == "north"){
            direction = "east";
        }
        else if (direction == "east"){
            direction = "south";
        }
        else if (direction == "south"){
            direction = "west";
        }
        else if (direction == "west"){
            direction = "north";
        }
    }

    private static void descend(){
        depth -= 1;
    }
    private static void undescend(){
        if (depth == 0){
            depth = 0;
        }
        else {
            depth += 1;
        }
    }
    public Nemo sendCommand(String command){
        for(char caracter : command.toCharArray()){
            if (caracter == 'd'){
                descend();
            }
            else if (caracter == 'u'){
                undescend();
            }
            else if (caracter == 'l'){}
            else if (caracter == 'r'){}
            else if (caracter == 'm'){}
        }
        return this;
    }

}