package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;

public class CdCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 2) {
            System.out.println("Usage: cd <directory_path>");
            return;
        }

        String targetPath = fileSystem.getPath(tokens[1]);
        if (fileSystem.getDirectories().containsKey(targetPath) && fileSystem.getDirectories().get(targetPath)) {
            fileSystem.setCurrentDirectory(targetPath);
        } else {
            System.out.println("Directory not found: " + tokens[1]);
        }
    }
}
