package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;

public class TouchCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 2) {
            System.out.println("Usage: touch <file_path>");
            return;
        }

        String filePath = fileSystem.getPath(tokens[1]);
        if (fileSystem.getFileContents().containsKey(filePath)) {
            System.out.println("File already exists: " + tokens[1]);
        } else {
            fileSystem.getFileContents().put(filePath, "");
            System.out.println("File created: " + tokens[1]);
        }
    }
}
