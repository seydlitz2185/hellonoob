
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> retVal = new ArrayDeque<Character>();
        char[] charArray = word.toCharArray();
        for (int i = 0; i< charArray.length; i++){
            retVal.addLast((Character) charArray[i]);
        }
        return retVal ;
    }

    public boolean isPalindrome(String word){
        if(word.length() == 1||word.length() == 0){
            return true;
        }
        Deque d = palindrome.wordToDeque(word);
        String ascWord = "";
        String descWord = "";
        for (int i = 0; i < word.length()/2; i++) {
            ascWord += d.removeFirst();
            descWord += d.removeLast();
        }
        return ascWord.equals(descWord);
    }

    /**
     * Generic palidrome :To allow for odd length palindromes,
     * we do not check the middle character for equality with itself.
     * So “flake” is an off-by-1 palindrome,
     * even though ‘a’ is not one character off from itself.
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = palindrome.wordToDeque(word);
        for (int i = 0; i < word.length()/2; i++) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())){
                return false;
            }
        }
        return true;
    }
    static Palindrome palindrome = new Palindrome();
}
