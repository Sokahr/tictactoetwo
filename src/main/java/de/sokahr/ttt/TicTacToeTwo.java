package de.sokahr.ttt;

import java.util.Properties;

class TicTacToeTwo {

    private GameIO gameIO;
    private TicTacToeGameField gameField;

    TicTacToeTwo(Properties properties, GameIO gameIO) {
        this.gameIO = gameIO;
        if (gameIO == null) {
            throw new IllegalArgumentException("gameIO must be provided");
        }
        if (properties == null) {
            throw new IllegalArgumentException("properties cannot be null");
        }
        validateProperty(ConfigurationKeys.PLAYGROUND_SIZE, properties);
        int size;
        try {
            size = Integer.parseInt(properties.getProperty(ConfigurationKeys.PLAYGROUND_SIZE));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ConfigurationKeys.PLAYGROUND_SIZE + " is not a number");
        }
        gameField = new TicTacToeGameField(size);
        validateProperty(ConfigurationKeys.PLAYER_A_SYMBOL, properties);
        validateProperty(ConfigurationKeys.PLAYER_B_SYMBOL, properties);
        validateProperty(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL, properties);
    }

    private void validateProperty(String key, Properties properties) throws IllegalArgumentException {
        if (!properties.containsKey(key)) {
            throw new IllegalArgumentException("property '" + key + "' not found");
        }
    }

    TicTacToeGameField getGameField() {
        return gameField;
    }
}
