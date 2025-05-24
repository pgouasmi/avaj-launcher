package fr.fortytwo_lyon.pgouasmi.AvajLauncher.flyables;

import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.Coordinates;
import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.WeatherTower;
import fr.fortytwo_lyon.pgouasmi.AvajLauncher.simulator.avajLogger;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private static final Map<String, String> weatherMessages;
	static {
		weatherMessages = new HashMap<>();
		weatherMessages.put("SUN", "its hot in here");
		weatherMessages.put("RAIN", "its rainy in here");
		weatherMessages.put("FOG", "its foggy in here");
		weatherMessages.put("SNOW", "its snowy in here");
	}

	JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		// System.out.println(p_name);
	}

    @Override
	public void registerTower(WeatherTower p_weatherTower) throws IOException {
		this.weatherTower = p_weatherTower;
		p_weatherTower.register(this);
	}

	
    @Override
	public String getType() {
		return("JetPlane");
	}

	@Override
	public void updateConditions() throws IOException {
		String weather;
            weather = this.weatherTower.getWeather(this.coordinates);
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
		this.coordinates.setLatitude(10);
		this.coordinates.setHeight(2);
	}

	@Override
	public void handleRain() {
		this.coordinates.setLatitude(5);
	}

	@Override
	public void handleFog() {
		this.coordinates.setLatitude(1);
	}

	@Override
	public void handleSnow() {
		this.coordinates.setHeight(-7);
	}

	@Override
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
        return "JetPlane#" + this.name + "(" + this.id + "): ";
    }
}