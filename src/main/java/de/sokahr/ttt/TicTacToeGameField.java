package de.sokahr.ttt;

class TicTacToeGameField {

    private char[][] fields;

    TicTacToeGameField(int size) {
        if (size < 3) {
            throw new IllegalArgumentException("Gamefield size must be larger or equal 3");
        }
        if (size > 10) {
            throw new IllegalArgumentException("Gamefield size must be smaller or equal 10");
        }
        fields = new char[size][size];
    }

    char[][] getFields() {
        return fields;
    }
}
