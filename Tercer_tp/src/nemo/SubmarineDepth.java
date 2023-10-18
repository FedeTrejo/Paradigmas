package nemo;

import java.util.ArrayList;

public abstract class SubmarineDepth {
    public abstract void shoot();

    public abstract int submarineDepths();

    public ArrayList<SubmarineDepth> ascend(Submarine submarine) {
        ArrayList<SubmarineDepth> newSubmarineDepthList =  submarine.getSubmarineDepthList();
        newSubmarineDepthList.remove(0);
        return newSubmarineDepthList;
    }

    public void descend(Submarine submarine) {
        ArrayList<SubmarineDepth> newSubmarineDepthList =  submarine.getSubmarineDepthList();
        newSubmarineDepthList.add(0, new UnsafeDepthLevel());
    }

    public boolean equals(Object other) {
        return other instanceof SubmarineDepth && areObjectsEqual((SubmarineDepth) other);
    }

    public boolean areObjectsEqual(SubmarineDepth other) {
        return this.submarineDepths() == other.submarineDepths();
    }

}
