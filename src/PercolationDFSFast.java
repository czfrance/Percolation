public class PercolationDFSFast extends PercolationDFS {
    public PercolationDFSFast(int n) {
        super(n);
    }

    public boolean adjacentToFull(int row, int col) {
        boolean ret = false;
        if (inBounds(row - 1, col) && myGrid[row - 1][col] == FULL) {
            ret = true;
        }
        if (inBounds(row + 1, col) && myGrid[row + 1][col] == FULL) {
            ret = true;
        }
        if (inBounds(row, col - 1) && myGrid[row][col - 1] == FULL) {
            ret = true;
        }
        if (inBounds(row, col + 1) && myGrid[row][col + 1] == FULL) {
            ret = true;
        }

        return ret;
    }

    @Override
    protected void updateOnOpen(int row, int col) {
//        if (! inBounds(row,col)) return;
//
//        if (isFull(row, col) || !isOpen(row, col))
//            return;

        if (row == 0 || adjacentToFull(row, col)) {
            dfs(row, col);
        }
    }
}
