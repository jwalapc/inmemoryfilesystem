package inito.filesystem.commands;

import java.util.Map.Entry;

import inito.filesystem.InMemoryFileSystem;

public class LsCommand {
    public static void execute(String[] tokens, InMemoryFileSystem fileSystem) {
        String targetPath = (tokens.length < 2) ? fileSystem.getCurrentDirectory() : fileSystem.getPath(tokens[1]);
        for (Entry<String, Boolean> entry : fileSystem.getDirectories().entrySet()) {
            if (entry.getKey().startsWith(targetPath) && entry.getKey().length() > targetPath.length()) {
                String relativePath = entry.getKey().substring(targetPath.length() + 1);
                if (!relativePath.contains("/")) {
                    System.out.println(relativePath + "/");
                }
            }
        }

        for (Entry<String, String> entry : fileSystem.getFileContents().entrySet()) {
            if (entry.getKey().startsWith(targetPath)) {
                String relativePath = entry.getKey().substring(targetPath.length() + 1);
                System.out.println(relativePath);
            }
        }
    }
}
