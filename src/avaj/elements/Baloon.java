package avaj.elements;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Baloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		// System.out.println(p_name);
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
		p_weatherTower.register(this);
	}

	public String getType() {
		return("Baloon");
	}
}