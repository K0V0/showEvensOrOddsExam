package app;

import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    public void noArgsTest() {
        App app = new App(null);
        assertEquals(Messages.ERROR_NO_ARGUMENTS, app.getResult());

        String[] emptyStrings = new String[2];
        app = new App(emptyStrings);
        assertEquals(Messages.ERROR_NO_ARGUMENTS, app.getResult());

        emptyStrings = new String[2];
        emptyStrings[0] = "";
        emptyStrings[1] = "";
        app = new App(emptyStrings);
        assertEquals(Messages.ERROR_NO_ARGUMENTS, app.getResult());

        emptyStrings = new String[2];
        emptyStrings[0] = " ";
        emptyStrings[1] = "  ";
        app = new App(emptyStrings);
        assertEquals(Messages.ERROR_NO_ARGUMENTS, app.getResult());
    }

    @Test
    public void wrongValuesTest() {
        String[] args = new String[2];
        args[0] = "11";
        args[1] = "1a";
        App app = new App(args);
        assertEquals(Messages.ERROR_WRONG_ARGUMENTS, app.getResult());
    }

    @Test
    public void toAndFromConsoleTest() {
        String[] args = new String[] { "1", "2", "3", "4", "5" };
        App app = new App(args);
        assertEquals("1, 3, 5", app.getResult());

        args = new String[] { "1", "2", "3", "4", "5", "6" };
        app = new App(args);
        assertEquals("2, 4, 6", app.getResult());

        args = new String[] { "1" };
        app = new App(args);
        assertEquals("1", app.getResult());

        args = new String[] { "2" };
        app = new App(args);
        assertEquals("", app.getResult());

        args = new String[] { "0" };
        app = new App(args);
        assertEquals("", app.getResult());

        args = new String[] { "-1" };
        app = new App(args);
        assertEquals(Messages.ERROR_WRONG_ARGUMENTS, app.getResult());
    }

    @Test
    public void filesReadingTest() {
        String[] args = new String[] { "notExisting.txt" };
        App app = new App(args);
        assertEquals(Messages.ERROR_NO_INPUT_FILE_FOUND, app.getResult());

        args = new String[] { getResourceFilePath("wrong.txt") };
        app = new App(args);
        assertEquals(Messages.ERROR_FILE_NOT_ALLOWED_CONTENT, app.getResult());

        args = new String[] { getResourceFilePath("empty.txt") };
        app = new App(args);
        assertEquals(Messages.ERROR_FILE_EMPTY, app.getResult());

        args = new String[] { getResourceFilePath("ok.txt") };
        app = new App(args);
        assertEquals("1, 3, 56789, 11", app.getResult());
    }

    @Test
    public void fileWritingTest() {
        String[] args = new String[] { getResourceFilePath("ok.txt"), getResourceFilePath("ok_out.txt") };
        App app = new App(args);
        assertTrue(app.getResult().startsWith(Messages.FILE_WRITE_DONE));
    }

    private String getResourceFilePath(String fileName) {
        return getClass().getResource(FileSystems.getDefault().getSeparator()+fileName).getFile();
    }
}
