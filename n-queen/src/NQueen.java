public class NQueen {
    private int[][] board;
    private int n;

    private boolean canPlace(int row, int col) {
        // check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // check left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // check right diagonal
        for (int i = row, j = col; i >= 0 && j < this.n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }
}
