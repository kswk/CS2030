public class LongerThan implements BooleanCondition<String> {

    private final int len;

    public LongerThan(int len) {
        this.len = len;
    }

    public boolean test(String string) {
        return string.length() > len;
    }
}
