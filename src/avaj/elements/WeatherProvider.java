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
        return (WeatherProvider.weather[(int)Math.abs((coordinates.height * 31 + coordinates.latitude * 17 + coordinates.longitude * 13) % 4)]);
    }
    
}
