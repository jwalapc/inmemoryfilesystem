package inito.filesystem.commands;

import java.util.Map.Entry;

import inito.filesystem.InMemoryFileSystem;

public class MvCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 3) {
            System.out.println("Usage: mv <source_path> <destination_path>");
            return;
        }

        String sourcePath = fileSystem.getPath(tokens[1]);
        String destinationPath = fileSystem.getPath(tokens[2]);

        if (!fileSystem.getFileContents().containsKey(sourcePath) && !fileSystem.getDirectories().containsKey(sourcePath)) {
            System.out.println("Source not found: " + tokens[1]);
            return;
        }

        if (fileSystem.getDirectories().containsKey(destinationPath) && fileSystem.getDirectories().get(destinationPath)) {
            String fileName = sourcePath.substring(sourcePath.lastIndexOf('/') + 1);
            String newDestinationPath = destinationPath + "/" + fileName;

            if (fileSystem.getDirectories().containsKey(newDestinationPath) || fileSystem.getFileContents().containsKey(newDestinationPath)) {
                System.out.println("Destination path already exists: " + tokens[2]);
                return;
            }

            moveFileOrDirectory(fileSystem, sourcePath, newDestinationPath);
            System.out.println("Moved successfully");
        } else if (!fileSystem.getDirectories().containsKey(destinationPath.substring(0, destinationPath.lastIndexOf('/')))) {
            System.out.println("Destination directory not found: " + tokens[2]);
        } else {
            moveFileOrDirectory(fileSystem, sourcePath, destinationPath);
            System.out.println("Moved successfully");
        }
    }

    private static void moveFileOrDirectory(InMemoryFileSystem fileSystem, String sourcePath, String destinationPath) {
        if (fileSystem.getDirectories().containsKey(sourcePath)) {
            moveDirectory(fileSystem, sourcePath, destinationPath);
        } else {
            fileSystem.getFileContents().put(destinationPath, fileSystem.getFileContents().get(sourcePath));
            fileSystem.getFileContents().remove(sourcePath);
        }
    }

    private static void moveDirectory(InMemoryFileSystem fileSystem, String sourcePath, String destinationPath) {
        for (Entry<String, Boolean> entry : fileSystem.getDirectories().entrySet()) {
            if (entry.getKey().startsWith(sourcePath)) {
                String relativePath = entry.getKey().substring(sourcePath.length());
                String newPath = destinationPath + relativePath;
                fileSystem.getDirectories().put(newPath, entry.getValue());
            }
        }
        fileSystem.getDirectories().remove(sourcePath);
    }
}
