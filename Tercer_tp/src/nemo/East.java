package nemo;
public class East extends Orientation {
    public Orientation rotateLeft() {
        return new North();
    }

    public Orientation rotateRight() {
        return new South();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(1, 0);
    }

}
