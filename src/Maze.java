import java.util.Scanner;
import java.util.InputMismatchException;

public class Maze {
    public static void main(String[] args) {
        int rows = 0, columns = 0; char mazeY[][];char mazeX[][]; boolean check[][]; Scanner sc = new Scanner(System.in);

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

        // initialize starting points
        int frontierY = (int)(Math.floor((Math.random() * ((rows-1) - 1)) + 1));
        int frontierX = (int)(Math.floor((Math.random() * ((columns-1) - 1)) + 1));

        // size maze
        mazeY = new char[rows][columns];
        mazeX = new char[rows][columns];
        check = new boolean[rows][columns];

        // set all cells unvisited
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                check[i][j] = false;
            }
        }

        // display maze
        generation(rows, columns, mazeY, mazeX, check, frontierY, frontierX);
        display(rows, columns, mazeY, mazeX);
    }

    public static void display(int rows,int columns, char mazeY[][], char mazeX[][]) {
        for(int i = 0; i < rows; i++) {

            // creating north walls
            for(int j = 0; j < columns; j++) {
                System.out.print((mazeY[i][j] == 0) ? "+---" : "+   ");
            }
            System.out.println("+");

            // creating west walls
            for(int j = 0; j < columns; j++) {
                System.out.print((mazeX[i][j] == 0) ? "|   " : "    ");
            }
            System.out.println("|");
        }

        // creating bottom line
        for (int j = 0; j < columns; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
    }

    public static void generation(int rows, int columns, char mazeY[][], char mazeX[][], boolean check[][], int frontierY, int frontierX) {
        String[] directions = { "North", "South", "East", "West"};
        String dir = directions[(int) (Math.floor(Math.random() * directions.length))];

        // actual debug
        System.out.println("Y : " + frontierY);
        System.out.println("X : " + frontierX);
        System.out.println(" ");

        // stay in range
        // if(frontierX == 0)
        //     frontierX += 1;
        // if(frontierX == columns)
        //     frontierX -= 1;
        // if(frontierY == 0)
        //     frontierY += 1;
        // if(frontierY == rows)
        //     frontierY -= 1;

        // maze generation
        if(check[frontierY][frontierX] == false) {
            check[frontierY][frontierX] = true;
            while(true) {
                if(dir == "North") {
                    if(((frontierY-1) >= 0) && ((frontierY-1) < rows)) {
                        if(check[frontierY-1][frontierX] == false)
                            mazeY[frontierY][frontierX] = 1;
                            frontierY -= 1;
                            generation(rows, columns, mazeY, mazeX, check, frontierY, frontierX);
                    } else {
                        dir = "South";
                    }
                }
                if(dir == "South") {
                    if(((frontierY+1) >= 0) && ((frontierY+1) < rows)) {
                        if(check[frontierY+1][frontierX] == false)
                            mazeY[frontierY+1][frontierX] = 1;
                            frontierY += 1;
                            generation(rows, columns, mazeY, mazeX, check, frontierY, frontierX);
                    } else {
                        dir = "East";
                    }
                }
                if(dir == "East") {
                    if(((frontierX-1) >= 0) && ((frontierX-1) < columns)) {
                        if(check[frontierY][frontierX-1] == false)
                            mazeX[frontierY][frontierX] = 1;
                            frontierX -= 1;
                            generation(rows, columns, mazeY, mazeX, check, frontierY, frontierX);
                    } else { 
                        dir = "West";
                    }
                }
                if(dir == "West") {
                    if(((frontierX+1) >= 0) && ((frontierX+1) < columns)) {
                        if(check[frontierY][frontierX+1] == false)
                            mazeX[frontierY][frontierX+1] = 1;
                            frontierX += 1;
                            generation(rows, columns, mazeY, mazeX, check, frontierY, frontierX);
                    } else {
                        dir = "North";
                    }
                } else {
                    break;
                }
            }
        // } else {
            // frontierY = (int)(Math.floor((Math.random() * ((rows-1) - 0)) + 0));
            // frontierX = (int)(Math.floor((Math.random() * ((columns-1) - 0)) + 0));
            // generation(rows, columns, mazeY, mazeX, check, frontierY, frontierX);
        }
    }
}