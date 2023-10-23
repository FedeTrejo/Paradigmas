package nemo;

import java.util.ArrayList;

public abstract class Depth {
    public abstract void shoot();

    public abstract int submarineDepths();

    public ArrayList<Depth> ascend(Submarine submarine) {
        ArrayList<Depth> newDepthList =  submarine.getSubmarineDepthList();
        newDepthList.remove(0);
        return newDepthList;
    }

    public void descend(Submarine submarine) {
        ArrayList<Depth> newDepthList =  submarine.getSubmarineDepthList();
        newDepthList.add(0, new UnsafeDepthLevel());
    }

    public boolean equals(Object other) {
        return other instanceof Depth && areObjectsEqual((Depth) other);
    }

    public boolean areObjectsEqual(Depth other) {
        return this.submarineDepths() == other.submarineDepths();
    }

}
