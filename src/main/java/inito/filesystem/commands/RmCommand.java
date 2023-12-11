package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;
import java.util.HashMap;


import java.util.Map;

public class RmCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 2) {
            System.out.println("Usage: rm <file_path>");
            return;
        }

        String targetPath = fileSystem.getPath(tokens[1]);

        if (!fileSystem.getDirectories().containsKey(targetPath) && !fileSystem.getFileContents().containsKey(targetPath)) {
            System.out.println("File or directory not found: " + tokens[1]);
            return;
        }

        if (fileSystem.getDirectories().containsKey(targetPath)) {
            // Directory removal
            removeDirectory(fileSystem, targetPath);
            System.out.println("Directory removed successfully");
        } else {
            // File removal
            fileSystem.getFileContents().remove(targetPath);
            System.out.println("File removed successfully");
        }
    }

    private static void removeDirectory(InMemoryFileSystem fileSystem, String targetPath) {
        Map<String, String> newFileContents = new HashMap<>();
        Map<String, Boolean> newDirectories = new HashMap<>();

        for (Map.Entry<String, String> entry : fileSystem.getFileContents().entrySet()) {
            if (!entry.getKey().startsWith(targetPath)) {
                newFileContents.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<String, Boolean> entry : fileSystem.getDirectories().entrySet()) {
            if (!entry.getKey().startsWith(targetPath)) {
                newDirectories.put(entry.getKey(), entry.getValue());
            }
        }

        fileSystem.setFileContents(newFileContents);
        fileSystem.setDirectories(newDirectories);
    }
}
