package nemo;

import java.util.ArrayList;

public class Submarine {
    private ArrayList<Integer> coords = new ArrayList<Integer>(2);
    private int depth;
    private String direction;
    public boolean capsuleReleased = false;

    public Submarine(){
        coords.add(0);
        coords.add(0);
        depth = 0;
        direction = "north";
    }

    private  void moveLeft(){
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

    private  void moveRight(){
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

    private  void descend(){
        depth -= 1;
    }
    private  void undescend(){
        if (depth == 0){
            depth = 0;
        }
        else {
            depth += 1;
        }
    }
    private  void goForward(){
        if (direction.equals("north")) {
            coords.set(1, coords.get(1) + 1);
        } else if (direction.equals("east")) {
            coords.set(0, coords.get(0) + 1);
        } else if (direction.equals("south")) {
            coords.set(1, coords.get(1) - 1);
        } else if (direction.equals("west")) {
            coords.set(0, coords.get(0) - 1);
        }
    }

    private void releaseCapsule() {
        if(depth < -1) {
            throw new Error("Cannot release capsule, submarine exploded");
        }
        else{
            capsuleReleased = true;
        }
    }

    public Submarine sendCommand(String command){
        for(char caracter : command.toCharArray()){
            if (caracter == 'f'){
                goForward();
            }
            else if (caracter == 'd'){
                descend();
            }
            else if (caracter == 'u'){
                undescend();
            }
            else if (caracter == 'l'){
                moveLeft();
            }
            else if (caracter == 'r'){
                moveRight();
            }
            else if (caracter == 'm'){
                releaseCapsule();
            }
        }
        return this;
    }

    public Integer getCoordX() {
        return coords.get(0);
    }
    public Integer getCoordY() {
        return coords.get(1);
    }
    public  Integer getDepth() {
        return depth;
    }
    public  String getDirection() {
        return direction;
    }
}