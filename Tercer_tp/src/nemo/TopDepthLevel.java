package nemo;

import java.util.ArrayList;

public class TopDepthLevel extends SubmarineDepth {
    public void shoot() {}

    public int submarineDepths() {
        return 0;
    }

    public ArrayList<SubmarineDepth> ascend(Submarine submarine) {
        return submarine.getSubmarineDepthList();
    }

    public void descend(Submarine submarine) {
        ArrayList<SubmarineDepth> newSubmarineDepthList = submarine.getSubmarineDepthList();
        newSubmarineDepthList.add(0, new SafeDepthLevel());
    }
}
