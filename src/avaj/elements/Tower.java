package avaj.elements;

import java.util.List;
import java.util.ArrayList;

public class Tower {
	List<Flyable> observers;

	public void register(Flyable p_flyable) {
		if (this.observers == null) {
			this.observers = new ArrayList<Flyable>();
		}
		this.observers.add(p_flyable);
		System.out.println("Tower says: " + p_flyable.getType()+"#"+p_flyable.getName()+'('+p_flyable.getID()+ ") registered to weather tower");
	}

	public void unregister(Flyable p_flyable) {
		if (this.observers.contains(p_flyable))
			this.observers.remove(p_flyable);
			System.out.println("Tower says: " + p_flyable.getType()+"#"+p_flyable.getName()+'('+p_flyable.getID()+ ") unregistered from weather tower");

	}

	protected void conditionChanged() {

	}


	
}
