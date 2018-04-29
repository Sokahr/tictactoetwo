package de.sokahr.ttt.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("Constructor sets symbol")
    void testConstructorSetsSymbol() {
        Player p = new PlayerTestee('P');
        assertEquals(p.getSymbol(), 'P');
    }

    private class PlayerTestee extends Player {
        public PlayerTestee(char symbol) {
            super(symbol);
        }
    }
}