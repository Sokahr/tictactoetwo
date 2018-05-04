package de.sokahr.ttt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropertyConfigurationReaderTest {

    @Mock
    private GameIO gameIO;

    @BeforeEach
    void setUp() {
        gameIO = mock(GameIO.class);
    }

    @Test
    @DisplayName("read the properties and set configuration as given")
    void testReadProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeys.PLAYGROUND_SIZE, "7");
        properties.setProperty(PropertyKeys.PLAYER_A_SYMBOL, "A");
        properties.setProperty(PropertyKeys.PLAYER_B_SYMBOL, "B");
        properties.setProperty(PropertyKeys.PLAYER_COMPUTER_SYMBOL, "C");
        PropertyConfigurationReader propertyConfigurationReader = new PropertyConfigurationReader(properties);
        TicTacToeConfiguration configuration = propertyConfigurationReader.read(gameIO);

        assertAll(
                () -> assertEquals(7, configuration.getFieldSize()),
                () -> assertThat(configuration.getPlayers()).hasSize(3),
                () -> assertThat(configuration.getPlayers()).hasOnlyElementsOfType(PlayerConfiguration.class),
                () -> assertThat(configuration.getPlayers()).extracting("playerSymbol").contains('A', 'B', 'C'),
                () -> assertThat(configuration.getPlayers()).extracting("playerType")
                        .contains(PlayerConfiguration.HUMAN, PlayerConfiguration.COMPUTER)
        );
    }

    @Test
    @DisplayName("No property-object, show error message and set default values")
    void testReadFieldSizeSetsDefaultWhenPropertyIsNull() {
        PropertyConfigurationReader reader = new PropertyConfigurationReader(null);
        TicTacToeConfiguration configuration = reader.read(gameIO);

        assertAll(
                () -> assertEquals(PropertyConfigurationReader.DEFAULT_PLAYGROUND_SIZE, configuration.getFieldSize()),
                () -> assertThat(configuration.getPlayers()).hasSize(3),
                () -> assertThat(configuration.getPlayers()).hasOnlyElementsOfType(PlayerConfiguration.class),
                () -> assertThat(configuration.getPlayers()).extracting("playerSymbol")
                        .contains(PropertyConfigurationReader.DEFAULT_PLAYER_A_SYMBOL,
                                PropertyConfigurationReader.DEFAULT_PLAYER_B_SYMBOL,
                                PropertyConfigurationReader.DEFAULT_PLAYER_COMPUTER_SYMBOL),
                () -> assertThat(configuration.getPlayers()).extracting("playerType")
                        .contains(PlayerConfiguration.HUMAN, PlayerConfiguration.COMPUTER)
        );
        verify(gameIO, atLeastOnce()).showErrorMessage(any());
    }

    @Test
    @DisplayName("No playground size, show error and set default value")
    void testReadPlaygroundSizeNoPlaygroundSize() {
        Properties properties = new Properties();
        PropertyConfigurationReader reader = new PropertyConfigurationReader(properties);
        int playgroundSize = reader.readPlaygroundSize(gameIO);
        assertEquals(PropertyConfigurationReader.DEFAULT_PLAYGROUND_SIZE, playgroundSize);
        verify(gameIO, atLeastOnce()).showErrorMessage(any());
    }

    @Test
    @DisplayName("Wrong playground size, show error and set default value")
    void testWrongPlagroundSize() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeys.PLAYGROUND_SIZE, "XL");
        PropertyConfigurationReader reader = new PropertyConfigurationReader(properties);
        int playgroundSize = reader.readPlaygroundSize(gameIO);
        assertEquals(PropertyConfigurationReader.DEFAULT_PLAYGROUND_SIZE, playgroundSize);
        verify(gameIO, atLeastOnce()).showErrorMessage(any());
    }

    @Test
    @DisplayName("No player symbol, show error and set default value")
    void testNoPlayerSymbol() {
        Properties properties = new Properties();
        PropertyConfigurationReader reader = new PropertyConfigurationReader(properties);
        PlayerConfiguration playerConfiguration =
                reader.getPlayerConfiguration(gameIO,
                        PlayerConfiguration.HUMAN,
                        PropertyKeys.PLAYER_A_SYMBOL,
                        PropertyConfigurationReader.DEFAULT_PLAYER_A_SYMBOL);

        assertAll(
                () -> assertThat(playerConfiguration.getPlayerSymbol())
                        .isEqualTo(PropertyConfigurationReader.DEFAULT_PLAYER_A_SYMBOL),
                () -> assertThat(playerConfiguration.getPlayerType()).isEqualTo(PlayerConfiguration.HUMAN)
        );
        verify(gameIO, atLeastOnce()).showErrorMessage(any());
    }

    @Test
    @DisplayName("Wrong player symbol, show error and set default value")
    void testWrongPlayerSymbol() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeys.PLAYER_A_SYMBOL, "PLAYER A");
        PropertyConfigurationReader reader = new PropertyConfigurationReader(properties);
        PlayerConfiguration playerConfiguration =
                reader.getPlayerConfiguration(gameIO,
                        PlayerConfiguration.HUMAN,
                        PropertyKeys.PLAYER_A_SYMBOL,
                        PropertyConfigurationReader.DEFAULT_PLAYER_A_SYMBOL);

        assertAll(
                () -> assertThat(playerConfiguration.getPlayerSymbol())
                        .isEqualTo(PropertyConfigurationReader.DEFAULT_PLAYER_A_SYMBOL),
                () -> assertThat(playerConfiguration.getPlayerType()).isEqualTo(PlayerConfiguration.HUMAN)
        );
        verify(gameIO, atLeastOnce()).showErrorMessage(any());
    }

    @Test
    @DisplayName("Any of the two Players got the same symbol, show error and set defaults")
    void testPlayersGotSameSymbols() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeys.PLAYER_A_SYMBOL, "A");
        properties.setProperty(PropertyKeys.PLAYER_B_SYMBOL, "A");
        PropertyConfigurationReader reader = new PropertyConfigurationReader(properties);
        TicTacToeConfiguration configuration = reader.read(gameIO);

        assertAll(
                () -> assertThat(configuration.getPlayers()).extracting("playerSymbol")
                        .contains(PropertyConfigurationReader.DEFAULT_PLAYER_A_SYMBOL,
                                PropertyConfigurationReader.DEFAULT_PLAYER_B_SYMBOL,
                                PropertyConfigurationReader.DEFAULT_PLAYER_COMPUTER_SYMBOL),
                () -> assertThat(configuration.getPlayers()).extracting("playerType")
                        .contains(PlayerConfiguration.HUMAN, PlayerConfiguration.COMPUTER)
        );
        verify(gameIO, atLeastOnce()).showErrorMessage(any());
    }
}