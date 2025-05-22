package avaj.elements;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	String messageDetails = "Helicopter#" + this.name + "(" + this.id + "): ";
	private static Map<String, String> weatherMessages;
	static {
		weatherMessages = new HashMap<>();
		weatherMessages.put("SUN", "its hot in here");
		weatherMessages.put("RAIN", "its rainy in here");
		weatherMessages.put("FOG", "its foggy in here");
		weatherMessages.put("SNOW", "its snowy in here");
	}

	Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
		p_weatherTower.register(this);
	}

	public String getType() {
		return("Helicopter");
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
		System.out.println("long: " + this.coordinates.longitude + ", lat: " + this.coordinates.latitude + ", height: " + + this.coordinates.height);
		if (this.coordinates.height > 100) {
			this.coordinates.height = 100;
		}
		if (this.coordinates.height <= 0) {
			this.land();
		}
	}

	public void handleSun() {
		this.coordinates.height += 2;
		this.coordinates.latitude += 10;
	}

	public void handleRain() {
		this.coordinates.longitude += 5;

	}

	public void handleFog() {
		this.coordinates.longitude += 1;
	}

	public void handleSnow() {
		this.coordinates.height -= 12;
	}

	public void land() {
		System.out.println(this.messageDetails + "landing");
		this.weatherTower.unregister(this);
	}
}
