package nemo;

public class WestSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation turnLeft() {
        return new SouthSubmarineOrientation();
    }

    public SubmarineOrientation turnRight() {
        return new NorthSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom(SubmarinePosition submarinePosition) {
        return new SubmarinePosition(submarinePosition.x() - 1, submarinePosition.y());
    }

    public boolean equals(Object other) {
        return other instanceof WestSubmarineOrientation;
    }

}
