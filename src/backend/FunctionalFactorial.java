import java.util.stream.LongStream;

public class FunctionalFactorial {
    public static long compute(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return LongStream.rangeClosed(2, n)
                .reduce(1, (acc, val) -> acc * val);
    }
}