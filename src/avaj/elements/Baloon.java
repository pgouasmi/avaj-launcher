package avaj.elements;

import java.util.HashMap;
import java.util.Map;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	String messageDetails = "Baloon#" + this.name + "(" + this.id + "): ";
	private static Map<String, String> weatherMessages;
	static {
		weatherMessages = new HashMap<>();
		weatherMessages.put("SUN", "its hot in here");
		weatherMessages.put("RAIN", "its rainy in here");
		weatherMessages.put("FOG", "its foggy in here");
		weatherMessages.put("SNOW", "its snowy in here");
	}

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

	public void updateConditions() {
		String weather = this.weatherTower.getWeather(this.coordinates);
		if (weather.equals("SUN")) {
			this.handleSun();
		}
		else if (weather.equals("RAIN")) {
			this.handleRain();
		}
		else if (weather.equals("FOG")) {
			this.handleFog();
		}
		else {
			this.handleSnow();
		}

		System.out.println(this.messageDetails + weatherMessages.get(weather));
		if (this.coordinates.height > 100) {
			this.coordinates.height = 100;
		}
		if (this.coordinates.height <= 0) {
			this.land();
		}
	}

	public void handleSun() {
		this.coordinates.longitude += 2;
		this.coordinates.height += 4;
	}

	public void handleRain() {
		this.coordinates.height -= 5;
	}

	public void handleFog() {
		this.coordinates.height -= 3;
	}

	public void handleSnow() {
		this.coordinates.height -= 15;
	}

	public void land() {
		System.out.println(this.messageDetails + "landing");
		this.weatherTower.unregister(this);
	}
}