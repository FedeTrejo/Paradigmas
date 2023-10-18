package nemo;

import java.util.ArrayList;

public class Submarine {
    private SubmarinePosition submarinePosition;
    private SubmarineOrientation submarineOrientation;
    private ArrayList<SubmarineDepth> submarineDepth = new ArrayList<>();

    public Submarine(SubmarinePosition submarinePosition, SubmarineOrientation submarineOrientation) {
        this.submarinePosition = submarinePosition;
        this.submarineOrientation = submarineOrientation;
        this.submarineDepth.add(new TopDepthLevel());
    }

    public SubmarinePosition getSubmarinePosition() {
        return submarinePosition;
    }

    public SubmarineOrientation getSubmarineOrientation() {
        return submarineOrientation;
    }

    public SubmarineDepth getSubmarineDepth() {
        return submarineDepth.get(0);
    }

    public ArrayList<SubmarineDepth> getSubmarineDepthList() {
        return submarineDepth;
    }

    public void runCommands(String instructions) {
        instructions.chars().forEach(instruction -> SubmarineCommand.commandFor(String.valueOf((char) instruction)).runCommand(this));
    }

    public void forward() {
        setSubmarinePosition(getSubmarinePosition().sum(getSubmarineOrientation().moveForwardFrom()));
    }

    public void left() {
        setSubmarineOrientation(getSubmarineOrientation().rotateLeft());
    }

    public void right() {
        setSubmarineOrientation(getSubmarineOrientation().rotateRight());
    }

    public void shoot() {
        getSubmarineDepth().shoot();
    }

    public void up() {
        setSubmarineDepth(getSubmarineDepth().ascend(this));
    }

    public void down() {
        getSubmarineDepth().descend(this);
    }

    private void setSubmarinePosition(SubmarinePosition submarinePosition) {
        this.submarinePosition = submarinePosition;
    }

    private void setSubmarineOrientation(SubmarineOrientation submarineOrientation) {
        this.submarineOrientation = submarineOrientation;
    }

    private void setSubmarineDepth(ArrayList<SubmarineDepth> submarineDepths) {
        this.submarineDepth = submarineDepths;
    }

}
