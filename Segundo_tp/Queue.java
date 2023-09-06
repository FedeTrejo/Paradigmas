package queue;

import java.util.LinkedList;

public class Queue {

	private final LinkedList<Object> list = new LinkedList<>();

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Queue add(Object cargo) {
		list.addLast(cargo);
		return this;
	}

	public Object take() {
		if(list.isEmpty()) {
			throw new Error("Queue is empty");
		}
		return list.removeFirst();
	}

	public Object head() {
		if(list.isEmpty()) {
			throw new Error("Queue is empty");
		}
		return list.getFirst();
	}

	public int size() {
		return list.size();
	}

}