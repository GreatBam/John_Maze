import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        int i, j, l = 0, c = 0;
        char maze[][];
        Scanner sc = new Scanner(System.in);


        System.out.println("How many lines");
        l = sc.nextInt();
        System.out.println("How many columns");
        c = sc.nextInt();

        maze = new char[l][c];

        Initialize(l, c, maze);
//        path(l, c, maze);
        Show(l, c, maze);
    }

    public static void Initialize(int l,int c, char t[][]) {
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < c; j++)
                if(i==0 && j==0)
                    t[i][j] = '┌';
                else if(i==0 && j==(c-1))
                    t[i][j] = '┐';
                else if(i > 0 && j==0 && i < (l-1) || i > 0 && j==(c-1) && i < (l-1))
                    t[i][j] = '│';
                else if(j > 0 && i==0 && j < (c-1) || j > 0 && i==(l-1) && j < (c-1))
                    t[i][j] = '─';
                else if(i==(l-1) && j==0)
                    t[i][j] = '└';
                else if(i==(l-1) && j==(c-1))
                    t[i][j] = '┘';
                else
                    t[i][j] = '#';
        }
    }

    public static void Show(int l, int c, char t[][]) {
        for(int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++)
                System.out.print(t[i][j] + " ");
            System.out.println();
        }
    }

    public static boolean Test(int i, int j, char t[][]) {
        int s = 0;
        if(t[i][j]=='#') {
            if(t[i][j-1]==' ')
                s++;
            if(t[i][j+1]==' ')
                s++;
            if(t[i+1][j]==' ')
                s++;
            if(t[i-1][j]==' ')
                s++;
            if(s>1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public static void path(int l, int c, char tab[][]) {
        int low = 0;
        int randomPath = 0;
        int selector = 0;
        int liner = 0;
        int randomStart = (int) (Math.random() * ((c - low)) + low);

        for(int i = 1; i < (l-1); i++) {
            if(randomStart <= 0)
                randomStart += 1;
            if(randomStart >= (c-2))
                randomStart -= 1;
            tab[i][randomStart] = 'X';
            randomPath = randomStart;

            selector = (int)(Math.random() * (30-1+1)+ 1);
            if(selector <= 30 && selector > 20)
                randomPath += 1;
            if(selector <= 20 && selector > 10)
                randomPath += 0;
            if(selector <= 10 && selector > 0)
                randomPath -= 1;

            if(randomPath <= 0)
                randomPath += 1;
            if(randomPath >= (c-2))
                randomPath -= 1;
            tab[i][randomPath] = 'X';

            liner = (int)(Math.random()* (100-1+1)+1);
            if(liner < 50)
                if(selector <= 30 && selector > 20)
                    randomPath += 1;
            if(selector <= 20 && selector > 10)
                randomPath += 0;
            if(selector <= 10 && selector > 0)
                randomPath -= 1;
            if(randomPath <= 0)
                randomPath += 1;
            if(randomPath >= (c-2))
                randomPath -= 1;
            tab[i][randomPath] = 'X';
            randomStart = randomPath;
        }
    }
}