package fr.fortytwo_lyon.pgouasmi.AvajLauncher.simulator;

import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.Coordinates;
import fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements.WeatherTower;
import fr.fortytwo_lyon.pgouasmi.AvajLauncher.flyables.AircraftFactory;
import fr.fortytwo_lyon.pgouasmi.AvajLauncher.flyables.Flyable;
import java.io.IOException;

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
			avajLogger.createInstance();
			Parser.openFile(args[0]);
			Parser.getRepetitions();
			Simulator.tower = new WeatherTower();
			Simulator.instanciateFlyables();
			while (Simulator.repetitions-- != 0) {
				Simulator.tower.changeWeather();
			}
			avajLogger.getInstance().close();

		} catch(NumberFormatException e) {
            System.err.println("Wrong repetitions on first line!");
			System.exit(1);
			avajLogger.getInstance().close();
        } catch(Exception e) {
			System.err.println("Error caught: " + e.getMessage());
			System.exit(1);
			avajLogger.getInstance().close();
		}
	}

	public static void instanciateFlyables() throws 
				Parser.NoAircraftInFileException, 
				Parser.MalformedAircraftDataException, 
				Parser.WrongAircraftTypeException, 
				Parser.BrokenCoordinatesException,
				IOException {
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
