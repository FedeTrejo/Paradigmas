package nemo;

import java.util.ArrayList;

public class SurfaceLevel extends Depth {
    public void shoot() {}

    public int submarineDepths() {
        return 0;
    }

    public ArrayList<Depth> ascend(Submarine submarine) {
        return submarine.getSubmarineDepthList();
    }

    public void descend(Submarine submarine) {
        ArrayList<Depth> newDepthList = submarine.getSubmarineDepthList();
        newDepthList.add(0, new SafeDepthLevel());
    }
}
