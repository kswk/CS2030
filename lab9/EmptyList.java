import java.util.function.Function;
import java.util.function.Predicate;

public class EmptyList<T> extends InfiniteListImpl<T> {
    
    public EmptyList() {
        super(null, null);
    }
    
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }

    @Override
    public EmptyList<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public EmptyList<T> peek() {
        return this;
    }

    @Override
    public EmptyList<T> limit(long n) {
        return this;
    }

    @Override
    public EmptyList<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }
}
