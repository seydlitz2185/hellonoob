public class OriginFinder {
    public static void main(String[] args) {
        int minLength = 2;
        In in = new In("E:\\lab1\\library-sp19\\data\\words.txt");
        Palindrome palindrome = new Palindrome();
        OffByN offBy5 = new OffByN(5);
        OffByOne offBy1 = new OffByOne();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offBy5)) {
                System.out.println(word);
            }
        }
    }
}
