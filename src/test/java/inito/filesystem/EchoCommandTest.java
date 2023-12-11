package inito.filesystem;
import org.junit.Test;

import inito.filesystem.commands.EchoCommand;

import static org.junit.Assert.assertEquals;

public class EchoCommandTest {

    @Test
   public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();

        EchoCommand.execute(new String[]{"echo", "'Hello, World!'", "new_file.txt"}, fileSystem);
        assertEquals("Hello", fileSystem.getFileContents().get("/new_file.txt"));
    }
}
