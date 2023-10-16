package nemo;

public class SafeDepthLevel extends SubmarineDepth {
    public void shoot() {}

    public int submarineDepths() {
        return 1;
    }

    public SubmarineDepth ascend() {
        return new TopDepthLevel();
    }

    public SubmarineDepth descend() {
        return new UnsafeDepthLevel();
    }
}
