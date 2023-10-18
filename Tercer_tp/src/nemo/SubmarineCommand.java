package nemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class    SubmarineCommand {
    private final String name;
    private final Consumer<Submarine> action;

    public static ArrayList<SubmarineCommand> submarineCommands = new ArrayList<>(Arrays.asList(
            new SubmarineCommand("f", Submarine::forward),
            new SubmarineCommand("l", Submarine::left),
            new SubmarineCommand("r", Submarine::right),
            new SubmarineCommand("u", Submarine::up),
            new SubmarineCommand("d", Submarine::down),
            new SubmarineCommand("m", Submarine::shoot)));

    public static SubmarineCommand commandFor(String commandLetter) {
            return submarineCommands.stream().filter(submarineCommand -> submarineCommand.applies(commandLetter)).findFirst().get();
    }

    public SubmarineCommand(String name, Consumer<Submarine> action) {
        this.name = name;
        this.action = action;
    }

    public boolean applies( String commandName ) {
        return name.equals(commandName);
    }

    public void runCommand(Submarine submarine) {
        action.accept(submarine);
    }
}
