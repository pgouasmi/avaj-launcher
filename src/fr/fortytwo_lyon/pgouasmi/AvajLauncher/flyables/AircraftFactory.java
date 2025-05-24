package fr.fortytwo_lyon.pgouasmi.AvajLauncher.flyables;

import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.Coordinates;

public class AircraftFactory {

	private static long count = 0;

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		AircraftFactory.count++;
            return switch (p_type) {
                case "Helicopter" -> new Helicopter(AircraftFactory.count, p_name, p_coordinates);
                case "Baloon" -> new Baloon(AircraftFactory.count, p_name, p_coordinates);
                default -> new JetPlane(AircraftFactory.count, p_name, p_coordinates);
            };
	}
}
