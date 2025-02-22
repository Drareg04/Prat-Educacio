package tiktaktoe;
import java.util.Scanner;
import java.util.Random;

public class  tictactoe {
    public String white = "\u001B[37m";
    public String red = "\u001B[31m";
    public String blue = "\u001B[34m";
    public String green = "\u001B[32m";
    public String clear = "\033[H\033[2J";
    private global global = new global();
    private place place = new place();
    private display display = new display();
    private check check = new check();
    public void start3(){
        char p;
        tictactoe tictactoe = new tictactoe();
        Scanner num = new Scanner(System.in);
        Random randomNum = new Random();
        int choice = 0;
        char [][] game = global.getGame();
        for(int i = 0; i < game.length; i++) {
            for(int j = 0; j < game[i].length; j++) {
                game[i][j] = '1';
            }
        }
        global.setGame(game);
        char [][][][] matrix = global.getMatrix();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                for(int k = 0; k < matrix[i][j].length; k++) {
                    for(int l = 0; l < matrix[i][j][k].length; l++) {
                        //Assign space to all cells
                        matrix[i][j][k][l] = ' ';
                    }
                }
            }
        }
        global.setMatrix(matrix);
        int choice2, chek;
        System.out.println(clear);
        System.out.println("\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░\u001B[36m██╗\u001B[91m░░░░░░░\u001B[36m██╗███████╗██╗\u001B[91m░░░░░░\u001B[36m█████╗\u001B[91m░░\u001B[36m█████╗\u001B[91m░\u001B[36m███╗\u001B[91m░░░\u001B[36m███╗███████╗\u001B[91m░░░░░░");
        System.out.println("░░\u001B[36m██║\u001B[91m░░\u001B[36m██╗\u001B[91m░░\u001B[36m██║██╔════╝██║\u001B[91m░░░░░\u001B[36m██╔══██╗██╔══██╗████╗\u001B[91m░\u001B[36m████║██╔════╝\u001B[91m░░░░░░");
        System.out.println("░░\u001B[36m╚██╗████╗██╔╝█████╗\u001B[91m░░\u001B[36m██║\u001B[91m░░░░░\u001B[36m██║\u001B[91m░░\u001B[36m╚═╝██║\u001B[91m░░\u001B[36m██║██╔████╔██║█████╗\u001B[91m░░░░░░░░");
        System.out.println("░░░\u001B[36m████╔═████║\u001B[91m░\u001B[36m██╔══╝\u001B[91m░░\u001B[36m██║\u001B[91m░░░░░\u001B[36m██║\u001B[91m░░\u001B[36m██╗██║\u001B[91m░░\u001B[36m██║██║╚██╔╝██║██╔══╝\u001B[91m░░░░░░░░");
        System.out.println("░░░\u001B[36m╚██╔╝\u001B[91m░\u001B[36m╚██╔╝\u001B[91m░\u001B[36m███████╗███████╗╚█████╔╝╚█████╔╝██║\u001B[91m░\u001B[36m╚═╝\u001B[91m░\u001B[36m██║███████╗\u001B[91m░░░░░░");
        System.out.println("░░░░\u001B[36m╚═╝\u001B[91m░░░\u001B[36m╚═╝\u001B[91m░░\u001B[36m╚══════╝╚══════╝\u001B[91m░\u001B[36m╚════╝\u001B[91m░░\u001B[36m╚════╝\u001B[91m░\u001B[36m╚═╝\u001B[91m░░░░░\u001B[36m╚═╝╚══════╝\u001B[91m░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░\u001B[36m████████╗\u001B[91m░\u001B[36m█████╗\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░\u001B[36m╚══██╔══╝██╔══██╗\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║\u001B[91m░░\u001B[36m██║\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║\u001B[91m░░\u001B[36m██║\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░\u001B[36m██║\u001B[91m░░░\u001B[36m╚█████╔╝\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░\u001B[36m╚═╝\u001B[91m░░░░\u001B[36m╚════╝\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░\u001B[36m██████╗██╗\u001B[91m░░░\u001B[36m██╗██████╗\u001B[91m░\u001B[36m███████╗██████╗\u001B[91m░░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░\u001B[36m██╔════╝██║\u001B[91m░░░\u001B[36m██║██╔══██╗██╔════╝██╔══██╗\u001B[91m░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░\u001B[36m╚█████╗\u001B[91m░\u001B[36m██║\u001B[91m░░░\u001B[36m██║██████╔╝█████╗\u001B[91m░░\u001B[36m██████╔╝\u001B[91m░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░░\u001B[36m╚═══██╗██║\u001B[91m░░░\u001B[36m██║██╔═══╝\u001B[91m░\u001B[36m██╔══╝\u001B[91m░░\u001B[36m██╔══██╗\u001B[91m░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░\u001B[36m██████╔╝╚██████╔╝██║\u001B[91m░░░░░\u001B[36m███████╗██║\u001B[91m░░\u001B[36m██║\u001B[91m░░░░░░░░░░░░░░░░");
        System.out.println("░░░░░░░░░░░░░░░\u001B[36m╚═════╝\u001B[91m░░\u001B[36m╚═════╝\u001B[91m░\u001B[36m╚═╝\u001B[91m░░░░░\u001B[36m╚══════╝╚═╝\u001B[91m░░\u001B[36m╚═╝\u001B[91m░░░░░░░░░░░░░░░░");
        System.out.println("░\u001B[36m████████╗██╗\u001B[91m░\u001B[36m█████╗\u001B[91m░\u001B[36m████████╗\u001B[91m░\u001B[36m█████╗\u001B[91m░░\u001B[36m█████╗\u001B[91m░\u001B[36m████████╗\u001B[91m░\u001B[36m█████╗\u001B[91m░\u001B[36m███████╗\u001B[91m░");
        System.out.println("░\u001B[36m╚══██╔══╝██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔════╝\u001B[91m░");
        System.out.println("░░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║██║\u001B[91m░░\u001B[36m╚═╝\u001B[91m░░░\u001B[36m██║\u001B[91m░░░\u001B[36m███████║██║\u001B[91m░░\u001B[36m╚═╝\u001B[91m░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║\u001B[91m░░\u001B[36m██║█████╗\u001B[91m░░░");
        System.out.println("░░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║██║\u001B[91m░░\u001B[36m██╗\u001B[91m░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██╔══██║██║\u001B[91m░░\u001B[36m██╗\u001B[91m░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║\u001B[91m░░\u001B[36m██║██╔══╝\u001B[91m░░░");
        System.out.println("░░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║╚█████╔╝\u001B[91m░░░\u001B[36m██║\u001B[91m░░░\u001B[36m██║\u001B[91m░░\u001B[36m██║╚█████╔╝\u001B[91m░░░\u001B[36m██║\u001B[91m░░░\u001B[36m╚█████╔╝███████╗\u001B[91m░");
        System.out.println("░░░░\u001B[36m╚═╝\u001B[91m░░░\u001B[36m╚═╝\u001B[91m░\u001B[36m╚════╝\u001B[91m░░░░\u001B[36m╚═╝\u001B[91m░░░\u001B[36m╚═╝\u001B[91m░░\u001B[36m╚═╝\u001B[91m░\u001B[36m╚════╝\u001B[91m░░░░\u001B[36m╚═╝\u001B[91m░░░░\u001B[36m╚════╝\u001B[91m░\u001B[36m╚══════╝\u001B[91m░");
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" + white);
        System.out.println("Who begins (X, O, R):");
        do{
            p = tictactoe.LLegirPTeclat(num);
            }while(p == ' ');
        if (p == 'r' || p == 'R')
            global.setPlayer(randomNum.nextInt(2));
        else if (p == 'x' || p == 'X')
            global.setPlayer(0);
        else
            global.setPlayer(1);
        while(global.getNotfinished() && !global.getDraw()){
            display.displayall();
            if(choice == 0){
                //Marca todos los espacios libres como jugables (verde)
                game = global.getGame();
                for (int g = 0; g < 3; g++){
                    for (int g2 = 0; g2 < 3; g2++){
                        if (game[g][g2] == ' ')
                            game[g][g2] = '1';
                    }
                }
                global.setGame(game);
                display.displayall();
                do{
                    choice = tictactoe.LLegirEnterTeclat(num);
                    chek = check.checkchoice(choice);
                }while(chek == 0);
                display.displayall();
            }
            display.displaytiny(choice);
            do{
                choice2 = tictactoe.LLegirEnterTeclat(num);
                chek = place.placesign(choice, choice2);
            }while(chek == 0);
            int win = check.checkwin(choice);
            if (win == 1){
                place.placewin(choice);
                check.checkbigwin();
            }else if (win == 2) {
                place.placedraw(choice);
                check.checkbigwin();
            }
            choice = check.checkchoice(choice2);
            if (global.getNotfinished()){
                if (global.getPlayer() == 0){
                    global.setPlayer(1);
                }else{
                    global.setPlayer(0);
                }
            }
        }
        String win;
        if(global.getDraw()){
            System.out.println(clear);
            System.out.println("\u001B[30m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
            System.out.println("░\u001B[35m██████╗\u001B[30m░\u001B[35m██████╗\u001B[30m░░\u001B[35m█████╗\u001B[30m░░\u001B[35m██╗\u001B[30m░░░░░░░\u001B[35m██╗\u001B[30m░");
            System.out.println("░\u001B[35m██╔══██╗██╔══██╗██╔══██╗\u001B[30m░\u001B[35m██║\u001B[30m░░\u001B[35m██╗\u001B[30m░░\u001B[35m██║\u001B[30m░");
            System.out.println("░\u001B[35m██║\u001B[30m░░\u001B[35m██║██████╔╝███████║\u001B[30m░\u001B[35m╚██╗████╗██╔╝\u001B[30m░");
            System.out.println("░\u001B[35m██║\u001B[30m░░\u001B[35m██║██╔══██╗██╔══██║\u001B[30m░░\u001B[35m████╔═████║\u001B[30mv░░");
            System.out.println("░\u001B[35m██████╔╝██║\u001B[30m░░\u001B[35m██║██║\u001B[30m░░\u001B[35m██║\u001B[30m░░\u001B[35m╚██╔╝\u001B[30m░\u001B[35m╚██╔╝\u001B[30m░░");
            System.out.println("░\u001B[35m╚═════╝\u001B[30m░\u001B[35m╚═╝\u001B[30m░░\u001B[35m╚═╝╚═╝\u001B[30m░░\u001B[35m╚═╝\u001B[30m░░░\u001B[35m╚═╝\u001B[30m╚═╝\u001B[35m╚═╝\u001B[30m░░░");
            System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"+white);
        }
        else if(global.getPlayer() == 0){
            System.out.println(clear);
            System.out.println("\u001B[36m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
            System.out.println("░░\u001B[91m██╗\u001B[36m░░\u001B[91m██╗██╗\u001B[36m░\u001B[91m██████╗\u001B[36m░░░░░░░░░░░░░░░░░\u001B[91m██╗\u001B[36m░░░░░░░\u001B[91m██╗██╗███╗\u001B[36m░░\u001B[91m██╗\u001B[36m░\u001B[91m██████╗\u001B[36m░░");
            System.out.println("░░\u001B[91m╚██╗██╔╝╚█║██╔════╝\u001B[36m░░░░░░░░░░░░░░░░░\u001B[91m██║\u001B[36m░░\u001B[91m██╗\u001B[36m░░\u001B[91m██║██║████╗\u001B[36m░\u001B[91m██║██╔════╝\u001B[36m░░");
            System.out.println("░░░\u001B[91m╚███╔╝\u001B[36m░░\u001B[91m╚╝╚█████╗\u001B[36m░░░░░░░░░░░░░░░░░░\u001B[91m╚██╗████╗██╔╝██║██╔██╗██║╚█████╗\u001B[36m░░░");
            System.out.println("░░░\u001B[91m██╔██╗\u001B[36m░░░░░\u001B[91m╚═══██╗\u001B[36m░░░░░░░░░░░░░░░░░░\u001B[91m████╔═████║\u001B[36m░\u001B[91m██║██║╚████║░\u001B[91m╚═══██╗\u001B[36m░░");
            System.out.println("░░\u001B[91m██╔╝╚██╗\u001B[36m░░░\u001B[91m██████╔╝\u001B[36m░░░░░░░░░░░░░░░░░░\u001B[91m╚██╔╝\u001B[36m░\u001B[91m╚██╔╝\u001B[36m░\u001B[91m██║██║\u001B[36m░\u001B[91m╚███║██████╔╝\u001B[36m░░");
            System.out.println("░░\u001B[91m╚═╝\u001B[36m░░\u001B[91m╚═╝\u001B[36m░░░\u001B[91m╚═════╝\u001B[36m░░░░░░░░░░░░░░░░░░░░\u001B[91m╚═╝\u001B[36m░░░\u001B[91m╚═╝\u001B[36m░░\u001B[91m╚═╝╚═╝\u001B[36m░░\u001B[91m╚══╝╚═════╝\u001B[36m░░░");
            System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"+white);
            }
        else{
            System.out.println(clear);
            System.out.println("\u001B[91m░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
            System.out.println("░░░\u001B[36m█████╗\u001B[91m░\u001B[36m██╗\u001B[91m░\u001B[36m██████╗\u001B[91m░░░░░░░░░░░░░░░░░\u001B[36m██╗\u001B[91m░░░░░░░\u001B[36m██╗██╗███╗\u001B[91m░░\u001B[36m██╗\u001B[91m░\u001B[36m██████╗\u001B[91m░░");
            System.out.println("░░\u001B[36m██╔══██╗╚█║██╔════╝\u001B[91m░░░░░░░░░░░░░░░░░\u001B[36m██║\u001B[91m░░\u001B[36m██╗\u001B[91m░░\u001B[36m██║██║████╗\u001B[91m░\u001B[36m██║██╔════╝\u001B[91m░░");
            System.out.println("░░\u001B[36m██║\u001B[91m░░\u001B[36m██║\u001B[91m░\u001B[36m╚╝╚█████╗\u001B[91m░░░░░░░░░░░░░░░░░░\u001B[36m╚██╗████╗██╔╝██║██╔██╗██║╚█████╗\u001B[91m░░░");
            System.out.println("░░\u001B[36m██║\u001B[91m░░\u001B[36m██║\u001B[91m░░░░\u001B[36m╚═══██╗\u001B[91m░░░░░░░░░░░░░░░░░░\u001B[36m████╔═████║\u001B[91m░\u001B[36m██║██║╚████║\u001B[91m░\u001B[36m╚═══██╗\u001B[91m░░");
            System.out.println("░░\u001B[36m╚█████╔╝\u001B[91m░░░\u001B[36m██████╔╝\u001B[91m░░░░░░░░░░░░░░░░░░\u001B[36m╚██╔╝\u001B[91m░\u001B[36m╚██╔╝\u001B[91m░\u001B[36m██║██║\u001B[91m░\u001B[36m╚███║██████╔╝\u001B[91m░░");
            System.out.println("░░░\u001B[36m╚════╝\u001B[91m░░░░\u001B[36m╚═════╝\u001B[91m░░░░░░░░░░░░░░░░░░░░\u001B[36m╚═╝\u001B[91m░░░\u001B[36m╚═╝\u001B[91m░░\u001B[36m╚═╝╚═╝\u001B[91m░░\u001B[36m╚══╝╚═════╝\u001B[91m░░░");
            System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"+white);
        }
    }
    public static int LLegirEnterTeclat(Scanner scan){
        int Llegit;

        while (!scan.hasNextInt()) {
            scan.next();
        }
        
        Llegit = scan.nextInt();
        return Llegit;
    }
    public static char LLegirPTeclat(Scanner scan){
        char llegit;

        if (scan.hasNext()) {
            String token = scan.next();
            if (token.equals("x") || token.equals("X") || token.equals("o") || token.equals("O") || token.equals("r") || token.equals("R")) {
                llegit = token.charAt(0);
                return llegit;
            }
        }
        return ' ';
    }
}