public class OffByN implements CharacterComparator {

    private int N;
    public OffByN(int N) {
        this.N = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int val = x - y;
        if (val == -N | val == N) {
            return true;
        }
        return false;
    }
}
