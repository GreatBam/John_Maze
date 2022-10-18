import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int lines = 0, columns = 0;
        char maze[][];
        Scanner sc = new Scanner(System.in);

        System.out.print("How many lines: ");
        // checking for integer         
        while (!sc.hasNextInt())
        {
        System.out.println("Please Enter integer!");
        sc.nextLine();
        }
        lines = Integer.parseInt(sc.nextLine());

        System.out.print("How many columns: ");
        //checking for integer           
        while (!sc.hasNextInt())
        {
        System.out.println("Please Enter integer!");
        sc.nextLine();
        }
        columns = Integer.parseInt(sc.nextLine());

        // close Scanner
        sc.close();

        // size maze
        maze = new char[lines][columns];

        // print maze
        Initialize(lines, columns, maze);
    }

    public static void Initialize(int lines,int columns, char maze[][]) {
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

        // bottom line
        for (int j = 0; j < columns; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
    }      
}