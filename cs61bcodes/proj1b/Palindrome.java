
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> retVal = new ArrayDeque<Character>();
        char[] charArray = word.toCharArray();
        for (int i = 0; i< charArray.length; i++){
            retVal.addLast((Character) charArray[i]);
        }
        return retVal ;
    }
}
