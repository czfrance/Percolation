public class PercolationUF implements IPercolate {

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size) {
        finder.initialize((size * size) + 2);
        myFinder = finder;
        myGrid = new boolean[size][size];
        VTOP = size*size;
        VBOTTOM = size*size + 1;
        myOpenCount = 0;
    }

    public int toCell(int row, int col) {
        return (row * myGrid.length) + col;
    }

    public boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    @Override
    public void open(int row, int col) {
        int cell = toCell(row, col);

        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d) not in bounds", cell));
        }

        if (myGrid[row][col] != true) {
            myGrid[row][col] = true;

            int[] deltaR = {-1, 1, 0, 0};
            int[] deltaC = {0, 0, -1, 1};

            for (int i = 0; i < deltaR.length; i++) {
                int r = row + deltaR[i];
                int c = col + deltaC[i];

                if (inBounds(r, c) && myGrid[r][c]) {
                    myFinder.union(toCell(r, c), cell);
                }
            }

            if (cell >= 0 && cell <= myGrid.length - 1) {
                myFinder.union(VTOP, cell);
            }

            if (cell <= myGrid.length*myGrid.length - 1 && cell >= toCell(myGrid.length - 1, 0)) {
                myFinder.union(VBOTTOM, cell);
            }
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        int cell = toCell(row, col);

        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d) not in bounds", cell));
        }

        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        int cell = toCell(row, col);

        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d) not in bounds", cell));
        }

        return myFinder.connected(VTOP, cell);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
