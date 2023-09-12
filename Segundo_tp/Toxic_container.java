package queue;


public class Toxic_container extends Container{

    public Object getCargo() { throw new Error("Queue is empty");}
}
