package de.sokahr.ttt.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComputerPlayerTest {
    @Test
    @DisplayName("Constructor sets Symbol")
    void testConstructorSetsSymbol() {
        ComputerPlayer p = new ComputerPlayer('P');
        assertEquals(p.getSymbol(), 'P');
    }
}