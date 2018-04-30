package de.sokahr.ttt.player;

import de.sokahr.ttt.GameIO;

import java.awt.*;

public class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Point makeMove(GameIO gameIO, char[][] fields) {
        String input = gameIO.getInput();
        String[] strings = input.split(",");
        Point result;
        try {
            int x = Integer.parseInt(strings[0])-1;
            int y = Integer.parseInt(strings[1])-1;
            result = new Point(x, y);
            if(x<fields.length && y<fields[x].length) {
                if (fields[x][y] != '\0') {
                    gameIO.showErrorMessage("field is already occupied, please choose a different one");
                    result = makeMove(gameIO, fields);
                }
            } else {
                gameIO.showErrorMessage("field is not in reach, please choose a different one");
                result = makeMove(gameIO,fields);
            }
        } catch (NumberFormatException e) {
            gameIO.showErrorMessage("wrong input format please enter [column],[row] i.E. 2,3");
            result = makeMove(gameIO, fields);
        }
        return result;
    }
}
