package de.sokahr.ttt;

class TicTacToeGameField {

    protected char[][] fields;

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
        return fields.clone();
    }

    boolean setMove(int x, int y, char symbol) {
        boolean moveMade = false;
        if(x<fields.length && y<fields[x].length) {
            if (fields[x][y] == '\0'){
                fields[x][y] = symbol;
                moveMade = true;
            }
        }
        return moveMade;
    }
}
