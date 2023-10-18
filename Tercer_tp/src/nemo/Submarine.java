package nemo;

import java.util.ArrayList;

public class Submarine {
    private SubmarinePosition submarinePosition;
    private Orientation orientation;
    private ArrayList<Depth> depth = new ArrayList<>();

    public Submarine(SubmarinePosition submarinePosition, Orientation orientation) {
        this.submarinePosition = submarinePosition;
        this.orientation = orientation;
        this.depth.add(new SurfaceLevel());
    }

    public SubmarinePosition getSubmarinePosition() {
        return submarinePosition;
    }

    public Orientation getSubmarineOrientation() {
        return orientation;
    }

    public Depth getSubmarineDepth() {
        return depth.get(0);
    }

    public ArrayList<Depth> getSubmarineDepthList() {
        return depth;
    }

    public void runCommands(String instructions) {
        instructions.chars().forEach(instruction -> Command.commandFor(String.valueOf((char) instruction)).runCommand(this));
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

    private void setSubmarineOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    private void setSubmarineDepth(ArrayList<Depth> depths) {
        this.depth = depths;
    }

}
