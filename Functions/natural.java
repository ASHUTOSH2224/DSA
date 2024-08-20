import java.io.*;
public class natural {
    static int findSum(int n) {
        return n * (n + 1) / 2;
    }
    public static void main(String args[]) {
        int n = 5;
        System.out.println(findSum(n));
    }
}
