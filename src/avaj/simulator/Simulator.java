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
		} catch(NumberFormatException e) {
            System.out.println("Wrong repetitions on first line!");
			System.exit(1);

        }  
		
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public static void instanciateFlyables() throws 
				Parser.NoAircraftInFileException, 
				Parser.MalformedAircraftDataException, 
				Parser.WrongAircraftTypeException, 
				Parser.BrokenCoordinatesException {
		if (!Parser.scanner.hasNext()) {
			throw new Parser.NoAircraftInFileException();
		}
		try {
			while (Parser.scanner.hasNext()) {
				String currentLine = Parser.scanner.nextLine();
				if (currentLine.isEmpty()) {
					continue;
				}
				String[] splittedLine = currentLine.split(" +");

				// System.out.println("Before verify format");

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
		} catch(Exception e) {
			throw e;
		}
	}

	public static Coordinates createCoordinates(String longitude, String latitude, String height) throws Parser.BrokenCoordinatesException {
		int longitudeInt;
		int latitudeInt;
		int heightInt;
		
		try {
			longitudeInt = Integer.parseInt(longitude);
			if (longitudeInt < -180 || longitudeInt > 180) {
				throw new Parser.BrokenCoordinatesException(longitude);
			}
		} catch(Exception e) {
			throw e;
		}

		try {
			latitudeInt = Integer.parseInt(latitude);
			if (latitudeInt < -90 || latitudeInt > 90) {
				throw new Parser.BrokenCoordinatesException(latitude);
			}
		} catch(Exception e) {
			throw e;
		}

		try {
			heightInt = Integer.parseInt(height);
			if (heightInt < 1) {
				throw new Parser.BrokenCoordinatesException(height);
			}
			if (heightInt > 100) {
				heightInt = 100;
			}
		} catch(Exception e) {
			throw e;
		}
		return new Coordinates(longitudeInt, latitudeInt, heightInt);
	}
}
