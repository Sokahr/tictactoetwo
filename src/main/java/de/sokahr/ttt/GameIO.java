package de.sokahr.ttt;

public interface GameIO {
    void showErrorMessage(String message);

    void drawGame(char[][] fields);

    String getInput();
}
