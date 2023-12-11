package inito.filesystem;

import java.io.*;
import java.util.Map;

public class FileManager {

    public static void saveState(String fileName, String currentDirectory, Map<String, String> fileContents, Map<String, Boolean> directories) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(currentDirectory);
            outputStream.writeObject(fileContents);
            outputStream.writeObject(directories);
            System.out.println("State saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    public static void loadState(String fileName, InMemoryFileSystem fileSystem) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            String currentDirectory = (String) inputStream.readObject();
            Map<String, String> fileContents = (Map<String, String>) inputStream.readObject();
            Map<String, Boolean> directories = (Map<String, Boolean>) inputStream.readObject();

            fileSystem.setCurrentDirectory(currentDirectory);
            fileSystem.setFileContents(fileContents);
            fileSystem.setDirectories(directories);

            System.out.println("State loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading state: " + e.getMessage());
        }
    }
}
