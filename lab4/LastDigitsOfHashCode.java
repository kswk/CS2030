public class LastDigitsOfHashCode implements Transformer<Object, Integer> {

    private final Integer k;

    public LastDigitsOfHashCode(Integer k) {
        this.k = k;
    }

    public Integer transform(Object obj) {
        Integer fullHashCode = Math.abs(obj.hashCode());
        Integer result = fullHashCode % (int) (Math.pow(10, k));
        return result;
    }
}
