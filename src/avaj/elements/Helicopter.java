package avaj.elements;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(5, p_name, p_coordinates);
		// System.out.println(p_name);
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
		p_weatherTower.register(this);
	}
	
}
