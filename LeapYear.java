import java.util.Scanner;

public class LeapYear {
	public static void main (String[] args){
		//leapyear
		Scanner in = new Scanner(System.in);
		isLeapYear(in.nextInt());
	}
	
	public static boolean isLeapYear(int year){
		if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0 )){
			System.out.println(year+" is a leap year");
			return true;
		}else {
			System.out.println(year+" is not a leap year");
			return false;
		}
	}
}
	