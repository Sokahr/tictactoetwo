package de.sokahr.ttt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicTacToeTwoTest {

    @Test
    @DisplayName("Constructor does not accept null for properties")
    void testConstructorFailWithNull() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new TicTacToeTwo(null));
        assertEquals("properties cannot be null", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key playground.size")
    void testContructorFailWithInvalidKeyFieldSize() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            Properties prop = new Properties();
            new TicTacToeTwo(prop);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYGROUND_SIZE + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key player.a.symbol")
    void testConstructorFailWithInvalidKeyPlayerASymbol() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            Properties prop = new Properties();
            prop.setProperty(ConfigurationKeys.PLAYGROUND_SIZE, "3");
            new TicTacToeTwo(prop);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYER_A_SYMBOL + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key player.b.symbol")
    void testConstructorFailWithInvalidKeyPlayerBSymbol() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            Properties prop = new Properties();
            prop.setProperty(ConfigurationKeys.PLAYGROUND_SIZE, "3");
            prop.setProperty(ConfigurationKeys.PLAYER_A_SYMBOL, "O");
            new TicTacToeTwo(prop);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYER_B_SYMBOL + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key player.computer.symbol")
    void testConstructorFailWithInvalidKeyPlayerComputerSymbol() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            Properties prop = new Properties();
            prop.setProperty(ConfigurationKeys.PLAYGROUND_SIZE, "3");
            prop.setProperty(ConfigurationKeys.PLAYER_A_SYMBOL, "O");
            prop.setProperty(ConfigurationKeys.PLAYER_B_SYMBOL, "X");
            new TicTacToeTwo(prop);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYER_COMPUTER_SYMBOL + "' not found", e.getMessage());
    }

}