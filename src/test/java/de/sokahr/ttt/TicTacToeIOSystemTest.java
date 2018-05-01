package de.sokahr.ttt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicTacToeIOSystemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream sysOut;
    private PrintStream sysErr;
    private InputStream inputStream;

    @BeforeEach
    void setUp() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));

        sysErr = System.err;
        System.setErr(new PrintStream(errContent));

        inputStream = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setOut(sysOut);
        System.setErr(sysErr);
        System.setIn(inputStream);
    }

    @Test
    @DisplayName("Prints over System.err the errorMessage")
    void testShowErrorMessage() {
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        ticTacToeIOSystem.showErrorMessage("Error");

        assertTrue(errContent.toString().contains("Error"));
    }

    @Test
    @DisplayName("Prints over System.out the actual gameField")
    void testDrawGame() {
        char[][] fields = new char[3][3];
        ByteArrayOutputStream expectedOutput = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(expectedOutput);
        ps.println("   1   2   3   ");
        ps.println(" |---|---|---| ");
        ps.println("1|   |   |   |1");
        ps.println(" |---|---|---| ");
        ps.println("2|   |   |   |2");
        ps.println(" |---|---|---| ");
        ps.println("3|   |   |   |3");
        ps.println(" |---|---|---| ");
        ps.println("   1   2   3   ");
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        ticTacToeIOSystem.drawGame(fields);
        assertEquals(expectedOutput.toString(), outContent.toString());

        fields = new char[4][4];
        ps.println("   1   2   3   4   ");
        ps.println(" |---|---|---|---| ");
        ps.println("1|   |   |   |   |1");
        ps.println(" |---|---|---|---| ");
        ps.println("2|   |   |   |   |2");
        ps.println(" |---|---|---|---| ");
        ps.println("3|   |   |   |   |3");
        ps.println(" |---|---|---|---| ");
        ps.println("4|   |   |   |   |4");
        ps.println(" |---|---|---|---| ");
        ps.println("   1   2   3   4   ");
        ticTacToeIOSystem.drawGame(fields);
        assertEquals(expectedOutput.toString(), outContent.toString());

        fields = new char[4][4];
        fields[3][0]='O';
        fields[1][3]='X';
        ps.println("   1   2   3   4   ");
        ps.println(" |---|---|---|---| ");
        ps.println("1|   |   |   | O |1");
        ps.println(" |---|---|---|---| ");
        ps.println("2|   |   |   |   |2");
        ps.println(" |---|---|---|---| ");
        ps.println("3|   |   |   |   |3");
        ps.println(" |---|---|---|---| ");
        ps.println("4|   | X |   |   |4");
        ps.println(" |---|---|---|---| ");
        ps.println("   1   2   3   4   ");
        ticTacToeIOSystem.drawGame(fields);
        assertEquals(expectedOutput.toString(), outContent.toString());
    }

    @Test
    @DisplayName("getInput returns the current line of input")
    void testGetInput() throws IOException {
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        ticTacToeIOSystem.bufferedReader = mock(BufferedReader.class);
        when(ticTacToeIOSystem.bufferedReader.readLine()).thenReturn("test input", "1,2");
        String input = ticTacToeIOSystem.getInput();
        assertEquals("test input", input);

        input = ticTacToeIOSystem.getInput();
        assertEquals("1,2", input);
    }

    @Test
    @DisplayName("getInput rethrows the IOException to inform the calling classes.")
    void testGetInputRethrowsIOException() throws IOException {
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        ticTacToeIOSystem.bufferedReader = mock(BufferedReader.class);
        when(ticTacToeIOSystem.bufferedReader.readLine()).thenThrow(IOException.class);
        assertThrows(IOException.class, ()->ticTacToeIOSystem.getInput());
    }

    @Test
    @DisplayName("showInfoMessage just prints the message over System.out ")
    void testShowInfoMessagePrintsMessage() {
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        ticTacToeIOSystem.showInfoMessage("Info");
        assertTrue(outContent.toString().contains("Info"));
    }
}