package nemo;

public class North extends Orientation {
    public Orientation rotateLeft() {
        return new West();
    }

    public Orientation rotateRight() {
        return new East();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(0, 1);
    }

}
