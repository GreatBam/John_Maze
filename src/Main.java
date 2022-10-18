import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int l = 0, c = 0;
        char maze[][];
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("How many lines");
            l = sc.nextInt();
            System.out.println("How many columns");
            c = sc.nextInt();
        }

        maze = new char[l][c];

        Initialize(l, c, maze);
    }

    public static void Initialize(int l,int c, char t[][]) {
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < c; j++) {
                System.out.print((t[i][j] == 0) ? "+---" : "    ");
            }
            System.out.println("+");
            for(int j = 0; j < c; j++) {
                System.out.print((t[i][j] == 0) ? "|   " : "    ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < c; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
    }      
}