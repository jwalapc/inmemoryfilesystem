package inito.filesystem;

import inito.filesystem.InMemoryFileSystem;
import inito.filesystem.commands.LsCommand;
import inito.filesystem.commands.MkdirCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LsCommandTest {

    @Test
    public void execute() {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        MkdirCommand.execute(new String[]{"mkdir", "new_directory"}, fileSystem);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        LsCommand.execute(new String[]{"ls"}, fileSystem);

        System.setOut(System.out);

        assertTrue(outContent.toString().contains("new_directory"));
    }
}
