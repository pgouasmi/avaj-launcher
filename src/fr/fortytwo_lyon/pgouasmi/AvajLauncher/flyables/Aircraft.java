package fr.fortytwo_lyon.pgouasmi.AvajLauncher.flyables;

import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.Coordinates;

public class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	protected String messageDetails;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinate;
    }

	public String getName() {
		return this.name;
	}

	public long getID() {
		return this.id;
	}


}
