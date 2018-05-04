package de.sokahr.ttt;

import de.sokahr.ttt.player.ComputerPlayer;
import de.sokahr.ttt.player.HumanPlayer;
import de.sokahr.ttt.player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicTacToeTwoTest {

    private GameIO gameIO;
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream sysErr;

    @BeforeEach
    void setUp() {
        sysErr = System.err;
        System.setErr(new PrintStream(errContent));
        gameIO = mock(GameIO.class);
    }

    @AfterEach
    void tearDown() {
        System.setErr(sysErr);
    }

    @Test
    @DisplayName("Constructor will initialize a default IOSystem and shows errorMessage if gameIO was null")
    void testConstructorInitDefaultGameIOIfNull() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(null);
        assertThat(ticTacToeTwo.gameIO).isInstanceOf(TicTacToeIOSystem.class);
        assertThat(errContent.toString()).contains("IO System was not set using SystemIO as default");
    }

    @Test
    @DisplayName("Constructor initialize with the given GameIO instant")
    void testConstructorSetsTheGivenGameIO() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        assertThat(ticTacToeTwo.gameIO).isEqualTo(gameIO);
    }

    @Test
    @DisplayName("configureGame configures and initialize game")
    void testConfigureGameWillCreateAGameFieldAndAListOfPlayers() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ArrayList<PlayerConfiguration> players = new ArrayList<>();
        players.add(new PlayerConfiguration(PlayerConfiguration.HUMAN, 'H'));
        players.add(new PlayerConfiguration(PlayerConfiguration.COMPUTER, 'C'));
        ticTacToeTwo.configureGame(new TicTacToeConfiguration(3, players));
        assertAll(
                () -> assertThat(ticTacToeTwo.gameField).isNotNull(),
                () -> assertThat(ticTacToeTwo.players).isNotNull().hasSize(2)
        );
    }

    @Test
    @DisplayName("configureGame create a defaultGame if configuration is null")
    void testConfigureGameWillCreateADefaultGameIfConfigurationIsNull() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ticTacToeTwo.configureGame(null);
        assertAll(
                () -> assertThat(ticTacToeTwo.players).hasSize(3),
                () -> assertThat(ticTacToeTwo.gameField).isNotNull()
        );
        verify(gameIO).showErrorMessage(any());
    }

    @Test
    @DisplayName("useDefaults configures default game")
    void testUseDefaultsConfiguresDefaultGame() {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ticTacToeTwo.useDefaults();
        assertAll(
                () -> assertThat(ticTacToeTwo.gameField).isNotNull(),
                ()->assertThat(ticTacToeTwo.gameField.fields).hasSize(4),
                () -> assertThat(ticTacToeTwo.players)
                        .hasSize(3)
                        .hasOnlyElementsOfTypes(HumanPlayer.class, ComputerPlayer.class)
        );
    }

    @Test
    @DisplayName("play runs through a simple game")
    void testPlay() throws IOException {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ticTacToeTwo.gameField=spy(new TicTacToeGameField(3));
        ticTacToeTwo.players = new ArrayList<>();
        Player playerA = mock(Player.class);
        ticTacToeTwo.players.add(playerA);
        Player playerB = mock(Player.class);
        ticTacToeTwo.players.add(playerB);
        when(playerA.makeMove(any(), any())).thenReturn(new Point(0,0), new Point(0,1), new Point(0,2));
        when(playerA.getSymbol()).thenReturn('A');
        when(playerB.makeMove(any(),any())).thenReturn(new Point(1,0), new Point(1,1), new Point(1,2));
        when(playerB.getSymbol()).thenReturn('B');
        ticTacToeTwo.play();

        verify(gameIO, atLeast(5)).drawGame(any());
        verify(gameIO, atLeast(6)).showInfoMessage(anyString());
        verify(ticTacToeTwo.gameField).setMove(0, 0, 'A');
        verify(ticTacToeTwo.gameField).setMove(0,1, 'A');
        verify(ticTacToeTwo.gameField).setMove(1,0, 'B');
        verify(ticTacToeTwo.gameField).setMove(1,1,'B');
    }

    @Test
    void testPlayShowsErrorAndExitsIfPlayerMakeMoveThrowsIOException() throws IOException {

        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ticTacToeTwo.gameField=spy(new TicTacToeGameField(3));
        ticTacToeTwo.players = new ArrayList<>();
        Player playerA = mock(Player.class);
        ticTacToeTwo.players.add(playerA);
        when(playerA.makeMove(any(), any())).thenThrow(IOException.class);
        when(playerA.getSymbol()).thenReturn('A');
        ticTacToeTwo.play();
        verify(gameIO).showErrorMessage(any());
        verify(gameIO,times(1)).showInfoMessage(any());
    }

    @Test
    @DisplayName("turn shows an info message and returns true if the player makes a valid move")
    void testTurnReturnsTrueIfThePlayerMakesAMove() throws IOException {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        Player player = mock(Player.class);
        when(player.makeMove(any(),any())).thenReturn(new Point());
        boolean turn = ticTacToeTwo.turn(player);
        assertEquals(true, turn);
        verify(gameIO).showInfoMessage(anyString());
    }

    @Test
    @DisplayName("turn asks again if the move was invalid")
    void testTurnAsksAgainIfTheMoveWasInvalid() throws IOException {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ticTacToeTwo.gameField = mock(TicTacToeGameField.class);
        when(ticTacToeTwo.gameField.setMove(0,1,'A')).thenReturn(false);
        when(ticTacToeTwo.gameField.setMove(1,1,'A')).thenReturn(true);
        when(ticTacToeTwo.gameField.getFields()).thenReturn(new char[3][3]);
        Player player = mock(Player.class);
        when(player.getSymbol()).thenReturn('A');
        when(player.makeMove(any(), any())).thenReturn(new Point(0,1), new Point(1,1));

        ticTacToeTwo.turn(player);
        verify(gameIO, atLeast(2)).showInfoMessage(any());
    }

    @Test
    void testTurnShowsErrorAndReturnsFalseIfIOExceptionHappens() throws IOException {
        TicTacToeTwo ticTacToeTwo = new TicTacToeTwo(gameIO);
        ticTacToeTwo.gameField = mock(TicTacToeGameField.class);
        when(ticTacToeTwo.gameField.getFields()).thenReturn(new char[3][3]);
        Player player = mock(Player.class);
        when(player.makeMove(any(),any())).thenThrow(IOException.class);
        boolean turn = ticTacToeTwo.turn(player);

        verify(gameIO).showErrorMessage(any());
        assertEquals(false, turn);
    }
}