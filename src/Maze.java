import java.util.Scanner;
import java.util.InputMismatchException;

public class Maze {
    public static void main(String[] args) {
        int rows = 0, columns = 0; char mazeL[][];char mazeC[][]; boolean check[][]; Scanner sc = new Scanner(System.in);

        // checking for integers
        while(true) {
            try {
                System.out.print("How many rows: ");
                rows = sc.nextInt();
                if((rows >= 0 && rows <= Integer.MAX_VALUE)) {
                    System.out.println("Set");
                    System.out.println(" ");
                    break;
                }
            }
            catch(InputMismatchException ex) {
                System.out.println("Error : invalid value");
                System.out.println(" ");
                sc.next();
            }           
        }
        while(true) {
            try {
                System.out.print("How many columns: ");
                columns = sc.nextInt();
                if((columns >= 0 && columns <= Integer.MAX_VALUE)) {
                    System.out.println("Set");
                    System.out.println(" ");
                    break;
                }
            }
            catch(InputMismatchException ex) {
                System.out.println("Error : invalid value");
                System.out.println(" ");
                sc.next();
            }           
        }
        System.out.println("Lines   : " + rows);
        System.out.println("Columns : " + columns);
        System.out.println(" ");
        sc.close();

        int startY = (int)(Math.floor((Math.random() * (rows - 0)) + 0));
        int startX = (int)(Math.floor((Math.random() * (columns - 0)) + 0));
        System.out.println("StartY : " + startY);
        System.out.println("StartX : " + startX);

        // size maze
        mazeL = new char[rows][columns];
        mazeC = new char[rows][columns];
        check = new boolean[rows][columns];

        // set all cells unvisited
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                check[i][j] = false;
            }
        }

        // display maze
        generation(rows, columns, mazeL, mazeC, check, startY, startX);
        display(rows, columns, mazeL, mazeC);
    }

    public static void display(int rows,int columns, char mazeL[][], char mazeC[][]) {
        for(int i = 0; i < rows; i++) {

            // creating north walls
            for(int j = 0; j < columns; j++) {
                System.out.print((mazeL[i][j] == 0) ? "+---" : "    ");
            }
            System.out.println("+");

            // creating west walls
            for(int j = 0; j < columns; j++) {
                System.out.print((mazeC[i][j] == 0) ? "|   " : "    ");
            }
            System.out.println("|");
        }

        // creating bottom line
        for (int j = 0; j < columns; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
    }

    public static void generation(int rows, int columns, char mazeL[][], char mazeC[][], boolean check[][], int startY, int startX) {
        String[] directions = { "North", "South", "East", "West"};
        String dir = directions[(int) (Math.floor(Math.random() * directions.length))];

        // int dir = (int)(Math.floor((Math.random() * (4 - 0)) + 0));
        System.out.println("StartY : " + startY);
        System.out.println("StartX : " + startX);
        if(startX == 0)
            startX += 1;
        if(startY == 0)
            startY += 1;

        if(check[startY][startX] == false) {
            check[startY][startX] = true;
            if(dir == "North") {
                try {
                    mazeL[startY][startX] = 1;
                    generation(rows, columns, mazeL, mazeC, check, (startY-1), startX);
                } catch(Exception e) {
                    dir = "South";
                }
            }
            if(dir == "South") {
                try {
                    mazeL[startY+1][startX] = 1;
                    generation(rows, columns, mazeL, mazeC, check, (startY+1), startX);
                } catch(Exception e) {
                    dir = "East";
                }
            }
            if(dir == "East") {
                try {
                    mazeC[startY][startX] = 1;
                    generation(rows, columns, mazeL, mazeC, check, startY, (startX-1));
                } catch(Exception e) {
                    dir = "West";
                }
            }
            if(dir == "West") {
                try {
                    mazeC[startY][startX+1] = 1;
                    generation(rows, columns, mazeL, mazeC, check, startY, (startX+1));
                } catch(Exception e) {
                    startY = (int)(Math.floor((Math.random() * (rows - 0)) + 0));
                    startX = (int)(Math.floor((Math.random() * (columns - 0)) + 0));
                    generation(rows, columns, mazeL, mazeC, check, startY, startX);
                }
            }
        else 
            startY = (int)(Math.floor((Math.random() * (rows - 0)) + 0));
            startX = (int)(Math.floor((Math.random() * (columns - 0)) + 0));
            generation(rows, columns, mazeL, mazeC, check, startY, startX);
        }
    }
}