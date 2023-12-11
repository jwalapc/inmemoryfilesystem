package inito.filesystem.commands;

import inito.filesystem.InMemoryFileSystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        if (tokens.length < 2) {
            System.out.println("Usage: grep <pattern> <file_path>");
            return;
        }

        String pattern = tokens[1];
        String filePath = fileSystem.getPath(tokens[2]);

        if (!fileSystem.getFileContents().containsKey(filePath)) {
            System.out.println("File not found: " + filePath);
            return;
        }

        String content = fileSystem.getFileContents().get(filePath);
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(content);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
