package avaj.elements;

import java.io.IOException;

public interface Flyable {

	public void updateConditions() throws IOException;
	public void registerTower(WeatherTower p_Tower) throws IOException;
	public String getType();
	public String getName();
	public void handleSun();
	public void handleFog();
	public void handleRain();
	public void handleSnow();
	public void land() throws IOException;
	public long getID();
	public String getMessageDetails();
}
