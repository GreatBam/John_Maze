import java.util.Scanner;
import java.util.InputMismatchException;

public class Maze {
    public static void main(String[] args) {
        int rows = 0, columns = 0; char maze[][]; Scanner sc = new Scanner(System.in);

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

        // size maze
        maze = new char[rows][columns];

        // display maze
        generation(rows, columns, maze);
        display(rows, columns, maze);
    }

    public static void display(int rows,int columns, char maze[][]) {
        for(int i = 0; i < rows; i++) {

            // creating north walls
            for(int j = 0; j < columns; j++) {
                System.out.print(((maze[i][j] & 1) == 0) ? "+---" : "    ");
            }
            System.out.println("+");

            // creating west walls
            for(int j = 0; j < columns; j++) {
                System.out.print(((maze[i][j] & 8) == 0) ? "|   " : "    ");
            }
            System.out.println("|");
        }

        // creating bottom line
        for (int j = 0; j < columns; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
    }

    public static void generation(int rows, int columns, char maze[][]) {
        boolean check[][] = new boolean[rows][columns];

        // set all cells unvisited
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                check[i][j] = false;
            }
        }

        int startY = (int)(Math.floor((Math.random() * (rows - 0)) + 0));
        int startX = (int)(Math.floor((Math.random() * (columns - 0)) + 0));
        System.out.println("StartY : " + startY);
        System.out.println("StartX : " + startX);

        check[startY][startX] = true;
        (maze[startY][startX] & 1) = 1;



    }
}