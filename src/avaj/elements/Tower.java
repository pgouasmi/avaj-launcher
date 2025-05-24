package avaj.elements;

import avaj.simulator.avajLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tower {
	List<Flyable> observers = new ArrayList<>();;

	public void register(Flyable p_flyable) throws IOException {
		try {
			this.observers.add(p_flyable);
			avajLogger.getInstance().writeToFile("Tower says: " + p_flyable.getType()+"#"+p_flyable.getName()+'('+p_flyable.getID()+ ") registered to weather tower");
		} catch (Exception e) {
			throw e;
		}
	}

	public void unregister(Flyable p_flyable) throws IOException{
		try {
			if (this.observers.contains(p_flyable))
				this.observers.remove(p_flyable);
			avajLogger.getInstance().writeToFile("Tower says: " + p_flyable.getType()+"#"+p_flyable.getName()+'('+p_flyable.getID()+ ") unregistered from weather tower");
		} catch(Exception e) {
			throw e;
		}
	}

	protected void conditionChanged() throws IOException {
		for (int i = 0; i < this.observers.size(); i++) {
			this.observers.get(i).updateConditions();
		}
	}
}
