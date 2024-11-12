public class NQueens {
    static int N = 4;

    // Method to print the solution board
    static void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if a queen can be placed at board[row][col]
    static boolean isSafe(int board[][], int row, int col) {
        // Check row on the left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    // Utility method to solve the N-Queens problem
    static boolean solveNQUtil(int board[][], int col) {
        if (col >= N) {
            return true;
        }

        // Consider this column and try placing a queen in all rows one by one
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                // Recursively place the rest of the queens
                if (solveNQUtil(board, col + 1)) {
                    return true;
                }

                // If placing queen in board[i][col] doesn't lead to a solution
                // then backtrack
                board[i][col] = 0;
            }
        }
        return false;
    }

    // Method to solve the N-Queens problem
    static boolean solveNQ() {
        int board[][] = new int[N][N];

        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(board);
        return true;
    }

    public static void main(String args[]) {
        solveNQ();
    }
}
