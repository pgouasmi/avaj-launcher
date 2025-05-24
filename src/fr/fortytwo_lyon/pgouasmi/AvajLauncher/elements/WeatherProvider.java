package fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements;

public class WeatherProvider {

    private static final WeatherProvider _instance = new WeatherProvider();
    private static final String[] weather = {"SUN", "RAIN", "SNOW", "FOG"};

    private WeatherProvider() {}

    public static WeatherProvider get_instance() {
        return _instance;
    }
    
    public String getCurrentWeather(Coordinates coordinates) {
        return (WeatherProvider.weather[(int)Math.abs((coordinates.getLongitude() * 13 + coordinates.getHeight() * 31 + coordinates.getLatitude() * 17) % 4)]);
    } 
}
