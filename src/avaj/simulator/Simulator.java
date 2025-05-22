package avaj.simulator;

import avaj.elements.AircraftFactory;
import avaj.elements.Coordinates;
import avaj.elements.WeatherTower;
import avaj.elements.Flyable;

public class Simulator {

	static long repetitions;
	static WeatherTower tower;
	static final AircraftFactory factory = new AircraftFactory();

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Wrong number of arguments");
			return ;
		}

		try {
			Parser.openFile(args[0]);
			Parser.getRepetitions();
			Simulator.tower = new WeatherTower();
			Simulator.instanciateFlyables();
			while (Simulator.repetitions-- != 0) {
				Simulator.tower.changeWeather();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return ;
		}
	}

	public static void instanciateFlyables() {
		if (!Parser.scanner.hasNext()) {
			throw new ExceptionInInitializerError("No flyables to instanciate");
		}
		while (Parser.scanner.hasNext()) {
			String currentLine = Parser.scanner.nextLine();
			if (currentLine.isEmpty()) {
				continue;
			}
			String[] splittedLine = currentLine.split(" ");
			Parser.verifyFormat(splittedLine);

			String aircraftType = splittedLine[0];
			String aircraftName = splittedLine[1];
			String longitude = splittedLine[2];
			String latitude = splittedLine[3];
			String height = splittedLine[4];

			// System.out.println("Type: " + aircraftType + ". Name: " + aircraftName);

			Parser.verifyAircraftType(aircraftType);
			Coordinates coordinates = Simulator.createCoordinates(longitude, latitude, height);
			Flyable aircraft = Simulator.factory.newAircraft(aircraftType, aircraftName, coordinates);
			aircraft.registerTower(Simulator.tower);
		}
	}

	public static Coordinates createCoordinates(String longitude, String latitude, String height) {
		int longitudeInt = Integer.parseInt(longitude);
		int latitudeInt = Integer.parseInt(latitude);
		int heightInt = Integer.parseInt(height);

		if (longitudeInt < 1 || latitudeInt < 1 || heightInt < 1) {
			throw new ExceptionInInitializerError("Broken coordinates");
		}

		if (heightInt > 100) {
			heightInt = 100;
		}

		return new Coordinates(longitudeInt, latitudeInt, heightInt);
	}
}
