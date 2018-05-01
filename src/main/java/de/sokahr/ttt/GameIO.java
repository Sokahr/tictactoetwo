package de.sokahr.ttt;

import java.io.IOException;

public interface GameIO {
    void showErrorMessage(String message);

    void drawGame(char[][] fields);

    String getInput() throws IOException;

    void showInfoMessage(String message);
}
