package avaj.simulator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;


public final class Parser {

    static String fileName;
	static File file;
	static Scanner scanner;
    static final ArrayList<String> aircraftTypes = new ArrayList<>(Arrays.asList("Helicopter", "Baloon", "JetPlane"));


    private Parser() {
        throw new AssertionError("Parser can not be instanciated"); 
    }

    static void getRepetitions() {
        while(Parser.scanner.hasNext()) {
            try {
                String reps = Parser.scanner.nextLine();
                if (reps.isEmpty()) {
                    continue ;
                }
                else {
                    long casted = Long.parseLong(reps);
                    Simulator.repetitions = casted;
                    System.out.println("REPS: " + Simulator.repetitions);
                    return;
                }
            } catch(Exception e) {
                e.printStackTrace();
                throw(e);
            }
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

    static void verifyFormat(String[] splitted) {
		if (splitted.length != 5) {
			throw new ExceptionInInitializerError();
		}
	}

    static void verifyAircraftType(String type) {
		if (!Parser.aircraftTypes.contains(type)) {
			throw new ExceptionInInitializerError();
		}
	}




    
}
