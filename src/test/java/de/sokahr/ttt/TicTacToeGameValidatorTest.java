package de.sokahr.ttt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameValidatorTest {

    @Test
    @DisplayName("validateMovesPossible returns true if at least one field is not occupied")
    void testValidateMovesPossibleReturnsTrueIfAtLeastOneFieldIsNotOccupied() {
        char[][] fields = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i!=0 && j!=0){
                    fields[i][j]='X';
                }
                assertTrue(TicTacToeGameValidator.validateMovesPossible(fields));
            }
        }
    }

    @Test
    @DisplayName("validateMovesPossible returns false if all fields are occupied")
    void testValidateMovesPossibleReturnsFalseIfAllFieldsAreOccupied() {
        char[][] fields = new char[2][2];
        fields[0][0]='X';
        fields[1][0]='X';
        fields[0][1]='X';
        fields[1][1]='X';
        assertFalse(TicTacToeGameValidator.validateMovesPossible(fields));
    }

    @Test
    @DisplayName("validateWin returns false if not a win three following in any direction")
    void testValidateWinReturnsFalseIfNotThreeFollowInAnyDirection() {
        assertFalse(TicTacToeGameValidator.validateWin('X', new char[3][3]));
    }

    @Test
    @DisplayName("validateWin returns true for three of the same symbol in a row")
    void testValidateWinReturnsTrueForThreeHorizontalSymbols() {
        char[][] fields = {
                {'X','X','X',' '},
                {' ','O','O','O'},
                {'W','W','W',' '},
                {'T','T','T',' '}
        };
        assertTrue(TicTacToeGameValidator.validateWin('X', fields));
        assertTrue(TicTacToeGameValidator.validateWin('O', fields));
        assertTrue(TicTacToeGameValidator.validateWin('W', fields));
        assertTrue(TicTacToeGameValidator.validateWin('T', fields));
    }

    @Test
    @DisplayName("validateWin returns true for three of the same symbol in a column")
    void testValidateWinReturnsTrueForThreeVerticalSymbols() {
        char[][] fields = {
                {'X',' ','W',' '},
                {'X','O','W','T'},
                {'X','O','W','T'},
                {' ','O',' ','T'}
        };
        assertTrue(TicTacToeGameValidator.validateWin('X', fields));
        assertTrue(TicTacToeGameValidator.validateWin('O', fields));
        assertTrue(TicTacToeGameValidator.validateWin('W', fields));
        assertTrue(TicTacToeGameValidator.validateWin('T', fields));
    }

    @Test
    @DisplayName("validateWin returns true for three of the same symbol in a diagonal")
    void testValidateWinReturnsTrueForThreeDiagonalSymbols() {
        char[][] fieldsDiagonalRight = {
                {' ','O',' ',' '},
                {'W','X','O',' '},
                {' ','W','X','O'},
                {' ',' ','W','X'}
        };
        assertTrue(TicTacToeGameValidator.validateWin('X', fieldsDiagonalRight));
        assertTrue(TicTacToeGameValidator.validateWin('O', fieldsDiagonalRight));
        assertTrue(TicTacToeGameValidator.validateWin('W', fieldsDiagonalRight));

        char[][] fieldsDiagonalLeft = {
                {' ',' ','W',' '},
                {' ','W','X','O'},
                {'W','X','O',' '},
                {'X','O',' ',' '}
        };
        assertTrue(TicTacToeGameValidator.validateWin('X', fieldsDiagonalLeft));
        assertTrue(TicTacToeGameValidator.validateWin('O', fieldsDiagonalLeft));
        assertTrue(TicTacToeGameValidator.validateWin('W', fieldsDiagonalLeft));
    }
}