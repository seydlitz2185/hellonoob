public class ArgsSum{
	public static void main (String[] args){
		int sum = 0;
		for(String s : args){
			sum += Integer.parseInt(s);
		} 
		System.out.println("Sum of args:" + sum);
	}
//test
}
