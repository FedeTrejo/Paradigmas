package nemo;

public class SouthSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation turnLeft() {
        return new EastSubmarineOrientation();
    }

    public SubmarineOrientation turnRight() {
        return new WestSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom(SubmarinePosition submarinePosition) {
        return new SubmarinePosition(submarinePosition.x(), submarinePosition.y() - 1);
    }

    public boolean equals(Object other) {
        return other instanceof SouthSubmarineOrientation;
    }

}
