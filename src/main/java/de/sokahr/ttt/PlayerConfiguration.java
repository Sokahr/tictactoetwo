package de.sokahr.ttt;

public class PlayerConfiguration {
    public static final String COMPUTER = "computer";
    public static final String HUMAN = "human";
    private String playerType;
    private char playerSymbol;

    public PlayerConfiguration(String playerType, char playerSymbol) {
        this.playerType = playerType;
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerType() {
        return playerType;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

}
