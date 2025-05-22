package avaj.elements;

public interface Flyable {

	WeatherTower weatherTower = null;

	public void updateConditions();
	public void registerTower(WeatherTower p_Tower);
	public String getType();
	public String getName();
	public void handleSun();
	public void handleFog();
	public void handleRain();
	public void handleSnow();
	public void land();
	public long getID();
}
