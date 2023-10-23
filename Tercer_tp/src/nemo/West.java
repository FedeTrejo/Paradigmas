package nemo;

public class West extends Orientation {
    public Orientation rotateLeft() {
        return new South();
    }

    public Orientation rotateRight() {
        return new North();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(-1, 0);
    }

}
