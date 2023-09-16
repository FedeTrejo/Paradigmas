package queue;

public class VoidContainer extends Container {
    public static String EMPTY_QUEUE = "Queue is empty";

    public Object item() {
        throw new Error(EMPTY_QUEUE);
    }
    

}
