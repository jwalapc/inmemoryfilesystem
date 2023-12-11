package inito.filesystem;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.EchoCommand;
import inito.filesystem.commands.GrepCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrepCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EchoCommand.execute(new String[]{"echo", "'Hello, World! This is a test file.'", "test_file.txt"}, fileSystem);
        GrepCommand.execute(new String[]{"grep", "test", "test_file.txt"}, fileSystem);

        assertEquals("test", outputStream.toString().trim());
    }
}
