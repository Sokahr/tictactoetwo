package de.sokahr.ttt.player;

import de.sokahr.ttt.PlayerConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerFactoryTest {

    @Test
    @DisplayName("getPlayer returns a HumanPlayer for the PlayerType Human with the correct symbol")
    void testGetPlayerReturnsHumanPlayerForTypeHuman() {
        Player player = PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'H'));
        assertThat(player).isInstanceOf(HumanPlayer.class).hasFieldOrPropertyWithValue("symbol", 'H');
    }
    @Test
    @DisplayName("getPlayer returns a ComputerPlayer for the PlayerType Computer with the correct symbol")
    void testGetPlayerReturnsComputerPlayerForTypeComputer() {
        Player player = PlayerFactory.getPlayer(new PlayerConfiguration(PlayerConfiguration.COMPUTER, 'C'));
        assertThat(player).isInstanceOf(ComputerPlayer.class).hasFieldOrPropertyWithValue("symbol", 'C');
    }
}