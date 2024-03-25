package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {
    private WeightedQuickUnionUF model;
    private WeightedQuickUnionUF model1;
    private boolean[][] twoD_arr;
    private int N;
    private int sites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {


        if((N < 0)){
            throw new IllegalArgumentException("index must not be negative");
        }
        this.N = N;
        twoD_arr = new boolean[N][N];
        model = new WeightedQuickUnionUF(N*N + 2);
        model1 = new WeightedQuickUnionUF(N*N + 2);
        this.sites = 0;

        //union the top N elements with the N+1 th element, union the bottom N elements with the N+2 the elements
        for(int i = 0; i < N; i++){
            model.union(i, N*N);
            model1.union(i, N*N);
            model.union(N*(N-1)+i, N*N+1);
        }

    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if((row < 0) | (col < 0) | (row > this.N-1) | (col > this.N-1)){
            throw new IndexOutOfBoundsException("index outside prescribed range");
        }
        if (twoD_arr[row][col]){
            return;
        }
        twoD_arr[row][col] = true;
        sites = sites + 1;

        if((row !=0 ) & (row != N-1) & (col != 0) & (col != N-1) ){
            for(int i = col-1; i < col+2; i++){
                if(isOpen(row, i)){
                    model.union(row*N+i, row*N+col);
                    model1.union(row*N+i, row*N+col);
                }
            }
            for(int i = row -1; i < row+2; i++){
                if(isOpen(i, col)){
                    model.union(i*N+col, row*N+col);
                    model1.union(i*N+col, row*N+col);
                }
            }
        }
        if ((col == 0) & (row != N-1) & (row != 0)){
            for(int i = row -1; i < row+2; i++){
                if(isOpen(i, col)){
                    model.union(i*N+col, row*N+col);
                    model1.union(i*N+col, row*N+col);
                }
            }
            if(isOpen(row, col+1)){
                model.union(row*N+col+1, row*N+col);
                model1.union(row*N+col+1, row*N+col);
            }
        }
        if ((col == N-1) & (row != N-1) & (row != 0)){
            for(int i = row -1; i < row+2; i++){
                if(isOpen(i, col)){
                    model.union(i*N+col, row*N+col);
                    model1.union(i*N+col, row*N+col);
                }
            }
            if(isOpen(row, col-1)){
                model.union(row*N+col-1, row*N+col);
                model1.union(row*N+col-1, row*N+col);
            }
        }


        if ((row == N-1) & (col != N-1) & (col != 0)){
            for(int i = col -1; i < col+2; i++){
                    if(isOpen(row, i)){
                        model.union(i+row*N, row*N+col);
                        model1.union(i+row*N, row*N+col);
                    }

            }
            if(isOpen(row-1, col)){
                model.union((row-1)*N+col, row*N+col);
                model1.union((row-1)*N+col, row*N+col);
            }
        }

        if ((row == N-1) & (col == 0)){
            if(isOpen(row-1, col)){
                model.union(row*N+col,(row-1)*N+col );
                model1.union(row*N+col,(row-1)*N+col );
            }

            if(isOpen(row, col+1)){
                model.union((row)*N+col+1, row*N+col);
                model1.union((row)*N+col+1, row*N+col);
            }

        }

        if ((row == N-1) & (col == N-1)){
            if(isOpen(row-1, col)){
                model.union((row-1)*N+col, row*N+col);
                model1.union((row-1)*N+col, row*N+col);
            }

            if(isOpen(row, col-1)){
                model.union((row)*N+col-1, row*N+col);
                model1.union((row)*N+col-1, row*N+col);
            }

        }

        if ((row == 0) & (col != N-1) & (col != 0)){
            for(int i = col-1; i < col+2; i++){
                if(isOpen(row, i)){
                    model.union(i, col);
                    model1.union(i, col);
                }
            }
            if(isOpen(row+1, col)){
                model.union(col, N + col);
                model1.union(col, N + col);
            }
        }

        if ((row == 0) & (col == N-1)){
            if(isOpen(row, col-1)){
                model.union(col-1, col);
                model1.union(col-1, col);
            }


            if(isOpen(row+1, col)){
                model.union(col, N + col);
                model1.union(col, N + col);
            }
        }

        if ((row == 0) & (col == 0)){
            if(isOpen(row, col+1)){
                model.union(0, 1);
                model1.union(0, 1);
            }


            if(isOpen(row+1, col)){
                model.union(0, N );
                model1.union(0, N );
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row<0||row>=N||col<0||col>=N){
            throw new IllegalArgumentException("row and col must between 0 and N-1");
        }
        return twoD_arr[row][col];
    }

    // is the site full?
    public boolean isFull(int row, int col){
        if (row<0||row>=N||col<0||col>=N){
            throw new IllegalArgumentException("row and col must between 0 and N-1");

        }
        if(!isOpen(row, col)){
            return false;
        }
        if(model.connected(N*N, N*N+1)){
            if (model1.connected(N*N, row*N + col)){
                return true;
            }
            return false;
        }
        return model.connected(N*N, row*N+col);
    }
    // number of open sites
    public int numberOfOpenSites(){
        return sites;
    }
    public boolean percolates(){
        return model.connected(N*N, N*N+1);
    }              // does the system percolate?
    // use for unit testing (not required)
    public static void main(String[] args){
        Percolation a = new Percolation(10);
        a.open(3, 5);
        a.open(4, 6);
        a.numberOfOpenSites();
    }

}


