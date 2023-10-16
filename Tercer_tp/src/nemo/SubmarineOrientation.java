package nemo;

public abstract class   SubmarineOrientation {
    public abstract SubmarineOrientation turnLeft();

    public abstract SubmarineOrientation turnRight();

    public abstract SubmarinePosition moveForwardFrom(SubmarinePosition submarinePosition);

}
