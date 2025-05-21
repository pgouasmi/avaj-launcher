package avaj.elements;

public interface Flyable {

	WeatherTower weatherTower = null;

	public void updateConditions();
	public void registerTower(WeatherTower p_Tower);
	public String getType();
	public String getName();
	public long getID();
}
