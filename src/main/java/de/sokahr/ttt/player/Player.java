package de.sokahr.ttt.player;

import de.sokahr.ttt.GameIO;

import java.awt.*;
import java.io.IOException;

public abstract class Player {
    private char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Point makeMove(GameIO gameIO, char[][] fields) throws IOException;
}
