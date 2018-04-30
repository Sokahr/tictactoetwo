package de.sokahr.ttt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameFieldTest {

    @Test
    @DisplayName("Constructor do not allow the size to be lower then 3")
    void testConstructorFailsWhenSizeSmallerThree() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new TicTacToeGameField(2));
        assertEquals("Gamefield size must be larger or equal 3", e.getMessage());
    }

    @Test
    @DisplayName("Constructor do not allow the size to be larger then 10")
    void testConstructorFailsWhenSizeLargenTen() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new TicTacToeGameField(11));
        assertEquals("Gamefield size must be smaller or equal 10", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not throw any Exception when called with correct size")
    void testConstructorAllowsValidValues() {
        for (int i = 3; i < 11; i++) {
            new TicTacToeGameField(i);
        }
    }

    @Test
    @DisplayName("Constructor initialize the gameFiledArray appropriate")
    void testConstructorInitialiseTheGameFieldArrayAppropiet() {
        for (int size = 3; size < 11; size++) {
            TicTacToeGameField ticTacToeGameField = new TicTacToeGameField(size);
            assertNotNull(ticTacToeGameField.getFields());
            assertEquals(size, ticTacToeGameField.getFields().length);
            assertEquals(size, ticTacToeGameField.getFields()[0].length);
        }
    }

    @Test
    @DisplayName("setMove returns true for valid move and sets symbol on the field")
    void testSetMoveReturnsTrueForValidMoveAndSetSymbol() {
        TicTacToeGameField ticTacToeGameField = new TicTacToeGameField(3);
        boolean set = ticTacToeGameField.setMove(0,0, 'X');
        assertEquals(ticTacToeGameField.fields[0][0], 'X');
        assertTrue(set);
    }

    @Test
    @DisplayName("setMove returns false for occupied move and do not set the symbol on that field")
    void testSetMoveReturnsFalseForOccupiedMove() {
        TicTacToeGameField ticTacToeGameField = new TicTacToeGameField(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToeGameField.fields[i][j]='X';
            }
        }
        boolean set = ticTacToeGameField.setMove(2,0, 'O');
        assertFalse(set);
        assertNotEquals(ticTacToeGameField.fields[2][0], 'O');
    }

    @Test
    @DisplayName("set move returns false for invalid move")
    void testSetMoveReturnsFalseForInvalidMove() {
        TicTacToeGameField ticTacToeGameField = new TicTacToeGameField(3);
        boolean set = ticTacToeGameField.setMove(3,3, 'X');
        assertFalse(set);
    }
}