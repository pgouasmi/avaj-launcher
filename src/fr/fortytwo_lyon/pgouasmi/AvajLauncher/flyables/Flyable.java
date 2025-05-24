package fr.fortytwo_lyon.pgouasmi.AvajLauncher.flyables;

import java.io.IOException;

import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.WeatherTower;

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
