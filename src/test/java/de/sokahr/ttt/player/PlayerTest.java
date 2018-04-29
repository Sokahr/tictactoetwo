package de.sokahr.ttt.player;

import de.sokahr.ttt.GameIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("Constructor sets symbol")
    void testConstructorSetsSymbol() {
        Player p = new PlayerTestee('P');
        assertEquals(p.getSymbol(), 'P');
    }

    private class PlayerTestee extends Player {
        PlayerTestee(char symbol) {
            super(symbol);
        }

        @Override
        public Point makeMove(GameIO gameIO, char[][] fields) {
            return null;
        }
    }
}