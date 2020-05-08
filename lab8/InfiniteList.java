import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;

interface InfiniteList<T> {
    
    Optional<T> get();

    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier);
    }

    static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return InfiniteListImpl.iterate(seed, f);
    }

    InfiniteList<T> limit(long maxSize);

    void forEach(Consumer<? super T> action);

    Object[] toArray();

    <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper);

    InfiniteList<T> filter(Predicate<? super T> predicate);

    InfiniteList<T> takeWhile(Predicate<? super T> predicate);

    long count();

    Optional<T> reduce(BinaryOperator<T> accumulator);

    T reduce(T identity, BinaryOperator<T> accumulator);
}
