package queue;

import java.awt.*;
import java.util.ArrayList;

public class Queue {

	private final ArrayList<Container> list = new ArrayList<>();
	public Queue(){
		list.add(new Toxic_container());
	}

	public boolean isEmpty() {
		return list.size()==1;
	}

	public Queue add(Object cargo) {
		list.add(1,new Normal_container(cargo));
		return this;
	}

	public Object take() {
		return list.remove(getLast()).getCargo();
	}

	public Object head() {
		return list.get(getLast()).getCargo();
	}

	public int size() {
		return list.size() - 1;
	}

	private int getLast(){
		return size();
	}

}