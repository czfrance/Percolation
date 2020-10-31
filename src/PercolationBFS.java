import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    protected void dfs(int row, int col) {
        int[] rowDelta = {-1,1,0,0};
        int[] colDelta = {0,0,-1,1};

        Queue<int[]> cells = new LinkedList<>();

        myGrid[row][col] = FULL;
        cells.add(new int[]{row, col});

        while (cells.size() != 0) {
            int[] cell = cells.remove();
            for (int i = 0; i < rowDelta.length; i++) {
                int r = cell[0] + rowDelta[i];
                int c = cell[1] + colDelta[i];
                if (inBounds(r, c) && myGrid[r][c] == OPEN) {
                    myGrid[r][c] = FULL;
                    cells.add(new int[]{r, c});
                }
            }
        }
    }
}
