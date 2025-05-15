package avaj.elements;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
		p_weatherTower.register(this);
	}
	
}
