package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;


public class Board implements WorldState{

    private final int[][] tile;
    private static final int BLANK = 0;
    private int N;
    private int esitimateddistance;
    public Board(int[][] tiles){
        N = tiles.length;
        this.tile = new int[N][N];

        esitimateddistance = -1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                this.tile[i][j]=tiles[i][j];
        }

    }
    public int tileAt(int i, int j){
        if ((i < 0) || (i > N - 1) || (j < 0) || (j > N-1)) {
            throw new IndexOutOfBoundsException("the coordinate parameter should be between 0 and N-1");
        }
        return tile[i][j];
    }
    public int size(){
        return N;
    }
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming(){
        int a = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(tile[i][j] == 0){
                    continue;
                }else if((i*N+1+j != tile[i][j])){
                        a++;
                }

            }
        }
        return a;
    }
    public int manhattan(){
        int a = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(tile[i][j] == 0){
                    continue;
                }else{
                    int targeti = (tile[i][j] - 1) / N;
                    int targetj = (tile[i][j] - 1) % N;
                    a += Math.abs(targeti - i);
                    a += Math.abs(targetj - j);
                }
            }
        }
        return a;
    }
    public int estimatedDistanceToGoal(){
        if(esitimateddistance == -1){
            esitimateddistance = manhattan();
        }
        return esitimateddistance;
    }
    public boolean equals(Object y){
        if (y == this){
            return true;
        }
        if (y == null || y.getClass() != this.getClass()){
            return false;
        }
        Board other = (Board) y;
        if (this.N != other.N) return false;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(this.tile[i][j] != other.tile[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }


}
