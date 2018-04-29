package de.sokahr.ttt;

import de.sokahr.ttt.player.HumanPlayer;
import de.sokahr.ttt.player.Player;

import java.util.ArrayList;
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

        validateProperty(ConfigurationKeys.PLAYER_A_SYMBOL, properties);
        String playerASymbolProp = properties.getProperty(ConfigurationKeys.PLAYER_A_SYMBOL);
        if (playerASymbolProp.length() > 1) {
            throw new IllegalArgumentException(ConfigurationKeys.PLAYER_A_SYMBOL + " is not a single character");
        }
        char playerASymbol = playerASymbolProp.charAt(0);

        validateProperty(ConfigurationKeys.PLAYER_B_SYMBOL, properties);
        String playerBSymbolProp = properties.getProperty(ConfigurationKeys.PLAYER_B_SYMBOL);
        if (playerBSymbolProp.length() > 1) {
            throw new IllegalArgumentException(ConfigurationKeys.PLAYER_B_SYMBOL + " is not a single character");
        }
        char playerBSymbol = playerBSymbolProp.charAt(0);
        validateProperty(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL, properties);

        players = new ArrayList<>();
        players.add(new HumanPlayer(playerASymbol));
        players.add(new HumanPlayer(playerBSymbol));
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
