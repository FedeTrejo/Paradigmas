package nemo;

import java.util.function.Consumer;

public class SubmarineCommand {
    private final String name;

    private final Consumer<SubmarineNemo> action;

    public SubmarineCommand(String name, Consumer<SubmarineNemo> action) {
        this.name = name;
        this.action = action;
    }

    public String name() {
        return name;
    }

    public void runCommand(SubmarineNemo submarineNemo) {
        action.accept(submarineNemo);
    }
}
