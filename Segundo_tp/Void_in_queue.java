package queue;


public class Void_in_queue extends Container{

    public Object getCargo() { throw new Error("Queue is empty");}
}
