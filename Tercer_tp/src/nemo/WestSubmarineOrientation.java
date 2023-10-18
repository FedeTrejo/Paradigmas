package nemo;

public class WestSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation rotateLeft() {
        return new SouthSubmarineOrientation();
    }

    public SubmarineOrientation rotateRight() {
        return new NorthSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(-1, 0);
    }

}
