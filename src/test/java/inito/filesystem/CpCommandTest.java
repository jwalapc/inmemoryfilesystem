package inito.filesystem;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.CpCommand;
import inito.filesystem.commands.EchoCommand;
import inito.filesystem.commands.MkdirCommand;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CpCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();

        EchoCommand.execute(new String[]{"echo", "'Hello, World! This is a test file.'", "test_file.txt"}, fileSystem);
        MkdirCommand.execute(new String[]{"mkdir", "new_directory"}, fileSystem);
        CpCommand.execute(new String[]{"cp", "test_file.txt", "new_directory"}, fileSystem);

        assertTrue(fileSystem.getFileContents().containsKey("/"));
        assertTrue(fileSystem.getFileContents().containsKey("/"));
        assertEquals(1, fileSystem.getFileContents().size());
    }
}
