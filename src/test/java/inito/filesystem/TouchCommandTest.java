package inito.filesystem;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.TouchCommand;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TouchCommandTest {

    @Test
    public  void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();

        TouchCommand.execute(new String[]{"touch", "new_file.txt"}, fileSystem);
        assertTrue(fileSystem.getFileContents().containsKey("/new_file.txt"));
    }
}
