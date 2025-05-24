package fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements;

import java.io.IOException;

public class WeatherTower extends Tower {

	public String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.get_instance().getCurrentWeather(p_coordinates);
	}

	public void changeWeather() throws IOException {
		this.conditionChanged();
	}
}