import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {
    
    @Override
    public abstract Optional<T> get();

    public abstract boolean hasNext();

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return Optional.ofNullable(supplier.get());
            }

            public boolean hasNext() {
                return true;
            }
        };
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>() {
            private T element = seed;
            private boolean firstElement = true;
            
            public Optional<T> get() {
                if (firstElement) {
                    firstElement = false;
                } else {
                    element = f.apply(element);
                }
                return Optional.ofNullable(element);
            }

            public boolean hasNext() {
                return true;
            }
        };
    }

    public InfiniteListImpl<T> limit(long maxSize) throws IllegalArgumentException {
        if (maxSize < 0) {
            throw new IllegalArgumentException(String.valueOf(maxSize));
        } else {
            return new InfiniteListImpl<T>() {
                private long count = 0;
                private long limit = maxSize;

                public Optional<T> get() {
                    Optional<T> current = InfiniteListImpl.this.get();
                    if (count < limit && current.isPresent()) {
                        count++;
                        return current;
                    } else {
                        return Optional.empty();
                    }
                }

                public boolean hasNext() {
                    return InfiniteListImpl.this.hasNext() && count < limit;
                }
            };
        }
    }

    public void forEach(Consumer<? super T> action) {
        while (hasNext()) {
            get().ifPresent(action);
        }
    }

    public Object[] toArray() {
        List<Object> list = new ArrayList<>();
        forEach(x -> list.add(x));
        return list.toArray();
    }

    public <S> InfiniteListImpl<S> map(Function<? super T, ? extends S> mapper) {
        return new InfiniteListImpl<S>() {
            
            public Optional<S> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }

            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext();
            }
        };
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            
            public Optional<T> get() {
                return InfiniteListImpl.this.get().filter(predicate);
            }

            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext();
            }
        };
    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            private Optional<T> next = InfiniteListImpl.this.get();

            public Optional<T> get() {
                Optional<T> current = next;
                next = InfiniteListImpl.this.get();
                return current;
            }

            public boolean hasNext() {
                return InfiniteListImpl.this.hasNext() && next.filter(predicate).isPresent();
            }
        };
    }

    public long count() {
        return map(x -> 1).reduce(0, (x, y) -> x + y);
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> result = get();
        while (hasNext()) {
            result = result.map(x -> accumulator.apply(x, get().get()));
        }
        return result;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T result = identity;
        while (hasNext()) {
            Optional<T> next = get();
            if (next.isPresent()) {
                result = accumulator.apply(result, next.get());
            }
        }
        return result;
    }
}
