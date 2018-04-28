package de.sokahr.ttt;

import java.util.Properties;

public class TicTacToeTwo {
    public TicTacToeTwo(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("properties cannot be null");
        }
        validateProperty(ConfigurationKeys.PLAYGROUND_SIZE, properties);
        validateProperty(ConfigurationKeys.PLAYER_A_SYMBOL, properties);
        validateProperty(ConfigurationKeys.PLAYER_B_SYMBOL, properties);
        validateProperty(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL, properties);
    }

    private void validateProperty(String key, Properties properties) throws IllegalArgumentException {
        if (!properties.containsKey(key)) {
            throw new IllegalArgumentException("property '" + key + "' not found");
        }
    }
}
