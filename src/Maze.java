import java.util.Scanner;
import java.util.InputMismatchException;

public class Maze {
    public static void main(String[] args) {
        int rows = 0, columns = 0; char mazeY[][];char mazeX[][]; boolean check[][]; Scanner sc = new Scanner(System.in); int run = 0; boolean path[][];
        int pathY = 0; int pathX = 0; boolean visited[][];

        // ask for number of rows/columns
        while(true) {
            try {
                System.out.print("How many rows: ");
                rows = sc.nextInt();
                if((rows >= 0 && rows <= Integer.MAX_VALUE)) {
                    System.out.println("Set");
                    System.out.println();
                    break;
                }
            }
            catch(InputMismatchException ex) {
                System.out.println("Error : invalid value");
                System.out.println();
                sc.next();
            }           
        }
        while(true) {
            try {
                System.out.print("How many columns: ");
                columns = sc.nextInt();
                if((columns >= 0 && columns <= Integer.MAX_VALUE)) {
                    System.out.println("Set");
                    System.out.println();
                    break;
                }
            }
            catch(InputMismatchException ex) {
                System.out.println("Error : invalid value");
                System.out.println();
                sc.next();
            }           
        }
        System.out.println("Lines   : " + rows);
        System.out.println("Columns : " + columns);
        System.out.println();
        sc.close();

        // initialize starting points
        int frontierY = (int)(Math.floor((Math.random() * ((rows-1) - 1)) + 1));
        int frontierX = (int)(Math.floor((Math.random() * ((columns-1) - 1)) + 1));

        // size maze
        mazeY = new char[rows][columns];
        mazeX = new char[rows][columns];
        check = new boolean[rows][columns];
        path = new boolean[rows][columns];
        visited = new boolean[rows][columns];

        // set all cells unvisited
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                check[i][j] = false;
                path[i][j] = false;
                visited[i][j] = false;
            }
        }
        path[0][0] = true;

        // generate maze
        // pathY = (rows-1)/2;
        // pathX = (columns-1)/2;
        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
        solver(rows, columns, mazeY, mazeX, path, pathX, pathY, run, visited);
        display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
    }

    public static void generate(int rows, int columns, char mazeY[][], char mazeX[][], boolean check[][], int frontierY, int frontierX, int run, boolean path[][]) {
        int cylce = 0;
        String[] directions = { "North", "South", "East", "West"};
        String dir = directions[(int) (Math.floor(Math.random() * directions.length))];
        mazeY[0][0] = 1;
        run += 1;
            
        // maze generation
        check[frontierY][frontierX] = true;
        while(true) {
            if(dir == "North") {
                cylce += 1;
                if(((frontierY-1) >= 0) && ((frontierY-1) < rows)) {
                    if(check[frontierY-1][frontierX] == false) {
                        mazeY[frontierY][frontierX] = 1;
                        frontierY -= 1;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    } else {
                        dir = "South";
                    }
                } else {
                    dir = "South";
                }
            }
            if(dir == "South") {
                cylce += 1;
                if(((frontierY+1) >= 0) && ((frontierY+1) < rows)) {
                    if(check[frontierY+1][frontierX] == false) {
                        mazeY[frontierY+1][frontierX] = 1;
                        frontierY += 1;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    } else {
                        dir = "West";
                    }
                } else {
                    dir = "West";
                }
            }
            if(dir == "West") {
                cylce += 1;
                if(((frontierX-1) >= 0) && ((frontierX-1) < columns)) {
                    if(check[frontierY][frontierX-1] == false) {
                        mazeX[frontierY][frontierX] = 1;
                        frontierX -= 1;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    } else {
                        dir = "East";
                    }
                } else { 
                    dir = "East";
                }
            }
            if(dir == "East") {
                cylce += 1;
                if(((frontierX+1) >= 0) && ((frontierX+1) < columns)) {
                    if(check[frontierY][frontierX+1] == false) {
                        mazeX[frontierY][frontierX+1] = 1;
                        frontierX += 1;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    } else {
                        dir = "North";
                    }
                } else {
                    dir = "North";
                }
            }
            if(cylce > 4) {
                terminate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                break;
            }
        }
    }

    public static void terminate(int rows, int columns, char mazeY[][], char mazeX[][], boolean check[][], int frontierY, int frontierX, int run, boolean path[][]) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(((i-1) >= 0) && ((i-1) < rows)) {
                    if(check[i][j] == true && check[i-1][j] == false) {
                        frontierY = i;
                        frontierX = j;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    }
                }
                if(((i+1) >= 0) && ((i+1) < rows)) {
                    if(check[i][j] == true && check[i+1][j] == false) {
                        frontierY = i;
                        frontierX = j;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    }
                }
                if(((j-1) >= 0) && ((j-1) < columns)) {
                    if(check[i][j] == true && check[i][j-1] == false) {
                        frontierY = i;
                        frontierX = j;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    }
                }
                if(((j+1) >= 0) && ((j+1) < columns)) {
                    if(check[i][j] == true && check[i][j+1] == false) {
                        frontierY = i;
                        frontierX = j;
                        generate(rows, columns, mazeY, mazeX, check, frontierY, frontierX, run, path);
                        break;
                    }
                }
            }
        }
    }

    public static void solver(int rows,int columns, char mazeY[][], char mazeX[][], boolean path[][], int pathX, int pathY, int run, boolean visited[][]) {
        int pathSearch = 0;

        // display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
        
        while(true) {
            // visited[pathY][pathX] = true;
            // path[pathY][pathX] = true;
            
            if(pathX == (columns-1) && (pathY == (rows-1))) {
                path[pathY][pathX] = true;
                System.out.println("done");
                System.out.println();
                System.exit(0);
                display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
                break;
            }
            
            if(((pathX+1) >= 0) && ((pathX+1) < columns)) {
                if(mazeX[pathY][pathX+1] == 1) {
                    if(visited[pathY][pathX+1] == false) {
                        System.out.println("right");
                        System.out.println();
                        pathX += 1;
                        visited[pathY][pathX] = true;
                        path[pathY][pathX] = true;
                        display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
                        solver(rows, columns, mazeY, mazeX, path, pathX, pathY, run, visited);
                        break;
                    }
                }
            } else {
                pathSearch += 1;
            }
            
            if(((pathY+1) >= 0) && ((pathY+1) < rows)) {
                if(mazeY[pathY+1][pathX] == 1) {
                    if(visited[pathY+1][pathX] == false) {
                        System.out.println("down");
                        System.out.println();
                        pathY += 1;
                        visited[pathY][pathX] = true;
                        path[pathY][pathX] = true;
                        display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
                        solver(rows, columns, mazeY, mazeX, path, pathX, pathY, run, visited);
                        break;
                    }
                }
            } else {
                pathSearch += 1;
            }

            if(((pathX-1) >= 0) && ((pathX-1) < columns)) {
                if(mazeX[pathY][pathX] == 1) {
                    if(visited[pathY][pathX-1] == false) {
                        System.out.println("left");
                        System.out.println();
                        pathX -= 1;
                        visited[pathY][pathX] = true;
                        path[pathY][pathX] = true;
                        display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
                        solver(rows, columns, mazeY, mazeX, path, pathX, pathY, run, visited);
                        break;
                    }
                }
            } else {
                pathSearch += 1;
            }
            
            if(((pathY-1) >= 0) && ((pathY-1) < rows)) {
                if(mazeY[pathY][pathX] == 1) {
                    if(visited[pathY-1][pathX] == false) {
                        System.out.println("up");
                        System.out.println();
                        pathY -= 1;
                        visited[pathY][pathX] = true;
                        path[pathY][pathX] = true;
                        solver(rows, columns, mazeY, mazeX, path, pathX, pathY, run, visited);
                        break;
                    }
                }
            } else {
                pathSearch += 1;
            }

            if(pathSearch == 4) {
                System.out.println("Exit solving loop");
                display(rows, columns, mazeY, mazeX, run, path, pathX, pathY);
                System.exit(0);
            }
        }
    }

    public static void display(int rows,int columns, char mazeY[][], char mazeX[][], int run, boolean path[][], int pathX, int pathY) {
        for(int i = 0; i < rows; i++) {

            // creating north walls
            for(int j = 0; j < columns; j++) {
                System.out.print((mazeY[i][j] == 0) ? "+---" : "+   ");
            }
            System.out.println("+");

            // creating west walls
            for(int j = 0; j < columns; j++) {
                if(mazeX[i][j] == 0) {
                    if(path[i][j] == false) {
                        System.out.print("|   ");
                    } else {
                        System.out.print("| # ");
                    }
                } else {
                    if(path[i][j] == false) {
                        System.out.print("    ");
                    } else {
                        System.out.print("  # ");
                    }
                }
            }
            System.out.println("|");
        }

        // creating bottom line
        for (int j = 0; j < columns-1; j++) {
			System.out.print("+---");
		}
        System.out.println("+   +");

        // iteration counter
        System.out.println("");
        System.out.println("Path X : " + pathX);
        System.out.println("Path Y : " + pathY);
        System.out.println("");
    }
}