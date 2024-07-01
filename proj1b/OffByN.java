public class OffByN implements CharacterComparator{
    private int diff;

    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int difference = x - y;
        if(difference == diff || difference == -diff) {
            return true;
        }
        return false;
    }
}
