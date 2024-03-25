package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF model;
    private boolean[][] twoD_arr;
    private int N;
    private int sites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {

        this.N = N;
        if((N < 0)){
            throw new IllegalArgumentException("index must not be negative");
        }
        twoD_arr = new boolean[N][N];
        model = new WeightedQuickUnionUF(N * N);

    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if((row < 0) | (col < 0) | (row > this.N-1) | (col > this.N-1)){
            throw new IndexOutOfBoundsException("index outside prescribed range");
        }
        if (!twoD_arr[row][col]){
            twoD_arr[row][col] = true;
        }
        sites++;
        if ((col == 0) & ((row != N-1) & (row !=0 ))){
            if (twoD_arr[row-1][col]){
                model.union((this.N*(row) + col), (this.N*(row-1) + col));
            }
            if (twoD_arr[row+1][col]){
                model.union((this.N*(row) + col), (this.N*(row+1) + col));
            }
            if (twoD_arr[row][col+1]){
                model.union((this.N*(row) + col), (this.N*(row) + col + 1));
            }
        }
        if ((col == N-1)& ((row != N-1) & (row !=0 ))){
            if (twoD_arr[row][col-1]){
                model.union((this.N*(row) + col), (this.N*(row) + col - 1));
            }
            if (twoD_arr[row-1][col]){
                model.union((this.N*(row) + col), (this.N*(row-1) + col));
            }
            if (twoD_arr[row+1][col]){
                model.union((this.N*(row) + col), (this.N*(row+1) + col));
            }
        }

        if ((col == 0)& (row == N-1)){
            if (twoD_arr[row-1][col]){
                model.union((this.N*(row) + col), (this.N*(row-1) + col));
            }
            if (twoD_arr[row][col+1]){
                model.union((this.N*(row) + col), (this.N*(row) + col + 1));
            }
        }
        if (((col != 0)&(col != N-1))&(row == N-1)){
            if (twoD_arr[row][col-1]){
                model.union((this.N*(row) + col), (this.N*(row) + col - 1));
            }
            if (twoD_arr[row-1][col]){
                model.union((this.N*(row) + col), (this.N*(row-1) + col));
            }
            if (twoD_arr[row][col+1]){
                model.union((this.N*(row) + col), (this.N*(row) + col + 1));
            }
        }

        if ((col == N-1)&(row == N-1)){
            if (twoD_arr[row-1][col]){
                model.union((this.N*(row) + col), (this.N*(row-1) + col));
            }
            if (twoD_arr[row][col-1]){
                model.union((this.N*(row) + col), (this.N*(row) + col - 1));
            }

        }
        if((row != 0) & (row != this.N-1) & (col != 0) & (col != this.N-1)){
            if (twoD_arr[row][col-1]){
                model.union((this.N*(row) + col), (this.N*(row) + col - 1));
            }
            if (twoD_arr[row-1][col]){
                model.union((this.N*(row) + col), (this.N*(row-1) + col));
            }
            if (twoD_arr[row+1][col]){
                model.union((this.N*(row) + col), (this.N*(row+1) + col));
            }
            if (twoD_arr[row][col+1]){
                model.union((this.N*(row) + col), (this.N*(row) + col + 1));
            }
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return twoD_arr[row][col];
    }

    // is the site full?
    public boolean isFull(int row, int col){
        boolean a = false;
        if(!isOpen(row, col)){
            return false;
        }
        for(int i = 0; i < this.N; i++){
            if (model.connected(i, this.N*(row)+col)){
                a = true;
            }
        }
        return a;
    }
    // number of open sites
    public int numberOfOpenSites(){
        return sites;
    }
    public boolean percolates(){
        boolean a = false;
        for (int i = 0; i < this.N; i++){
            for(int j = 0; j < this.N; j++){
                if (model.connected(i, this.N*(this.N-1)+j)){
                    a = true;
                }
            }
        }
        return a;
    }              // does the system percolate?
    // use for unit testing (not required)

}
