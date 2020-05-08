import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lazy<T> {
    
    private T v;
    private Supplier<? extends T> s;
    private boolean evaluated;

    private Lazy(T v) {
        this.v = v;
        s = () -> v;
        evaluated = true;
    }

    private Lazy(Supplier<? extends T> s) {
        this.v = null;
        this.s = s;
        evaluated = false;
    }

    @Override
    public String toString() {
        if (evaluated) {
            if (v == null) {
                return "null";
            } else {
                return v.toString();
            }
        } else {
            return "?";
        }
    }

    public static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<>(v);
    }

    public static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<>(() -> supplier.get());
    }

    public <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return new Lazy<>(() -> get().map(mapper).orElse(null));
    }

    public Lazy<T> filter(Predicate<? super T> predicate) {
        return new Lazy<>(() -> get().filter(predicate).orElse(null));
    }

    public Optional<T> get() {
        if (!evaluated) {
            evaluated = true;
            v = s.get();
            s = () -> v;
        }
        return Optional.ofNullable(v);
    }
}
