package nemo;
public class EastSubmarineOrientation extends SubmarineOrientation {
    public SubmarineOrientation turnLeft() {
        return new NorthSubmarineOrientation();
    }

    public SubmarineOrientation turnRight() {
        return new SouthSubmarineOrientation();
    }

    public SubmarinePosition moveForwardFrom(SubmarinePosition submarinePosition) {
        return new SubmarinePosition(submarinePosition.x() + 1, submarinePosition.y());
    }

    public boolean equals(Object other) {
        return other instanceof EastSubmarineOrientation;
    }

}
