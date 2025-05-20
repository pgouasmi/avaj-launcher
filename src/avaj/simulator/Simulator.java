package avaj.simulator;

import avaj.elements.AircraftFactory;
import avaj.elements.Coordinates;
import avaj.elements.WeatherTower;

import java.io.File; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import avaj.elements.Flyable;

public class Simulator {

	static String fileName;
	static File file;
	static Scanner scanner;
	static long repetitions;
	static WeatherTower tower;
	static ArrayList<String> aircraftTypes = new ArrayList<>(Arrays.asList("Helicopter", "Baloon", "JetPlane"));
	static AircraftFactory factory = new AircraftFactory();

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Wrong number of arguments");
			return ;
		}
		Simulator.fileName = args[0];
		// System.out.println(file_name);

		try {
			Simulator.file = new File(Simulator.fileName);
			Simulator.scanner = new Scanner(Simulator.file);
		} catch(Exception e) {
			e.printStackTrace();
		}

		try {
			Simulator.getRepetitions();
		} catch(Exception e) {
			e.printStackTrace();
		}

		Simulator.tower = new WeatherTower();

		try {
			Simulator.instanciateFlyables();
		} catch(Exception e) {

		}
	}

	public static void getRepetitions() {
		try {
			String reps = Simulator.scanner.nextLine();
			long casted = Long.parseLong(reps);
			Simulator.repetitions = casted;
		} catch(Exception e) {
			e.printStackTrace();
			throw(e);
		}
	}

	public static void instanciateFlyables() {
		if (!Simulator.scanner.hasNext()) {
			throw new ExceptionInInitializerError("No flyables to instanciate");
		}
		while (Simulator.scanner.hasNext()) {
			String currentLine = Simulator.scanner.nextLine();
			String[] splittedLine = currentLine.split(" ");
			Simulator.verifyFormat(splittedLine);

			String aircraftType = splittedLine[0];
			String aircraftName = splittedLine[1];
			String longitude = splittedLine[2];
			String latitude = splittedLine[3];
			String height = splittedLine[4];

			System.out.println("Type: " + aircraftType + ". Name: " + aircraftName);

			Simulator.verifyAircraftType(aircraftType);
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

	public static void verifyFormat(String[] splitted) {
		if (splitted.length != 5) {
			throw new ExceptionInInitializerError();
		}
	}

	public static void verifyAircraftType(String type) {
		if (!Simulator.aircraftTypes.contains(type)) {
			throw new ExceptionInInitializerError();
		}
	}
}
