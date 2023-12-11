package inito.filesystem;
import org.junit.Test;

import inito.filesystem.commands.CatCommand;
import inito.filesystem.commands.EchoCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CatCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EchoCommand.execute(new String[]{"echo", "'Hello, World! This is a test file.'", "test_file.txt"}, fileSystem);
        assertEquals("Usage: echo '<text>' > <file_path>", outputStream.toString().trim());

    }
}
