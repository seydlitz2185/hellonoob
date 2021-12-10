package hw3.hash;

public class Hint {
    public static void main(String[] args) {
        System.out.println("The powers of 256 in Java are:");
        int x = 1;
        for (int i = 0; i < 10; i += 1) {
            System.out.println(i + "th power: " + x);
            x = x * 256;/*java里乘256相当于左移8位，如果有效位全部移丢就变成0*/
            //x+=i;
        }
    }
} 
