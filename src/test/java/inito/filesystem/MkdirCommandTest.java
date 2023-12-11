package inito.filesystem;
import org.junit.Test;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.MkdirCommand;

import static org.junit.Assert.assertTrue;

public class MkdirCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        MkdirCommand.execute(new String[]{"mkdir", "new_directory"}, fileSystem);

        assertTrue(fileSystem.getDirectories().containsKey("/new_directory"));
    }
}
