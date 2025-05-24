package avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// execptions: wromng aircraft type, no repetitions, malformed aircraft infos, no aircrafts, broken coordinates


public final class Parser {

    public static class WrongAircraftTypeException extends Exception {
        public WrongAircraftTypeException(String malformedType) {
            super("Unknown type of aircraft \'" + malformedType + "\'");
        }
    }

    public static class WrongRepetitionsException extends Exception {
        public WrongRepetitionsException(String firstLine) {
            super("First line of the file is not a number or a malformed number: \'" + firstLine + "\'");
        }
    }

    public static class MalformedAircraftDataException extends Exception {
        public MalformedAircraftDataException(String malformedLine) {
            super("Malformed line in file: \'" + malformedLine + "\'");
        }
    }

    public static class NoAircraftInFileException extends Exception {
        public NoAircraftInFileException() {
            super("No aircraft to instanciate in file!");
        }
    }

    public static class BrokenCoordinatesException extends Exception {
        public BrokenCoordinatesException(String coordinates) {
            super("Broken cooridinates: \'"+ coordinates + "\'");
        }
    }


    public static class EmptyFileException extends Exception {
        public EmptyFileException(String coordinates) {
            super("The file is empty!");
        }
    }

    static String fileName;
	static File file;
	static Scanner scanner;
    static final ArrayList<String> aircraftTypes = new ArrayList<>(Arrays.asList("Helicopter", "Baloon", "JetPlane"));


    private Parser() {
        throw new AssertionError("Parser can not be instanciated"); 
    }

    static void getRepetitions() throws WrongRepetitionsException, EmptyFileException {
        try {
            String reps = "";
            while(Parser.scanner.hasNext()) {
                reps = Parser.scanner.nextLine();
                if (reps.isEmpty()) {}
                else {
                    long casted = Long.parseLong(reps);
                    Simulator.repetitions = casted;
                    // System.out.println("REPS: " + Simulator.repetitions);
                    return;
                }
            }
            if (reps.isEmpty()) {
                throw new Parser.EmptyFileException(reps);
            }
        } catch(Exception e) {
            throw e;
        }
	}

    static void openFile(String p_fileName) throws FileNotFoundException {
        try {
            Parser.fileName = p_fileName;
            Parser.file = new File(Parser.fileName);
            if (Parser.file != null) 
                Parser.scanner = new Scanner(Parser.file);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    static void verifyFormat(String[] splitted) throws MalformedAircraftDataException {
        // System.out.println("in verify format, len: " + splitted.length);

		if (splitted.length != 5) {
            String[] copy = splitted;

            for (int i = 0; i < copy.length - 1; i++) {
                copy[i] += " ";
            }

            String originalLine = "";

            for (int i = 0; i < copy.length; i++) {
                originalLine += copy[i];
            }
            // System.out.println("Throwing malformed, " + originalLine);
			throw new MalformedAircraftDataException(originalLine);
		}
	}

    static void verifyAircraftType(String type) throws WrongAircraftTypeException {
		if (!Parser.aircraftTypes.contains(type)) {
			throw new WrongAircraftTypeException(type);
		}
	}
}
