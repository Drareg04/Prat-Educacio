package tiktaktoe;
public class  global {
    public char [][] game = new char[3][3];
    // Getter
  	public char [][] getGame() {
    	return game;
  	}
 	// Setter
  	public void setGame(char [][] newGame) {
    	this.game = newGame;
    }

    public char [][][][] matrix = new char[3][3][3][3];
    // Getter
  	public char [][][][] getMatrix() {
    	return matrix;
  	}
 	// Setter
  	public void setMatrix(char [][][][] newMatrix) {
    	this.matrix = newMatrix;
    }

    public int player;
    // Getter
  	public int getPlayer() {
    	return player;
  	}
 	// Setter
  	public void setPlayer(int newPlayer) {
    	this.player = newPlayer;
    }

    public boolean draw = false;
    // Getter
  	public boolean getDraw() {
    	return draw;
  	}
 	// Setter
  	public void setDraw(boolean newDraw) {
    	this.draw = newDraw;
    }

    public boolean notfinished = true;
    // Getter
  	public boolean getNotfinished() {
    	return notfinished;
  	}
 	// Setter
  	public void setNotfinished(boolean newNotfinished) {
    	this.notfinished = newNotfinished;
    }
}