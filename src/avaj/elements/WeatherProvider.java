package avaj.elements;

public class WeatherProvider {

    private static WeatherProvider _instance = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "SNOW", "FOG"};

    WeatherProvider() {
        ;
    }

    public static WeatherProvider get_instance() {
        return _instance;
    }
    
    String getCurrentWeather(Coordinates coordinates) {
        return (WeatherProvider.weather[((coordinates.height + coordinates.latitude + coordinates.longitude) % 4)]);
    }
    
}
