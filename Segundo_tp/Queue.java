package queue;

import java.util.ArrayList;

public class Queue {

	public ArrayList<Container> queue;

	public Queue() {
		queue = new ArrayList<>();
		queue.add(new VoidContainer());
	}

	public void add(Object cargo) {
		queue.add(size(), new LoadedContainer(cargo));
	}

	public Object take() {
		return queue.remove(0).item();
	}

	public Object head() {
		return queue.get(0).item();
	}

	public boolean isEmpty() {
		return queue.size() == 1;
	}

	public int size() {
		return queue.size() - 1;
	}

}