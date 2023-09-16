package queue;

public class LoadedContainer extends Container{
    private final Object cargo;

    public LoadedContainer(Object cargo) { this.cargo = cargo; }

    public Object item() { return this.cargo; }
}
