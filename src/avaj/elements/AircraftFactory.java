package avaj.elements;

public class AircraftFactory {

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		if (p_type.equals("Helicopter")) {
			System.out.println("Built an helico");
			return new Helicopter(5, p_name, p_coordinates);
		}
		else {
			System.out.println("Built smth else");
			return new Helicopter(5, p_name, p_coordinates);
		}
	}
	
}
