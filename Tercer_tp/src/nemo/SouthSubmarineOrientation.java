package nemo;

public class SouthSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation rotateLeft() {
        return new EastSubmarineOrientation();
    }

    public SubmarineOrientation rotateRight() {
        return new WestSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(0, -1);
    }

}
