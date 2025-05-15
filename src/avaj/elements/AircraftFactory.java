package avaj.elements;

public class AircraftFactory {

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		if (p_type == "Helicopter")
			return new Helicopter(p_type, p_name, p_coordinates);
	}
	
}
