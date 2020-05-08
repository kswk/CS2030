import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.List;

public class ImmutableList<T> {

    private List<T> list;
    
    public ImmutableList(List<T> list) {
        this.list = list;
    }

    @SafeVarargs
    public ImmutableList(T... arr) {
        list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public ImmutableList<T> add(T t) {
        List<T> newList = new ArrayList<>(list);
        newList.add(t);
        return new ImmutableList<>(newList);
    }

    public ImmutableList<T> replace(T t1, T t2) {
        List<T> newList = new ArrayList<>(list);
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i) == t1) {
                newList.set(i, t2);
            }
        }
        return new ImmutableList<>(newList);
    }

    public ImmutableList<T> remove(T t) {
        List<T> newList = new ArrayList<>(list);
        newList.remove(t);
        return new ImmutableList<>(newList);
    }

    public ImmutableList<T> filter(Predicate<? super T> predicate) {
        List<T> newList = new ArrayList<>(list);
        List<T> filteredList = new ArrayList<>();
        int len = newList.size();
        for (int i = 0; i < len; i++) {
            if (predicate.test(newList.get(i))) {
                filteredList.add(newList.get(i));
            }
        }
        return new ImmutableList<>(filteredList);
    }

    public <U> ImmutableList<U> map(Function<? super T, ? extends U> function) {
        List<T> newList = new ArrayList<>(list);
        List<U> mappedList = new ArrayList<>();
        for (int i = 0; i < newList.size(); i++) {
            U newElement = function.apply(newList.get(i));
            mappedList.add(newElement);
        }
        return new ImmutableList<>(mappedList);
    }

    public ImmutableList<T> limit(long n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("limit size < 0");
        } else {
            List<T> newList = new ArrayList<>(list);
            List<T> result = new ArrayList<>();
            if (n > newList.size()) {
                n = newList.size();
            }
            for (int i = 0; i < n; i++) {
                result.add(newList.get(i));
            }
            return new ImmutableList<>(result);
        }
    }
    
    @SuppressWarnings("unchecked")
    public ImmutableList<T> sorted() throws IllegalStateException {
        List<T> newList = new ArrayList<>(list);
        if (newList.size() == 0 || newList.get(0) instanceof Comparable) {
            T[] arr = (T[]) newList.toArray();
            Arrays.sort(arr);
            return new ImmutableList<>(arr);
        } else {
            throw new IllegalStateException("List elements do no implement Comparable");
        }
    }

    public ImmutableList<T> sorted(Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        } else {
            List<T> newList = new ArrayList<>(list);
            newList.sort(comparator);
            return new ImmutableList<>(newList);
        }
    }

    public Object[] toArray() {
        List<T> newList = new ArrayList<>(list);
        return newList.toArray();
    }

    public <U> U[] toArray(U[] arr) {
        if (arr == null) {
            throw new NullPointerException("Input array cannot be null");
        } else {
            try {
                List<T> newList = new ArrayList<>(list);
                return newList.toArray(arr);
            } catch (ArrayStoreException e) {
                throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
            }
        }
    }
}
