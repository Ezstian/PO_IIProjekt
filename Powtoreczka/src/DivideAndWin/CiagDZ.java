package DivideAndWin;

public class CiagDZ {
    public static int f(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return 1;
        }
        int a = f(n - 1);
        int b = f(n - 2);
        int c = f(n - 3);
        return (2 * (a + b + c));
    }
    public static void main(String[] args) {
        System.out.println(f(8));
    }
}