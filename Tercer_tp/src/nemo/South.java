package nemo;

public class South extends Orientation {
    public Orientation rotateLeft() {
        return new East();
    }

    public Orientation rotateRight() {
        return new West();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(0, -1);
    }

}
