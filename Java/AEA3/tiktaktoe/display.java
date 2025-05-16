package tiktaktoe;
public class  display {
    public String white = "\u001B[37m";
    public String red = "\u001B[31m";
    public String blue = "\u001B[34m";
    public String green = "\u001B[32m";
    public String clear = "\033[H\033[2J";
    private global global = new global();
    public void displayall() {
        System.out.print(clear);
        if (global.getPlayer() == 0){
            System.out.println(red + "X" + white + "'s turn\n");
        }else{
            System.out.println(blue + "O" + white + "'s turn\n");
        }
        char [][] game = global.getGame();
        char [][][][] matrix = global.getMatrix();
        System.out.println(white + "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        int j = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int k = 0; k < matrix[i][j].length; k++) {
                for(j = 0; j < matrix[i].length; j++) {
                    for(int l = 0; l < matrix[i][j][k].length; l++) {
                        //Print
                        if (j == 0 && l == 0){
                            System.out.print("N");
                        }if (matrix[i][j][k][l] == 'X')
                            System.out.print(" " + red + matrix[i][j][k][l] + white + " ");
                        else if(matrix[i][j][k][l] == 'O')
                            System.out.print(" " + blue + matrix[i][j][k][l] + white + " ");
                        else
                            System.out.print(" " + matrix[i][j][k][l] + " ");
                        if (l != 2){
                            if (game[i][j] == '1')
                                System.out.print(green + " |" + white);
                            else
                                System.out.print(" |");
                        }
                    }System.out.print(" N");
                }if (k != 2){
                    System.out.println();
                    for (int n = 0; n < 3; n++) {
                        if (game[i][n] == '1')
                            System.out.print("N" + green + " ------------ " + white);
                        else
                            System.out.print("N ------------ ");
                    }
                    System.out.println("N");
                }else{
                    System.out.println();
                }
                j = 0;
            }System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        }
    }
    public void displaytiny(int choice) {
        int i, j;

        if (choice <= 3)
            i = 0;
        else if (choice <= 6)
            i = 1;
        else
            i = 2;
        j = choice - (i*3) - 1;
        char [][][][] matrix = global.getMatrix();
        System.out.println("\n\nZZZZZZZZZZZZZZZZ");
        for(int k = 0; k < matrix[i][j].length; k++) {
            for(int l = 0; l < matrix[i][j][k].length; l++) {
                //Print
                if (l == 0){
                    System.out.print("N");
                }if (matrix[i][j][k][l] == 'X')
                    System.out.print(" " + red + matrix[i][j][k][l] + white + " ");
                else if(matrix[i][j][k][l] == 'O')
                    System.out.print(" " + blue + matrix[i][j][k][l] + white + " ");
                else
                    System.out.print(" " + matrix[i][j][k][l] + " ");
                if (l != 2){
                    System.out.print(green + " |" + white);
                }
            }if (k != 2){
                    System.out.println(" N\nN" + green + "--------------" + white + "N");
            }
        }System.out.println(" N\nZZZZZZZZZZZZZZZZ");
    }
}