package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;

public class MkdirCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 2) {
            System.out.println("Usage: mkdir <directory_name>");
            return;
        }

        String newDirName = tokens[1];
        String newPath = fileSystem.getPath(newDirName);
        if (fileSystem.getDirectories().containsKey(newPath)) {
            System.out.println("Directory already exists: " + newDirName);
        } else {
            fileSystem.getDirectories().put(newPath, true);
            System.out.println("Directory created: " + newDirName);
        }
    }
}
