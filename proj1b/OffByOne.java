public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int difference = x - y;
        return difference == 1 || difference == -1;
    }
}
