package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;

public class CatCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 2) {
            System.out.println("Usage: cat <file_path>");
            return;
        }

        String filePath = fileSystem.getPath(tokens[1]);
        if (!fileSystem.getFileContents().containsKey(filePath)) {
            System.out.println("File not found: " + tokens[1]);
        } else {
            System.out.println(fileSystem.getFileContents().get(filePath));
        }
    }
}
