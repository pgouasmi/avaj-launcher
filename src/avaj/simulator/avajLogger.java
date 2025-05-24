package avaj.simulator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class avajLogger {

	private PrintWriter writer;
	private static avajLogger _instance;
	private static final String filename = "simulation.txt";

	private avajLogger() throws IOException {
		writer = new PrintWriter(filename);
		initializeFile();
	}

	public static void createInstance() throws IOException {
			_instance = new avajLogger(); 
	}

    public static avajLogger getInstance() {
        return _instance;
    }

	private void initializeFile() throws IOException {
        Path filePath = Paths.get(filename);
        
        // 1. Créer le fichier s'il n'existe pas (et les dossiers parents)
        if (!Files.exists(filePath)) {
            // Créer les dossiers parents si nécessaire
            // Path parentDir = filePath.getParent();
            // if (parentDir != null && !Files.exists(parentDir)) {
            //     Files.createDirectories(parentDir);
            // }
            // Créer le fichier
            Files.createFile(filePath);
            System.out.println("Fichier de log créé : " + filename);
        }
        
        // 2. Vérifier les droits d'écriture
        if (!Files.isWritable(filePath)) {
            throw new IOException("Pas de droits d'écriture sur le fichier : " + filename);
        }
        
        // 3. Vider le fichier s'il contient déjà du contenu
        if (Files.size(filePath) > 0) {
            Files.write(filePath, new byte[0]); // Vider le fichier
            System.out.println("Contenu du fichier de log effacé : " + filename);
        }
    }

	public void writeToFile(String toWrite) {
		writer.println(toWrite);
        writer.flush();
	}

    public void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}
