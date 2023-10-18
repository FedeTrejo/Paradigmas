package nemo;
public class EastSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation rotateLeft() {
        return new NorthSubmarineOrientation();
    }

    public SubmarineOrientation rotateRight() {
        return new SouthSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom() {
        return new SubmarinePosition(1, 0);
    }

}
