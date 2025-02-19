import java.util.Scanner;
import java.util.Random;

public class  arrayD8{
    public static void main(String[] args) {
        Random randomNum = new Random();
        Scanner num = new Scanner(System.in);
        int fil = num.nextInt();
        int col = num.nextInt();
        int fil2 = num.nextInt();
        int col2 = num.nextInt();
        int fil3 = num.nextInt();
        int col3 = num.nextInt();
        int fil4 = num.nextInt();
        int col4 = num.nextInt();
        int [][][][][][][][] matriu = new int[fil][col][fil2][col2][fil3][col3][fil4][col4];
        System.out.println("-------------------------------------------");
        for(int i = 0; i < matriu.length; i++) {
            for(int j = 0; j < matriu[i].length; j++) {
                for(int k = 0; k < matriu[i][j].length; k++) {
                    for(int l = 0; l < matriu[i][j][k].length; l++) {
                        for(int m = 0; m < matriu[i][j][k][l].length; m++) {
                            for(int n = 0; n < matriu[i][j][k][l][m].length; n++) {
                                for(int o = 0; o < matriu[i][j][k][l][m][n].length; o++) {
                                    for(int p = 0; p < matriu[i][j][k][l][m][n][o].length; p++) {
                                        //Assign random num to all matrix cells
                                        matriu[i][j][k][l][m][n][o][p] = randomNum.nextInt(10);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
        int i = 0;
        int m = 0;
        for(int j = 0; j < matriu[i].length; j++) {
            for(int k = 0; k < matriu[i][j].length; k++) {
                for(i = 0; i < matriu.length; i++) {
                    for(int l = 0; l < matriu[i][j][k].length; l++) {
                        for(int n = 0; n < matriu[i][j][k][l][m].length; n++) {
                            for(int o = 0; o < matriu[i][j][k][l][m][n].length; o++) {
                                for(m = 0; m < matriu[i][j][k][l].length; m++) {
                                    for(int p = 0; p < matriu[i][j][k][l][m][n][o].length; p++) {
                                        //Print
                                        System.out.print(matriu[i][j][k][l][m][n][o][p]+ " ");
                                    }System.out.print("| ");
                                }System.out.println();
                                m = 0;
                            }System.out.println();
                        }System.out.print("I ");
                    }System.out.print("I|I ");
                }System.out.println();
                i = 0;
            }System.out.println();
        }
    }
}