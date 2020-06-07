public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int val = x - y;
        if (val == 1 | val == -1) {
            return true;
        }
        return false;
    }
}
