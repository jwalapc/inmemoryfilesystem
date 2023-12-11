package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;

public class EchoCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 4 || !tokens[2].equals(">")) {
            System.out.println("Usage: echo '<text>' > <file_path>");
            return;
        }

        StringBuilder textBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(">")) {
                break;
            }
            textBuilder.append(tokens[i]).append(" ");
        }
        String text = textBuilder.toString().trim();

        String filePath = fileSystem.getPath(tokens[tokens.length - 1]);

        if (!fileSystem.getFileContents().containsKey(filePath)) {
            System.out.println("File not found: " + tokens[tokens.length - 1]);
        } else {
            fileSystem.getFileContents().put(filePath, text);
            System.out.println("Text written to file: " + tokens[tokens.length - 1]);
        }
    }
}
