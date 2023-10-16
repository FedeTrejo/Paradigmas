package nemo;

public class UnsafeDepthLevel extends SubmarineDepth {

    public static String LAUNCH_ERROR_MESSAGE = "Not possible";


    public void shoot() {
        throw new RuntimeException(LAUNCH_ERROR_MESSAGE);
    }

    public int submarineDepths() {
        return 2;
    }

    public SubmarineDepth ascend() {
        return null;
    }

    public SubmarineDepth descend() {
        return new UnsafeDepthLevel();
    }
}
