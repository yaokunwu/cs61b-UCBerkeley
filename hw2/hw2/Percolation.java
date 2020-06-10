package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {
    int opensize;
    int[][] grids;
    int[][] uf_2D;
    WeightedQuickUnionUF uf;
    WeightedQuickUnionUF uf2;
    int vtInd;
    int vbInd;
    int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        grids = new int[n][n];
        uf_2D = new int[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 2);
        opensize = 0;
        vtInd = n * n;
        vbInd = n * n + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                uf_2D[i][j] = i * n + j;
                if (i == 0) {
                    uf.union(uf_2D[i][j], vtInd);
                    uf2.union(uf_2D[i][j], vtInd);
                }
                if (i == n - 1) {
                    uf.union(uf_2D[i][j], vbInd);
                }
            }
        }
    }

    private void connectaround(int row, int col) {
        if (row >= 1 && isOpen(row - 1, col)) {
            uf.union(uf_2D[row - 1][col], uf_2D[row][col]);
            uf2.union(uf_2D[row - 1][col], uf_2D[row][col]);
        }
        if (row < n - 1 && isOpen(row + 1, col)) {
            uf.union(uf_2D[row + 1][col], uf_2D[row][col]);
            uf2.union(uf_2D[row + 1][col], uf_2D[row][col]);
        }
        if (col >= 1 && isOpen(row, col - 1)) {
            uf.union(uf_2D[row][col - 1], uf_2D[row][col]);
            uf2.union(uf_2D[row][col - 1], uf_2D[row][col]);
        }
        if (col < n - 1 && isOpen(row, col + 1)) {
            uf.union(uf_2D[row][col + 1], uf_2D[row][col]);
            uf2.union(uf_2D[row][col + 1], uf_2D[row][col]);
        }
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (grids[row][col] == 0) {
            grids[row][col] = 1;
            opensize += 1;
            connectaround(row, col);
        }

        if (!isOpen(row, col)) {
            grids[row][col] = 1;
            opensize += 1;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (grids[row][col] == 0) {
            return false;
        }
        return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int ind = uf_2D[row][col];
        return (isOpen(row, col) && uf2.connected(ind, vtInd));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opensize;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(vbInd, vtInd);
    }

    // test client (optional)
    public static void main(String[] args) {

        // Percolation p = new Percolation(20);
    }
}
