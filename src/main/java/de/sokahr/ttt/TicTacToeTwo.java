package de.sokahr.ttt;

import de.sokahr.ttt.player.Player;
import de.sokahr.ttt.player.PlayerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TicTacToeTwo {

    GameIO gameIO;
    TicTacToeGameField gameField;
    List<Player> players;

    public TicTacToeTwo(GameIO gameIO) {
        this.gameIO = gameIO;
        if (gameIO == null) {
            this.gameIO = new TicTacToeIOSystem();
            this.gameIO.showErrorMessage("IO System was not set using SystemIO as default");
        }
    }

    public void configureGame(TicTacToeConfiguration configuration) {
        if (configuration != null) {
            gameField = new TicTacToeGameField(configuration.getFieldSize());
            players = new ArrayList<>();
            for (Iterator<PlayerConfiguration> it = configuration.getPlayers(); it.hasNext(); ) {
                PlayerConfiguration playerConfig = it.next();
                players.add(PlayerFactory.getPlayer(playerConfig));
            }
        } else {
            gameIO.showErrorMessage("Game configuration not set configure now with defaults");
            useDefaults();
        }
    }

    void useDefaults() {
        gameField = new TicTacToeGameField(4);
        players = new ArrayList<>();
        players.add(PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'X')));
        players.add(PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'O')));
        players.add(PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.COMPUTER, '@')));
    }

    public void play() {
        if (gameField == null || players == null || players.isEmpty()) {
            gameIO.showErrorMessage("Game configuration not set configure now with defaults");
            useDefaults();
        }
        Collections.shuffle(players, new Random(System.currentTimeMillis()));

        Iterator<Player> playerIterator = players.iterator();

        while (TicTacToeGameValidator.validateMovesPossible(gameField.getFields())) {
            this.gameIO.drawGame(gameField.getFields());
            if (!playerIterator.hasNext()) {
                playerIterator = players.iterator();
            }
            Player currentPlayer = playerIterator.next();
            if (!turn(currentPlayer)) {
                return;
            }
            if (TicTacToeGameValidator.validateWin(currentPlayer.getSymbol(), gameField.getFields())) {
                gameIO.showInfoMessage("Congratulations the player " + currentPlayer.getSymbol() + " won the game");
                return;
            }
        }
        gameIO.showInfoMessage("Draw nobody won the game");
    }

    boolean turn(Player currentPlayer) {
        try {
            Point move;
            do {
                gameIO.showInfoMessage("Player " + currentPlayer.getSymbol() + " make your move");
                move = currentPlayer.makeMove(gameIO, gameField.getFields());
            } while (!gameField.setMove(move.x, move.y, currentPlayer.getSymbol()));
        } catch (IOException e) {
            gameIO.showErrorMessage("Player " + currentPlayer.getSymbol() + " has problems with input the game will " +
                    "be canceled");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
