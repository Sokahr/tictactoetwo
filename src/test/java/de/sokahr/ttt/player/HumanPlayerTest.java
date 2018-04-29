package de.sokahr.ttt.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    @DisplayName("Constructor sets symbol")
    void testConstructorSetsSymbol() {
        HumanPlayer p = new HumanPlayer('P');
        assertEquals(p.getSymbol(), 'P');
    }
}