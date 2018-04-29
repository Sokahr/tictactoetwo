package de.sokahr.ttt.player;

import de.sokahr.ttt.GameIO;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComputerPlayer extends Player {
    public ComputerPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public Point makeMove(GameIO gameIO, char[][] fields) {
        List<Point> freePoints = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j] == '\0') {
                    freePoints.add(new Point(i,j));
                }
            }
        }
        Collections.shuffle(freePoints);
        return freePoints.get(0);
    }
}
