package nemo;

public class NorthSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation turnLeft() {
        return new WestSubmarineOrientation();
    }

    public SubmarineOrientation turnRight() {
        return new EastSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom(SubmarinePosition submarinePosition) {
        return new SubmarinePosition(submarinePosition.x(), submarinePosition.y() + 1);
    }

    public boolean equals(Object other) {
        return other instanceof NorthSubmarineOrientation;
    }
}
