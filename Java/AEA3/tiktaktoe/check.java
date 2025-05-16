package tiktaktoe;
public class  check {
    private global global = new global();
	public int checkwin(int choice) {
        char [][][][] matrix = global.getMatrix();
        int i, j;
        char sign;

        if(global.getPlayer() == 0)
                sign = 'X';
            else
                sign = 'O';
        if (choice <= 3)
            i = 0;
        else if (choice <= 6)
            i = 1;
        else
            i = 2;
        j = choice - (i*3) - 1;
        if (matrix[i][j][0][0] == sign && matrix[i][j][0][1] == sign && matrix[i][j][0][2] == sign)
            return (1);
        if (matrix[i][j][1][0] == sign && matrix[i][j][1][1] == sign && matrix[i][j][1][2] == sign)
            return (1);
        if (matrix[i][j][2][0] == sign && matrix[i][j][2][1] == sign && matrix[i][j][2][2] == sign)
            return (1);
        if (matrix[i][j][0][0] == sign && matrix[i][j][1][0] == sign && matrix[i][j][2][0] == sign)
            return (1);
        if (matrix[i][j][0][1] == sign && matrix[i][j][1][1] == sign && matrix[i][j][2][1] == sign)
            return (1);
        if (matrix[i][j][0][2] == sign && matrix[i][j][1][2] == sign && matrix[i][j][2][2] == sign)
            return (1);
        if (matrix[i][j][0][0] == sign && matrix[i][j][1][1] == sign && matrix[i][j][2][2] == sign)
            return (1);
        if (matrix[i][j][0][2] == sign && matrix[i][j][1][1] == sign && matrix[i][j][2][0] == sign)
            return (1);
        for(int k = 0; k < matrix[i][j].length; k++) {
            for(int l = 0; l < matrix[i][j][k].length; l++) {
                if (matrix[i][j][k][l] == ' ')
                    return (0);
            }
        }
        return (2);
    }
    public int checkchoice(int choice) {
        char [][] game = global.getGame();
        int i, j;

        if (choice <= 3)
            i = 0;
        else if (choice <= 6)
            i = 1;
        else if (choice <= 9)
            i = 2;
        else{
            i = 0;
            return (0);
        }
        j = choice - (i*3) - 1;
        if (game[i][j] != ' ' && game[i][j] != '1')
            return (0);
        for (int g = 0; g < 3; g++){
            for (int g2 = 0; g2 < 3; g2++){
                if (game[g][g2] == '1'){
                    game[g][g2] = ' ';
                }
            }
        }
        game[i][j] = '1';
        global.setGame(game);
        return (choice);
    }
    public boolean checkbigwin() {
        char [][] game = global.getGame();
        char sign;

        if(global.getPlayer() == 0)
            sign = 'X';
        else
            sign = 'O';
        if (game[0][0] == sign && game[0][1] == sign && game[0][2] == sign)
            global.setNotfinished(false);
        if (game[1][0] == sign && game[1][1] == sign && game[1][2] == sign)
            global.setNotfinished(false);
        if (game[2][0] == sign && game[2][1] == sign && game[2][2] == sign)
            global.setNotfinished(false);
        if (game[0][0] == sign && game[1][0] == sign && game[2][0] == sign)
            global.setNotfinished(false);
        if (game[0][1] == sign && game[1][1] == sign && game[2][1] == sign)
            global.setNotfinished(false);
        if (game[0][2] == sign && game[1][2] == sign && game[2][2] == sign)
            global.setNotfinished(false);
        if (game[0][0] == sign && game[1][1] == sign && game[2][2] == sign)
            global.setNotfinished(false);
        if (game[0][2] == sign && game[1][1] == sign && game[2][0] == sign)
            global.setNotfinished(false);
        for(int i = 0; i < game.length; i++) {
            for(int j = 0; j < game[i].length; j++) {
                if (game[i][j] == ' ' || game[i][j] == '1')
                    return (false);
            }
        }
        global.setDraw(true);
        return (true);
    }
}