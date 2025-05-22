package avaj.elements;

public class WeatherTower extends Tower {
	public String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.get_instance().getCurrentWeather(p_coordinates);
	}

	public void changeWeather() {
		for (int i = 0; i < this.observers.size(); i++) {
			this.observers.get(i).updateConditions();
		}

	}

}