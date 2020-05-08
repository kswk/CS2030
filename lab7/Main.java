import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.DoubleStream;
import java.util.DoubleSummaryStatistics;

public class Main {
    
    public static boolean isPrime(int n) {
        if (n == 0) {
            return false;
        } else {
            return IntStream.range(2, n).noneMatch(x -> n % x == 0);
        }
    }

    public static int[] twinPrimes(int n) {
        IntStream primeStream = IntStream.rangeClosed(3, n).filter(x -> isPrime(x));
        IntStream twinPrimes = primeStream.filter(x -> isPrime(x - 2) || isPrime(x + 2));
        return twinPrimes.toArray();
    }

    public static int gcd(int m, int n) {
        int[] arr = Stream.iterate(new Pair(m, n), x -> x.getY() > 0, x -> new Pair(x.getY(), x.getX() % x.getY())).mapToInt(x -> x.getY()).toArray();
        int len = arr.length;
        int p = arr[len - 1];
        return p;
    }

    public static long countRepeats(int... array) {
        int len = array.length;
        IntStream test = IntStream.of(array);
        List<Integer> lst = new ArrayList<>();
        test.forEach(x -> lst.add(x));
        lst.add(10);
        IntStream stream = IntStream.rangeClosed(1, len).filter(x -> lst.get(x) == lst.get(x - 1) && lst.get(x) != lst.get(x + 1));
        return stream.count();
    }

    public static double normalizedMean(Stream<Integer> stream) {
        DoubleSummaryStatistics stats = stream.flatMapToDouble(x -> DoubleStream.of(x)).summaryStatistics();
        double avg = stats.getAverage();
        double min = stats.getMin();
        double max = stats.getMax();
        double result = (avg - min) / (max - min);
        if (stats.getCount() < 2 || max == min) {
            return 0.0;
        } else {
            return result;
        }
    }

}
