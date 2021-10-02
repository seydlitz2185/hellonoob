/** This class outputs all palindromes in the words file in the current directory. */
/**
 * brute force
 * @author Dr.Seydlitz
 * @date 2021/10/3 1:32
 */
public class PalindromeFinder {

    public static int findMostPalindromesN(int length,int n){
        In in = new In("E:\\lab1\\library-sp19\\data\\words.txt");
        int palindromecounter = 0;
        int max = 0;


        String maxWord = new String();
        Palindrome palindrome = new Palindrome();
        String word;

        OffByN offByN = new OffByN(n);

        while (!in.isEmpty()) {
            word = in.readString();
            if (word.length() == length && palindrome.isPalindrome(word,offByN)) {
                System.out.println(word);
                palindromecounter++;
                if(word.length()>maxWord.length()){
                    maxWord = word;
                }
            }
        }

        if(palindromecounter != 0) {
            System.out.println(maxWord+" is the longest offBy"+n);
            System.out.println("word lengh = "+length+", N = " + n + " has " + palindromecounter + " palindroms");
        }

        return palindromecounter;
    }


    public static void main(String[] args) {

        In in = new In("E:\\lab1\\library-sp19\\data\\words.txt");
        int wordLength = in.readLine().length();
        int temp = 0;
        int maxN=0;
        int maxi=0;


        while (!in.isEmpty()){
            temp = in.readLine().length();
            if(temp>wordLength){
                wordLength = temp;
            }
        }
        int[][] maxLength = new int[wordLength+1][26];
        for (int i = 0;i<=wordLength; i++) {
            for (int j = 0; j<26;j++) {
                maxLength[i][j] = findMostPalindromesN(i,j);
            }
        }
        int[] maxNs = new int[26];
        for(int j = 0; j<26;j++)  {
            for (int i = 0;i<=wordLength; i++) {
                maxNs[j] += maxLength[i][j] ;
            }
            System.out.println("N = "+j+" has "+maxNs[j]+" palindroms");
        }
        for (int i = 0; i< 26;i++){
            if (maxNs[i]>maxN){
                maxN = maxNs[i];
                maxi = i;
            }
        }
        System.out.println("N = "+maxi+" are there the most palindromes in English, has "+maxN+" palindromes. ");


/**
 *      origin version
 */
//        int minLength = 4;
//        int palindromecounter = 0;
//        In in = new In("E:\\lab1\\library-sp19\\data\\words.txt");
//        Palindrome palindrome = new Palindrome();
//        OffByN offBy5 = new OffByN(5);
//        OffByOne offBy1 = new OffByOne();
//        while (!in.isEmpty()) {
//            String word = in.readString();
//            if (word.length() >= minLength && palindrome.isPalindrome(word,offBy5)) {
//                System.out.println(word);
//            }
//        }
    }
}