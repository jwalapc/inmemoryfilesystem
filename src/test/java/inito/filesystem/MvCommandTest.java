package inito.filesystem;
import org.junit.Test;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.MkdirCommand;
import inito.filesystem.commands.MvCommand;
import inito.filesystem.commands.TouchCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MvCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();

        TouchCommand.execute(new String[]{"touch", "file.txt"}, fileSystem);
        MkdirCommand.execute(new String[]{"mkdir", "new_directory"}, fileSystem);
        MvCommand.execute(new String[]{"mv", "file.txt", "new_directory"}, fileSystem);

        assertTrue(fileSystem.getFileContents().containsKey("/new_directory/file.txt"));
        assertEquals(0, fileSystem.getFileContents().size());
    }
}
