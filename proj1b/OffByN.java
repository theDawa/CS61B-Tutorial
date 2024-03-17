import static java.lang.Math.abs;

public class OffByN implements CharacterComparator{
    public int offByN;
    public OffByN(int N){
        this.offByN = N;
    }
    public boolean equalChars(char x, char y){
        return abs(x - y) == offByN;
    }
}
