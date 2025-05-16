package tiktaktoe;
public class  global {
    public static char [][] game = new char[3][3];
    // Getter
  	public char [][] getGame() {
    	return game;
  	}
 	// Setter
  	public void setGame(char [][] newGame) {
    	this.game = newGame;
    }
    public void displayGame() {
        for (int g = 0; g < 3; g++){
            for (int g2 = 0; g2 < 3; g2++){
                System.out.print(" " + game[g][g2] + " ");
            }System.out.println();
        }
    }
    public static char [][][][] matrix = new char[3][3][3][3];
    // Getter
  	public char [][][][] getMatrix() {
    	return matrix;
  	}
 	// Setter
  	public void setMatrix(char [][][][] newMatrix) {
    	this.matrix = newMatrix;
    }

    public static int player;
    // Getter
  	public int getPlayer() {
    	return player;
  	}
 	// Setter
  	public void setPlayer(int newPlayer) {
    	this.player = newPlayer;
    }

    public static boolean draw = false;
    // Getter
  	public boolean getDraw() {
    	return draw;
  	}
 	// Setter
  	public void setDraw(boolean newDraw) {
    	this.draw = newDraw;
    }

    public static boolean notfinished = true;
    // Getter
  	public boolean getNotfinished() {
    	return notfinished;
  	}
 	// Setter
  	public void setNotfinished(boolean newNotfinished) {
    	this.notfinished = newNotfinished;
    }
}