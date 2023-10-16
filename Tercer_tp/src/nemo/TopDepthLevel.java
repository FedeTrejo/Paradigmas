package nemo;

public class TopDepthLevel extends SubmarineDepth {
    public void shoot() {}

    public int submarineDepths() {
        return 0;
    }

    public SubmarineDepth ascend() {
        return this;
    }

    public SubmarineDepth descend() {
        return new SafeDepthLevel();
    }
}
