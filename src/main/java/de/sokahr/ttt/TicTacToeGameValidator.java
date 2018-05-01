package de.sokahr.ttt;

public class TicTacToeGameValidator {
    public static boolean validateMovesPossible(char[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j]=='\0'){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validateWin(char symbol, char[][] fields) {
        return validateHorizontalWin(symbol, fields)
                || validateVerticalWin(symbol, fields)
                || validateDiagonal(symbol, fields);
    }

    private static boolean validateDiagonal(char symbol, char[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j]==symbol){
                    if(i+2<fields.length && j+2<fields[i+2].length){
                        if(fields[i+1][j+1]==symbol && fields[i+2][j+2]==symbol){
                            return true;
                        }
                    }
                    if(i+2<fields.length && j-2>=0){
                        if(fields[i+1][j-1]==symbol && fields[i+2][j-2]==symbol){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean validateVerticalWin(char symbol, char[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                int hitcount = 0;
                for (int k = 0; k < fields.length; k++) {
                    if(fields[k][j]==symbol){
                        hitcount++;
                    }else{
                        hitcount=0;
                    }
                    if(hitcount==3){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean validateHorizontalWin(char symbol, char[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            int hitCount=0;
            for (int j = 0; j < fields[i].length; j++) {
                if(fields[i][j] == symbol) {
                    hitCount++;
                } else {
                    hitCount=0;
                }
                if (hitCount==3){
                    return true;
                }
            }
        }
        return false;
    }
}
