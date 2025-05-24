package avaj.elements;

import avaj.simulator.avajLogger;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private static final Map<String, String> weatherMessages;
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

    @Override
	public void registerTower(WeatherTower p_weatherTower) throws IOException {
		this.weatherTower = p_weatherTower;
		p_weatherTower.register(this);
	}

	@Override
	public String getType() {
		return("Helicopter");
	}

	@Override
	public void updateConditions() throws IOException {
		String weather = this.weatherTower.getWeather(this.coordinates);
            switch (weather) {
                case "SUN" -> this.handleSun();
                case "RAIN" -> this.handleRain();
                case "FOG" -> this.handleFog();
                case "SNOW" -> this.handleSnow();
            }

		avajLogger.getInstance().writeToFile(getMessageDetails() + weatherMessages.get(weather));
	
		if (this.coordinates.getHeight() <= 0) {
			this.land();
		}
	}

	@Override
	public void handleSun() {
		this.coordinates.setHeight(2);
		this.coordinates.setLatitude(10);
	}

	@Override
	public void handleRain() {
		this.coordinates.setLongitude(5);

	}

	@Override
	public void handleFog() {
		this.coordinates.setLongitude(1);
	}

	@Override
	public void handleSnow() {
		this.coordinates.setHeight(-12);
	}

	public void land() throws IOException {
		try {
			avajLogger.getInstance().writeToFile(getMessageDetails() + "landing");
			this.weatherTower.unregister(this);	
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
    public String getMessageDetails() {
        return "Helicopter#" + this.name + "(" + this.id + "): ";
    }
}
