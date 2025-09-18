public class OOPFactorial {
    public long compute(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * compute(n - 1); // Recursive OOP style
    }
}