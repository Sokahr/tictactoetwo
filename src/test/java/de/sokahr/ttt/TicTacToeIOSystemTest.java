package de.sokahr.ttt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeIOSystemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream sysOut;
    private PrintStream sysErr;

    @BeforeEach
    void setUp() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));

        sysErr = System.err;
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(sysOut);
        System.setErr(sysErr);
    }

    @Test
    void testShowErrorMessage() {
        TicTacToeIOSystem ticTacToeIOSystem = new TicTacToeIOSystem();
        ticTacToeIOSystem.showErrorMessage("Error");

        assertTrue(errContent.toString().contains("Error"));
    }
}