package de.sokahr.ttt;

class TicTacToeTwoTest {
/*
    @Mock
    private TicTacToeIOSystem gameIO = mock(TicTacToeIOSystem.class);
    private Properties properties;

    @BeforeEach
    void setUp() {
        properties = new Properties();
        properties.setProperty(ConfigurationKeys.PLAYGROUND_SIZE, "3");
        properties.setProperty(ConfigurationKeys.PLAYER_A_SYMBOL, "X");
        properties.setProperty(ConfigurationKeys.PLAYER_B_SYMBOL, "O");
        properties.setProperty(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL, "#");
    }

    @Test
    @DisplayName("Constructor does not accept null for properties")
    void testConstructorFailWithNull() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new TicTacToeTwo(null, gameIO));
        assertEquals("properties cannot be null", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key playground.size")
    void testContructorFailWithInvalidKeyFieldSize() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.remove(ConfigurationKeys.PLAYGROUND_SIZE);
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYGROUND_SIZE + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key player.a.symbol")
    void testConstructorFailWithInvalidKeyPlayerASymbol() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.remove(ConfigurationKeys.PLAYER_A_SYMBOL);
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYER_A_SYMBOL + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key player.b.symbol")
    void testConstructorFailWithInvalidKeyPlayerBSymbol() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.remove(ConfigurationKeys.PLAYER_B_SYMBOL);
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYER_B_SYMBOL + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept a properties file with missing key player.computer.symbol")
    void testConstructorFailWithInvalidKeyPlayerComputerSymbol() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.remove(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL);
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals("property '" + ConfigurationKeys.PLAYER_COMPUTER_SYMBOL + "' not found", e.getMessage());
    }

    @Test
    @DisplayName("Constructor does not accept null as gameIO")
    void testConstructorFailWithNullAsGameIO() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new TicTacToeTwo(properties, null));
        assertEquals("gameIO must be provided", e.getMessage());
    }

    @Test
    @DisplayName("Constructor creates a gameField according to the property playground.size")
    void testConstructorCreatesGameField() {
        for (int size = 3; size < 11; size++) {
            properties.setProperty(ConfigurationKeys.PLAYGROUND_SIZE, String.valueOf(size));
            TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(properties, gameIO);
            assertNotNull(ticTacToeTwo.getGameField());
            assertEquals(size, ticTacToeTwo.getGameField().getFields().length);
        }
    }

    @Test
    @DisplayName("Constructor fails when the playground.size is not a number")
    void testConstructorFailWhenSizeNotANumber() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.setProperty(ConfigurationKeys.PLAYGROUND_SIZE, "xl");
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals(ConfigurationKeys.PLAYGROUND_SIZE + " is not a number", e.getMessage());
    }

    @Test
    @DisplayName("Contructor fails when the player.a.symbol is not a character")
    void testConstructorFailWhenThePlayerASymbolIsNotACharacter() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.setProperty(ConfigurationKeys.PLAYER_A_SYMBOL, "playerA");
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals(ConfigurationKeys.PLAYER_A_SYMBOL + " is not a single character", e.getMessage());
    }

    @Test
    @DisplayName("Constructor fails when the player.b.symbol is not a character")
    void testConstructorFailWhenPlayerBSymbolIsNotACharacter() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.setProperty(ConfigurationKeys.PLAYER_B_SYMBOL, "playerB");
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals(ConfigurationKeys.PLAYER_B_SYMBOL + " is not a single character", e.getMessage());
    }

    @Test
    @DisplayName("Constructor will create two human players with the corresponding symbol")
    void testConstructorCreatesHumanPlayers() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(properties, gameIO);
        assertNotNull(ticTacToeTwo.getPlayers());
        assertThat(ticTacToeTwo.getPlayers()).size().isGreaterThanOrEqualTo(2);
        assertThat(ticTacToeTwo.getPlayers()).hasAtLeastOneElementOfType(HumanPlayer.class);
        assertThat(ticTacToeTwo.getPlayers()).extracting("symbol").contains('X', 'O');
    }

    @Test
    @DisplayName("Constructor fails when the player.computer.symbol is not a character")
    void testConstructorFailsWhenPlayerComputerSymbolIsNotACharacter() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
            properties.setProperty(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL, "playerC");
            new TicTacToeTwo(properties, gameIO);
        });
        assertEquals(ConfigurationKeys.PLAYER_COMPUTER_SYMBOL + " is not a single character", e.getMessage());
    }

    @Test
    @DisplayName("Constructor will create one computer player with the corresponding symbol")
    void testConstructorCreatesComputerPlayer() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(properties, gameIO);
        assertNotNull(ticTacToeTwo.getPlayers());
        assertThat(ticTacToeTwo.getPlayers()).size().isGreaterThanOrEqualTo(1);
        assertThat(ticTacToeTwo.getPlayers()).hasAtLeastOneElementOfType(ComputerPlayer.class);
        assertThat(ticTacToeTwo.getPlayers()).extracting("symbol").contains('#');
    }

    @Test
    @DisplayName("Constructor will create all Players")
    void testConstructorCreatesAllPlayers() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(properties, gameIO);
        assertNotNull(ticTacToeTwo.getPlayers());
        assertThat(ticTacToeTwo.getPlayers()).size().isEqualTo(3);
        assertThat(ticTacToeTwo.getPlayers()).hasOnlyElementsOfType(Player.class);
        assertThat(ticTacToeTwo.getPlayers()).extracting("symbol").contains('O','X','#');
    }

    @Test
    @DisplayName("calls drawGame on GameIO")
    void testCallsDrawGame() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(properties, gameIO);
        verify(gameIO).drawGame(ticTacToeTwo.getGameField().getFields());
    }*/
}