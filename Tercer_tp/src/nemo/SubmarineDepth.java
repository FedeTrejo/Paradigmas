package nemo;

public abstract class SubmarineDepth {
    public abstract void shoot();

    public abstract int submarineDepths();

    public abstract SubmarineDepth ascend();

    public abstract SubmarineDepth descend();

    @Override
    public boolean equals(Object other) {
        return other instanceof SubmarineDepth && hasSameDepthAs((SubmarineDepth) other);
    }

    public boolean hasSameDepthAs(SubmarineDepth other) {
        return this.submarineDepths() == other.submarineDepths();
    }
}
