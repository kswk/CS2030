public class DivisibleBy implements BooleanCondition<Integer> {

    private final Integer i;

    public DivisibleBy(Integer i) {
        this.i = i;
    }

    public boolean test(Integer i) {
        return i % this.i == 0;
    }
}
