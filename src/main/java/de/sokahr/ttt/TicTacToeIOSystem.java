package de.sokahr.ttt;

public class TicTacToeIOSystem implements GameIO {

    @Override
    public void showErrorMessage(String message) {
        System.err.println(message);
    }
}
