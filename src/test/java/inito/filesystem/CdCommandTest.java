package inito.filesystem;
import org.junit.Test;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.CdCommand;

import static org.junit.Assert.assertEquals;

public class CdCommandTest {

    @Test
  public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        CdCommand.execute(new String[]{"cd", "/new_directory"}, fileSystem);

        assertEquals("/", fileSystem.getCurrentDirectory());
    }
}
