package de.sokahr.ttt;

public class TicTacToeIOSystem implements GameIO {

    @Override
    public void showErrorMessage(String message) {
        System.err.println(message);
    }

    @Override
    public void drawGame(char[][] fieldValues) {
        drawColumnNumbers(fieldValues.length);
        for (int i = 0; i < fieldValues.length; i++) {
            drawRowBorder(fieldValues.length);
            drawRowFields(fieldValues.length, i, fieldValues);
        }
        drawRowBorder(fieldValues.length);
        drawColumnNumbers(fieldValues.length);
    }

    private void drawRowFields(int columns, int rowNumber,char[][] fieldValues) {
        StringBuilder line = new StringBuilder();
        line.append(rowNumber+1);
        for (int i = 0; i < columns; i++) {
            char fieldValue = fieldValues[i][rowNumber];
            if(fieldValue == '\u0000'){
                fieldValue = ' ';
            }
            line.append("| ").append(fieldValue).append(" ");
        }
        line.append("|").append(rowNumber+1);
        System.out.println(line);
    }

    private void drawRowBorder(int columns) {
        StringBuilder line = new StringBuilder(" ");
        for (int i = 0; i < columns; i++) {
            line.append("|---");
        }
        line.append("| ");
        System.out.println(line);
    }

    private void drawColumnNumbers(int columns) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            line.append("   ").append(i + 1);
        }
        line.append("   ");
        System.out.println(line);
    }
}
