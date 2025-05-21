package avaj.elements;

public class AircraftFactory {

	private static long count = 0;

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		AircraftFactory.count++;
		if (p_type.equals("Helicopter")) {
			return new Helicopter(AircraftFactory.count, p_name, p_coordinates);
		}
		else if (p_type.equals("Baloon")) {
			return new Baloon(AircraftFactory.count, p_name, p_coordinates);
		}
		else {
			return new JetPlane(AircraftFactory.count, p_name, p_coordinates);
		}
	}
}
