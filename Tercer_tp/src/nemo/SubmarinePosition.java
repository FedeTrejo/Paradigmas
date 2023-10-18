package nemo;

public class SubmarinePosition {
    private final int x;
    private final int y;

    public SubmarinePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SubmarinePosition sum(SubmarinePosition other) {
        return new SubmarinePosition(this.getX() + other.getX(), this.getY() + other.getY());
    }

    public boolean equals(Object other) {
        if (other instanceof SubmarinePosition aSubmarinePosition) {
            return this.getX() == aSubmarinePosition.getX() && this.getY() == aSubmarinePosition.getY();
        }
        return false;
    }

    private int getX() {
        return x;
    }
    private int getY() {
        return y;
    }

}
