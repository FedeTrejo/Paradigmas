package nemo;

public class NorthSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation rotateLeft() {
        return new WestSubmarineOrientation();
    }

    public SubmarineOrientation rotateRight() {
        return new EastSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(0, 1);
    }

}
