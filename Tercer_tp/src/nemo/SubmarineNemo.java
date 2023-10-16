package nemo;

import java.util.ArrayList;
import java.util.Objects;

public class SubmarineNemo {
    private SubmarinePosition submarinePosition;
    private SubmarineOrientation submarineOrientation;

    public ArrayList<SubmarineDepth> submarineDepth;


    private final ArrayList<SubmarineCommand> submarineCommands;

    public SubmarineNemo(SubmarinePosition submarinePosition, SubmarineOrientation submarineOrientation, ArrayList<SubmarineCommand> submarineCommands) {
        this.submarinePosition = submarinePosition;
        this.submarineOrientation = submarineOrientation;
        this.submarineCommands = submarineCommands;
        this.submarineDepth = new ArrayList<>();
        this.submarineDepth.add(0, new TopDepthLevel());

    }

    public SubmarinePosition getPosition() {
        return submarinePosition;
    }

    public void setPosition(SubmarinePosition submarinePosition) {
        this.submarinePosition = submarinePosition;
    }

    public SubmarineOrientation getOrientation() {
        return submarineOrientation;
    }

    public void setOrientation(SubmarineOrientation submarineOrientation) {
        this.submarineOrientation = submarineOrientation;
    }

    public SubmarineDepth getDepth() {
        return submarineDepth.get(0);
    }

    public void setDepth(SubmarineDepth submarineDepth) {
        this.submarineDepth.add(0, submarineDepth);
    }

    public SubmarineDepth goUp() {
        SubmarineDepth upperDepth = this.submarineDepth.remove(0).ascend();
        this.submarineDepth.add(0, upperDepth);
        return upperDepth;
    }

    public SubmarineDepth goDown() {
        SubmarineDepth lowerSubmarineDepth = this.submarineDepth.get(0).descend();
        this.submarineDepth.add(0, lowerSubmarineDepth);
        return lowerSubmarineDepth;
    }

    public void runCommands(String instructions) {
        instructions.chars().forEach((instruction -> submarineCommands.stream().
                filter(submarineCommand -> Objects.equals(submarineCommand.name(),
                                            String.valueOf( (char) instruction)))
                .forEach(submarineCommand -> submarineCommand.runCommand(this))));
    }

}
