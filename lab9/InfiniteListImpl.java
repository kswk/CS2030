import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.List;
import java.util.Optional;

public class InfiniteListImpl<T> implements InfiniteList<T> {
    
    private final Lazy<T> head;
    private final Lazy<InfiniteListImpl<T>> tail;

    protected InfiniteListImpl(Lazy<T> head, Lazy<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }
    
    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        Lazy<T> newHead = Lazy.generate(() -> supplier.get());
        Lazy<InfiniteListImpl<T>> newTail = Lazy.generate(() -> InfiniteListImpl.generate(supplier));
        return new InfiniteListImpl<>(newHead, newTail);
    }
    
    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> newHead = Lazy.ofNullable(seed);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.generate(() -> InfiniteListImpl.iterate(next.apply(seed), next));
        return new InfiniteListImpl<>(newHead, newTail);
    }
    
    @Override
    public InfiniteList<T> peek() {
        head.get().ifPresent(System.out::println);
        return tail.get().orElseThrow();
    }
    
    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = head.map(mapper);
        Lazy<InfiniteListImpl<R>> newTail = Lazy.generate(() -> tail.get().orElseThrow().map(mapper));
        return new InfiniteListImpl<>(newHead, newTail);
    }
    
    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = head.filter(predicate);
        Lazy<InfiniteListImpl<T>> newTail = Lazy.generate(() -> tail.get().orElseThrow().filter(predicate));
        return new InfiniteListImpl<>(newHead, newTail);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<>();
        } else {
            if (n == 1) {
                return new InfiniteListImpl<>(head, Lazy.generate(() -> head.get().isPresent() 
                    ? new EmptyList<>() 
                    : tail.get().orElseThrow().limit(n)));
            } else {
                return new InfiniteListImpl<>(head, tail.map(x -> head.get().isPresent() 
                    ? x.limit(n - 1)
                    : x.limit(n)));
            }
        }
    }

    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead = head.filter(predicate);
        return new InfiniteListImpl<>(newHead, 
            Lazy.generate(() -> head.get().isPresent() && newHead.get().isEmpty() 
                ? new EmptyList<>() 
                : tail.get().orElseThrow().takeWhile(predicate)));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> curr = this;
        while (!curr.isEmpty()) {
            curr.head.get().ifPresent(action);
            curr = curr.tail.get().orElseThrow();
        }
    }

    @Override
    public Object[] toArray() {
        List<T> list = new ArrayList<>();
        forEach(x -> list.add(x));
        return list.toArray();
    }

    @Override
    public long count() {
        return toArray().length;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        InfiniteListImpl<T> curr = this;
        U result = identity;
        while (!curr.isEmpty()) {
            Optional<T> head = curr.head.get();
            if (head.isPresent()) {
                result = accumulator.apply(result, head.orElseThrow());
            }
            curr = curr.tail.get().orElseThrow();
        }
        return result;
    }
}
