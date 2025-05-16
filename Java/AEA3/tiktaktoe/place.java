package tiktaktoe;
public class  place {
	private global global = new global();
	public int placesign(int choice, int choice2) {
		char [][][][] matrix = global.getMatrix();
        int i, j, k, l;
        
        if (choice < 1)
            return (0);
        else if (choice <= 3)
            i = 0;
        else if (choice <= 6)
            i = 1;
        else
            i = 2;
        j = choice - (i*3) - 1;
        if (choice2 < 1)
            return (0);
        else if (choice2 <= 3 )
            k = 0;
        else if (choice2 <= 6)
            k = 1;
        else if (choice2 <= 9)
            k = 2;
        else{
            i = 0;
            return (0);
        }
        l = choice2 - (k*3) - 1;
        if(matrix[i][j][k][l] == ' '){
            if(global.getPlayer() == 0)
                matrix[i][j][k][l] = 'X';
            else
                matrix[i][j][k][l] = 'O';
            global.setMatrix(matrix);
            return (1);
        }
        return (0);
    }
    public void placewin(int choice) {
    	char [][] game = global.getGame();
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
        if(global.getPlayer() == 0)
            game[i][j] = 'X';
        else
            game[i][j] = 'O';
        for(int k = 0; k < matrix[i][j].length; k++) {
            for(int l = 0; l < matrix[i][j][k].length; l++) {
                matrix[i][j][k][l] = sign;
            }

        }
        global.setMatrix(matrix);
        global.setGame(game);
    }
    public void placedraw(int choice) {
        char [][] game = global.getGame();
        char [][][][] matrix = global.getMatrix();
        int i, j;
        char sign;
        if (choice <= 3)
            i = 0;
        else if (choice <= 6)
            i = 1;
        else
            i = 2;
        j = choice - (i*3) - 1;
        game[i][j] = 'd';
        global.setGame(game);
    }
}