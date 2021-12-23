import java.util.*;

public class App {
    public static Scanner input = new Scanner(System.in);
    public static boolean player = true;
    public static char[][] grid = printGrid(3, 3, '_');

    public static void main(String[] args) throws Exception {
        System.out.println("TicTacToe");
        boolean win = false;
        System.out.println("\n");
        input.useDelimiter(System.lineSeparator());
        printArr(grid);
        int temp = 0;
        while (!win) {
            temp = player ? 1 : 2;
            System.out.print("Player " + temp + "\'s" + " Move: ");
            int move = input.nextInt();
            updateGrid(grid, move);
            printArr(grid);
            if (checkHwin(grid, 'x', 3) || checkVwin(grid, 'x', 3) || checkDwin(grid, 'x', 3)) {
                win = true;
                System.out.println("\n");
                blast(grid, 1, 1, 'x');
                System.out.println("Player 1 Wins!");
            }
            if (checkHwin(grid, 'o', 3) || checkVwin(grid, 'o', 3) || checkDwin(grid, 'o', 3)) {
                win = true;
                System.out.println("\n");
                blast(grid, 1, 1, 'o');
                System.out.println("Player 2 Wins!");
            }
        }
    }

    public static char[][] printGrid(int row, int col, char symbol) {
        char[][] grid = new char[row][col];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = symbol;
            }
        }
        return grid;
    }

    public static boolean checkHwin(char[][] arr, char symbol, int num) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == symbol) {
                    count += 1;
                    if (arr[i][j] != symbol) {
                        count = 0;
                    }
                    if (count == num) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean checkVwin(char[][] arr, char symbol, int num) {
        int count = 0;
        for (int col = 0; col < arr[0].length; col++) {
            for (int row = 0; row < arr.length; row++) {
                if (arr[row][col] == symbol) {
                    count += 1;
                    if (count == num) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }

    public static boolean writeBlock(char[][] arr, int row, int col, char symbol, boolean overWrite) {
        if (overWrite == true) {
            arr[row][col] = symbol;
            return true;
        }
        return false;
    }

    public static void blast(char[][] arr, int row, int col, char symbol) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                try {
                    arr[i][j] = symbol;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        printArr(grid);
    }

    public static void updateGrid(char[][] arr, int input) {
        int x = 0;
        int y = 0;
        if (input < 4) {
            y = input - 1;
            x = 2;
        }
        if (input > 3 && input < 7) {
            y = input - 4;
            x = 1;
        }
        if (input > 6) {
            y = input - 7;
        }
        if (arr[x][y] == '_') {
            if (player) {
                if(writeBlock(arr, x, y, 'x', true)) {
                    player = !player;
                } 
            } else {
                if (writeBlock(arr, x, y, 'o', true)) {
                    player = !player;
                }
            }
        }
    }

    public static boolean checkDwin(char[][] arr, char symbol, int num) {
        if (arr[0][0] == symbol && arr[1][1] == symbol && arr[2][2] == symbol) {
            return true;
        }
        if (arr[0][2] == symbol && arr[1][1] == symbol && arr[2][0] == symbol) {
            return true;
        }
        return false;
    }

    public static void printArr(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " | ");
            }
            System.out.println();
        }
    }
}