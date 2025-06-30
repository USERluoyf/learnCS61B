import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;


public class Percolation {
    // TODO: Add any necessary instance variables.

    private WeightedQuickUnionUF uf;
    private int[][] grid;
    private int opensize;
    private int gridsize;
    private int vTop;
    private int vBottom;
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0; // 0 blocked, 1 open
            }
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        opensize = 0;
        gridsize = N;
        vTop = 0;
        vBottom = N * N + 1;

    }



    private void validate(int r, int c) {
        if (r < 0 || r >= gridsize || c < 0 || c >= gridsize) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private int xyTo1D(int r, int c) {
        return r * gridsize + c + 1;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }

        grid[row][col] = 1;
        opensize++;
        int index = xyTo1D(row, col);

        if (row == 0) {
            uf.union(vTop, index);
        }

        if (row == gridsize - 1) {
            uf.union(vBottom, index);
        }

        // Check and union with neighbors
        // up
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(index, xyTo1D(row - 1, col));
        }
        // down
        if (row < gridsize - 1 && isOpen(row + 1, col)) {
            uf.union(index, xyTo1D(row + 1, col));
        }
        // left
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(index, xyTo1D(row, col - 1));
        }
        // right
        if (col < gridsize - 1 && isOpen(row, col + 1)) {
            uf.union(index, xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(vTop, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return opensize;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
//        for (int i = 0; i < grid.length; i++) {
//            if (isFull(gridsize-1,i)) {
//                return true;
//            }
//        }
//        return false;
        return uf.connected(vTop, vBottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
