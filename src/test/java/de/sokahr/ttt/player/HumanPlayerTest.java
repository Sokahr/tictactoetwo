package de.sokahr.ttt.player;

import de.sokahr.ttt.GameIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HumanPlayerTest {

    @Test
    @DisplayName("Constructor sets symbol")
    void testConstructorSetsSymbol() {
        HumanPlayer p = new HumanPlayer('P');
        assertEquals(p.getSymbol(), 'P');
    }

    @Test
    @DisplayName("makeMove should request the input")
    void testMakeMoveCallsGameIO() throws IOException {
        GameIO mockIO = mock(GameIO.class);
        when(mockIO.getInput()).thenReturn("1,1");
        HumanPlayer humanPlayer = new HumanPlayer('X');
        humanPlayer.makeMove(mockIO, new char[1][1]);
        verify(mockIO, VerificationModeFactory.times(1)).getInput();
    }

    @Test
    @DisplayName("makeMove returns a Point with the correct coordinates coressponding to the input")
    void testMakeMoveReturnsAValidMove() throws IOException {
        GameIO mockIO = mock(GameIO.class);
        HumanPlayer humanPlayer = new HumanPlayer('X');
        when(mockIO.getInput()).thenReturn("1,1", "2,1", "1,3");
        Point point = humanPlayer.makeMove(mockIO, new char[1][1]);
        assertEquals(new Point(0,0), point);
        point = humanPlayer.makeMove(mockIO, new char[2][2]);
        assertEquals(new Point(1,0), point);
        point = humanPlayer.makeMove(mockIO, new char[3][3]);
        assertEquals(new Point(0,2), point);
    }

    @Test
    @DisplayName("makeMove shows error on invalid input and request input again")
    void testMakeMoveShowsErrorOnInvalidInputAndRequestInputAgain() throws IOException{
        GameIO mockIO = mock(GameIO.class);
        HumanPlayer humanPlayer = new HumanPlayer('P');
        when(mockIO.getInput()).thenReturn("what?","1,1");
        humanPlayer.makeMove(mockIO, new char[1][1]);
        verify(mockIO, times(2)).getInput();
        verify(mockIO).showErrorMessage("wrong input format please enter [column],[row] i.E. 2,3");
    }

    @Test
    @DisplayName("makeMove shows error in occupied field and requests input again")
    void testMakeMoveShowsErrorOnOccupiedFieldAndRequestsInputAgain() throws IOException {
        GameIO mockIO = mock(GameIO.class);
        HumanPlayer humanPlayer = new HumanPlayer('X');
        char[][] fields = new char[2][2];
        fields[0][0] = 'X';
        when(mockIO.getInput()).thenReturn("1,1","1,2");
        humanPlayer.makeMove(mockIO, fields);
        verify(mockIO, times(2)).getInput();
        verify(mockIO).showErrorMessage("field is already occupied, please choose a different one");
    }

    @Test
    @DisplayName("makeMove shows error on out of reach fields and requests input again")
    void testMakeMoveShowsErrorOnOutOfReachFieldAndRequestInputAgain() throws IOException {
        GameIO mockIO = mock(GameIO.class);
        HumanPlayer humanPlayer = new HumanPlayer('X');
        char[][] fields = new char[2][2];
        when(mockIO.getInput()).thenReturn("3,1","1,3", "1,1");
        humanPlayer.makeMove(mockIO, fields);
        verify(mockIO, times(3)).getInput();
        verify(mockIO, times(2)).showErrorMessage("field is not in reach, please choose a different one");
    }

    @Test
    @DisplayName("makeMove rethrows IOException, the player can not input the move.")
    void testMakeMoveRethrowsIOException() throws IOException {
        GameIO mockIO = mock(GameIO.class);
        HumanPlayer humanPlayer = new HumanPlayer('X');
        when(mockIO.getInput()).thenThrow(IOException.class);

        assertThrows(IOException.class, ()->humanPlayer.makeMove(mockIO, new char[2][2]));

    }
}