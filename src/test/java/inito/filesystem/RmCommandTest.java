package inito.filesystem;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.RmCommand;
import inito.filesystem.commands.TouchCommand;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class RmCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();

        TouchCommand.execute(new String[]{"touch", "file.txt"}, fileSystem);
        RmCommand.execute(new String[]{"rm", "file.txt"}, fileSystem);

        assertFalse(fileSystem.getFileContents().containsKey("/file.txt"));
    }
}
