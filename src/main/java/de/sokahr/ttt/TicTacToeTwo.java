package de.sokahr.ttt;

import de.sokahr.ttt.player.ComputerPlayer;
import de.sokahr.ttt.player.HumanPlayer;
import de.sokahr.ttt.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

class TicTacToeTwo {

    private GameIO gameIO;
    private TicTacToeGameField gameField;
    private List<Player> players;

    TicTacToeTwo(Properties properties, GameIO gameIO) {
        this.gameIO = gameIO;
        if (gameIO == null) {
            throw new IllegalArgumentException("gameIO must be provided");
        }
        if (properties == null) {
            throw new IllegalArgumentException("properties cannot be null");
        }
        initGameField(properties);

        players = new ArrayList<>();
        players.add(new HumanPlayer(getSymbol(properties, ConfigurationKeys.PLAYER_A_SYMBOL)));
        players.add(new HumanPlayer(getSymbol(properties, ConfigurationKeys.PLAYER_B_SYMBOL)));
        players.add(new ComputerPlayer(getSymbol(properties, ConfigurationKeys.PLAYER_COMPUTER_SYMBOL)));
        Collections.shuffle(players);
    }

    private char getSymbol(Properties properties, String symbolKey) {
        validateProperty(symbolKey, properties);
        String playerASymbolProp = properties.getProperty(symbolKey);
        if (playerASymbolProp.length() > 1) {
            throw new IllegalArgumentException(symbolKey + " is not a single character");
        }
        return playerASymbolProp.charAt(0);
    }

    private void initGameField(Properties properties) {
        validateProperty(ConfigurationKeys.PLAYGROUND_SIZE, properties);
        int size;
        try {
            size = Integer.parseInt(properties.getProperty(ConfigurationKeys.PLAYGROUND_SIZE));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ConfigurationKeys.PLAYGROUND_SIZE + " is not a number");
        }
        gameField = new TicTacToeGameField(size);
    }

    private void validateProperty(String key, Properties properties) throws IllegalArgumentException {
        if (!properties.containsKey(key)) {
            throw new IllegalArgumentException("property '" + key + "' not found");
        }
    }

    TicTacToeGameField getGameField() {
        return gameField;
    }

    List<Player> getPlayers() {
        return players;
    }
}
