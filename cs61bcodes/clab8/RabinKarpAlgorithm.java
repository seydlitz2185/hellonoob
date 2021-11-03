/**
 * @author stevenyu
 */
public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int patternSize = pattern.length();
        /*miss while input.length() < pattern.length()*/
        if(input.length() < pattern.length()){
            return -1;
        }
        /*init RollingString: pattern and first subString*/
        char[] inputChatArray = input.toCharArray();
        RollingString patternString = new  RollingString(pattern,patternSize);
        String sub = String.copyValueOf(inputChatArray, 0,patternSize);
        RollingString subString = new RollingString(sub,patternSize);
        /*get pattern's hashcode*/
        int patternHashCode = patternString.hashCode();
        /*Start matching*/
        for(int i = 0 ; i< input.length()-patternSize;i++) {
            /*get substring's hashcode*/
            int subStringHashCode =  subString.hashCode();
            if(patternHashCode == subStringHashCode){
                if(pattern.equals(subString.toString())){
                    /*hit*/
                    return i;
                }
            }
            /*update substring, remove the first char and add next char from input*/
            char adder = inputChatArray[i+patternSize];
            subString.addChar(adder);
        }
        /*miss*/
        return -1;

    }

}
