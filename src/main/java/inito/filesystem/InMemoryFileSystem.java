package inito.filesystem;

import inito.filesystem.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InMemoryFileSystem {

    private String currentDirectory;
    private Map<String, String> fileContents;
    private Map<String, Boolean> directories;

    public InMemoryFileSystem() {
        currentDirectory = "/";
        fileContents = new HashMap<>();
        directories = new HashMap<>();
        directories.put("/", true);  
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.print(currentDirectory + "> ");
            String command = scanner.nextLine();
            exit = executeCommand(command);
        }

        scanner.close();
    }

    private boolean executeCommand(String command) {
        String[] tokens = command.trim().split("\\s+");
        
        if (tokens.length == 0) {
            return false;  
        }
    
        switch (tokens[0]) {
            case "mkdir":
                MkdirCommand.execute(tokens, this);
                break;
            case "cd":
                CdCommand.execute(tokens, this);
                break;
            case "ls":
                LsCommand.execute(tokens, this);
                break;
            case "grep":
                GrepCommand.execute(tokens, this);
                break;
            case "cat":
                CatCommand.execute(tokens, this);
                break;
            case "touch":
                TouchCommand.execute(tokens, this);
                break;
            case "echo":
                EchoCommand.execute(tokens, this);
                break;
            case "mv":
                MvCommand.execute(tokens, this);
                break;
            case "cp":
                CpCommand.execute(tokens, this);
                break;
            case "rm":
                RmCommand.execute(tokens, this);
                break;
            case "exit":
                return true;
            default:
                System.out.println("Invalid command");
        }
    
        return false;
    }
    public void moveUp() {
        String currentPath = getCurrentDirectory();
        int lastSlashIndex = currentPath.lastIndexOf("/");
        if (lastSlashIndex > 0) {
            setCurrentDirectory(currentPath.substring(0, lastSlashIndex));
        }
    }



    public String getPath(String path) {
        if (path.startsWith("/")) {
            return path;
        } else {
            return currentDirectory + "/" + path;
        }
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public Map<String, String> getFileContents() {
        return fileContents;
    }

    public void setFileContents(Map<String, String> fileContents) {
        this.fileContents = fileContents;
    }

    public Map<String, Boolean> getDirectories() {
        return directories;
    }

    public void setDirectories(Map<String, Boolean> directories) {
        this.directories = directories;
    }

    public static void main(String[] args) {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        fileSystem.run();
    }
}
