package de.sokahr.ttt;

import java.util.Iterator;
import java.util.List;

public class TicTacToeConfiguration {
    private int fieldSize;
    private List<PlayerConfiguration> players;

    public TicTacToeConfiguration(int fieldSize, List<PlayerConfiguration> players) {
        this.fieldSize = fieldSize;
        this.players = players;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Iterator<PlayerConfiguration> getPlayers() {
        return players.iterator();
    }
}
