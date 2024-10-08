import java.util.Stack;

public class NQueen {
    private int[][] board;
    private int column;
    private boolean found;
    private int n;

    public NQueen(int n) {
        this.n = n;
        this.board = new int[n][n];
        this.column = 0;
        this.found = false;
    }

    public void solve() {
        Stack<Integer> queens = new Stack<>();
        int row = 0;

        while (!this.found) {
            boolean placed = false;
            for (int col = column; col < this.n; col++) {
                if (canPlace(row, col)) {
                    this.board[row][col] = 1;
                    queens.push(col);
                    placed = true;
                    this.column = 0;
                    row++;

                    if (row == this.n) { // n queens placed
                        this.found = true;
                    }

                    break;
                }
            }

            if (!placed) {
                if (queens.isEmpty()) {
                    break;
                }

                // kind of backtracking
                int prev = queens.pop();
                this.board[--row][prev] = 0;
                this.column = prev + 1;
            }
        }
        this.dump();
    }

    private void dump() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                System.out.print("----");
            }
            System.out.println("-");

            for (int j = 0; j < this.n; j++) {
                String cell = (this.board[i][j] == 1) ? "Q" : " ";
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }

        for (int j = 0; j < this.n; j++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    private boolean canPlace(int row, int col) {
        // check column
        for (int i = 0; i < row; i++) {
            if (this.board[i][col] == 1) {
                return false;
            }
        }

        // check left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (this.board[i][j] == 1) {
                return false;
            }
        }

        // check right diagonal
        for (int i = row, j = col; i >= 0 && j < this.n; i--, j++) {
            if (this.board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }
}
