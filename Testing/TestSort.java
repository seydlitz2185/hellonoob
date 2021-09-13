/*
  @author stevenyu
 */
import org.junit.Test;
public class TestSort {
    @Test
    public void testFindSmallest(){
        String[] input1={"I","just","bought","Devil","May","Cry"};
        int expected1 = 2;
        String[] input2={"Resident","evil","Bioharzard"};
        int expected2 = 2;
        int retval1 = Sort.findSmallest(input1,0);
        org.junit.Assert.assertEquals(retval1,expected1);
        int retval2 = Sort.findSmallest(input2,0);
        org.junit.Assert.assertEquals(retval2,expected2);
    }
    @Test
    public void testSwap(){
        String[] input={"I","just","bought","Devil","May","Cry"};
        String[] expected={"bought","just","I","Devil","May","Cry"};
        Sort.swap(input,0,2);
        org.junit.Assert.assertEquals(expected,input);
    }
    @Test
    public void testSort(){
        String[] input={"I","just","bought","Devil","May","Cry"};
        String[] expected = {"bought","Cry","Devil","I","just","May"};
        Sort.sort(input,0);
        org.junit.Assert.assertEquals(expected,input);
    }
/**    public static void main(String[] args){
        testFindSmallest();
        testSwap();
        testSort();
    }
*/
}