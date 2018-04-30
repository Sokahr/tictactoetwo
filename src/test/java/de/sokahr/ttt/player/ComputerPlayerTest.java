package de.sokahr.ttt.player;

import de.sokahr.ttt.GameIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ComputerPlayerTest {
    @Test
    @DisplayName("Constructor sets Symbol")
    void testConstructorSetsSymbol() {
        ComputerPlayer p = new ComputerPlayer('P');
        assertEquals(p.getSymbol(), 'P');
    }

    @Test
    @DisplayName("makeMove returns a Point with a valid move")
    void testMakeMoveMakesAnyValidMove() {
        ComputerPlayer p = new ComputerPlayer('P');
        char[][] fields = new char[2][2];
        fields[0][0] = 'x';
        fields[1][0] = 'x';
        fields[1][1] = 'x';
        Point point = p.makeMove(mock(GameIO.class), fields);
        assertEquals(new Point(0,1), point);
    }
}