package de.sokahr.ttt.player;

import de.sokahr.ttt.PlayerConfiguration;

public class PlayerFactory {

    public static Player getPlayer(PlayerConfiguration playerConfig) {
        switch (playerConfig.getPlayerType()) {
            case PlayerConfiguration.HUMAN:
                return new HumanPlayer(playerConfig.getPlayerSymbol());

            case PlayerConfiguration.COMPUTER:
                return new ComputerPlayer(playerConfig.getPlayerSymbol());
        }
        return null;
    }
}
