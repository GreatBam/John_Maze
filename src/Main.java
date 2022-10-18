import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        int lines = 0, columns = 0; char maze[][]; Scanner sc = new Scanner(System.in);

        // checking for integers
        while(true) {
            try {
                System.out.print("How many lines: ");
                lines = sc.nextInt();
                if((lines >= 0 && lines <= Integer.MAX_VALUE)) {
                    System.out.println("Set");
                    System.out.println(" ");
                    break;
                }
            }
            catch(InputMismatchException ex) {
                System.out.println("Error : invalid value");
                System.out.println(" ");
                String s = sc.next();
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
                String s = sc.next();
            }           
        }
        System.out.println("Lines   : " + lines);
        System.out.println("Columns : " + columns);
        System.out.println(" ");
        sc.close();

        // size maze
        maze = new char[lines][columns];

        // display maze
        display(lines, columns, maze);
    }

    public static void display(int lines,int columns, char maze[][]) {
        for(int i = 0; i < lines; i++) {

            // creating north walls
            for(int j = 0; j < columns; j++) {
                System.out.print((maze[i][j] == 0) ? "+---" : "    ");
            }
            System.out.println("+");

            // creating west walls
            for(int j = 0; j < columns; j++) {
                System.out.print((maze[i][j] == 0) ? "|   " : "    ");
            }
            System.out.println("|");
        }

        // creating bottom line
        for (int j = 0; j < columns; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
    }      
}