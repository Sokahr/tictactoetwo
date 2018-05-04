package de.sokahr.ttt;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyConfigurationReader implements ConfigurationReader{

    public static final int DEFAULT_PLAYGROUND_SIZE = 4;
    public static final char DEFAULT_PLAYER_A_SYMBOL = 'X';
    public static final char DEFAULT_PLAYER_B_SYMBOL = 'O';
    public static final char DEFAULT_PLAYER_COMPUTER_SYMBOL = '@';
    private Properties properties;

    public PropertyConfigurationReader(Properties properties) {
        this.properties = properties;
    }

    @Override
    public TicTacToeConfiguration read(GameIO gameIO) {
        TicTacToeConfiguration configuration;
        if (properties == null) {
            gameIO.showErrorMessage("Properties is not set will use defaults" +
                    " "+PropertyKeys.PLAYGROUND_SIZE+": "+DEFAULT_PLAYGROUND_SIZE+
                    " "+PropertyKeys.PLAYER_A_SYMBOL+": "+DEFAULT_PLAYER_A_SYMBOL+
                    " "+PropertyKeys.PLAYER_B_SYMBOL+": "+DEFAULT_PLAYER_B_SYMBOL+
                    " "+PropertyKeys.PLAYER_COMPUTER_SYMBOL+": "+DEFAULT_PLAYER_COMPUTER_SYMBOL);

            ArrayList<PlayerConfiguration> playerConfigurations = createDefaultPlayer();
            configuration = new TicTacToeConfiguration(DEFAULT_PLAYGROUND_SIZE, playerConfigurations);
        } else {
            int size = readPlaygroundSize(gameIO);
            List<PlayerConfiguration> playerConfigurations = new ArrayList<>();
            playerConfigurations.add(
                    getPlayerConfiguration(gameIO,
                            PlayerConfiguration.HUMAN,
                            PropertyKeys.PLAYER_A_SYMBOL,
                            DEFAULT_PLAYER_A_SYMBOL));
            playerConfigurations.add(
                    getPlayerConfiguration(gameIO,
                            PlayerConfiguration.HUMAN,
                            PropertyKeys.PLAYER_B_SYMBOL,
                            DEFAULT_PLAYER_B_SYMBOL));
            playerConfigurations.add(
                    getPlayerConfiguration(gameIO,
                            PlayerConfiguration.COMPUTER,
                            PropertyKeys.PLAYER_COMPUTER_SYMBOL,
                            DEFAULT_PLAYER_COMPUTER_SYMBOL));
            setDefaultPlayerSymbolsIfEqual(gameIO, playerConfigurations);
            configuration = new TicTacToeConfiguration(size, playerConfigurations);
        }
        return configuration;
    }

    private ArrayList<PlayerConfiguration> createDefaultPlayer() {
        ArrayList<PlayerConfiguration> playerConfigurations = new ArrayList<>();
        playerConfigurations.add(
                new PlayerConfiguration(PlayerConfiguration.HUMAN, DEFAULT_PLAYER_A_SYMBOL));
        playerConfigurations.add(
                new PlayerConfiguration(PlayerConfiguration.HUMAN, DEFAULT_PLAYER_B_SYMBOL));
        playerConfigurations.add(
                new PlayerConfiguration(PlayerConfiguration.COMPUTER, DEFAULT_PLAYER_COMPUTER_SYMBOL));
        return playerConfigurations;
    }

    private void setDefaultPlayerSymbolsIfEqual(GameIO gameIO, List<PlayerConfiguration> playerConfigurations) {
        for (PlayerConfiguration pConfig :
                playerConfigurations) {
            for (PlayerConfiguration pConfigTest :
                    playerConfigurations) {
                if (pConfig != pConfigTest && pConfig.getPlayerSymbol() == pConfigTest.getPlayerSymbol()){
                    playerConfigurations.removeAll(playerConfigurations);
                    playerConfigurations.addAll(createDefaultPlayer());
                    gameIO.showErrorMessage("Players got the same symbol will use default symbols"+
                            " "+PropertyKeys.PLAYER_A_SYMBOL+": "+DEFAULT_PLAYER_A_SYMBOL+
                            " "+PropertyKeys.PLAYER_B_SYMBOL+": "+DEFAULT_PLAYER_B_SYMBOL+
                            " "+PropertyKeys.PLAYER_COMPUTER_SYMBOL+": "+DEFAULT_PLAYER_COMPUTER_SYMBOL);
                    return;
                }
            }
        }
    }


    PlayerConfiguration getPlayerConfiguration(GameIO gameIO, String playerType, String symbol, char defaultSymbol) {
        String property = properties.getProperty(symbol);
        if (property != null && !property.isEmpty() && property.length() == 1) {
            return new PlayerConfiguration(playerType, property.charAt(0));
        }
        gameIO.showErrorMessage(symbol+" is not a single character or missing using default value "+defaultSymbol);
        return new PlayerConfiguration(playerType, defaultSymbol);
    }

    int readPlaygroundSize(GameIO gameIO) {
        int size;
        try {
            size = Integer.parseInt(properties.getProperty(PropertyKeys.PLAYGROUND_SIZE));
        } catch (Exception e) {
            gameIO.showErrorMessage(PropertyKeys.PLAYGROUND_SIZE + " is not a number or missing using default size "
                    + DEFAULT_PLAYGROUND_SIZE);
            size = DEFAULT_PLAYGROUND_SIZE;
        }
        return size;
    }
}
