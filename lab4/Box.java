public class Box<T> {
    
    private static final Box<? extends Object> EMPTY_BOX = new Box<>(null);
    private final T t;

    private Box(T t) {
        this.t = t;
    }

    public static <T> Box<T> of(T t) {
        if (t == null) {
            return null;
        } else {
            return new Box<>(t);
        }
    }

    public static <T> Box<T> ofNullable(T t) {
        if (t == null) {
            return Box.empty();
        } else {
            return new Box<>(t);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Box<T> empty() {
        return (Box<T>) Box.EMPTY_BOX;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == null) {
            return obj == Box.empty();
        } else if (this == obj) {
            return true;
        } else if (obj instanceof Box) {
            Box<T> otherBox = (Box<T>) obj;
            return t.equals(otherBox.t);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (equals(Box.empty())) {
            return "[]";
        } else {
            return "[" + t.toString() + "]";
        }
    }

    public boolean isPresent() {
        if (equals(Box.empty())) {
            return false;
        } else {
            return true;
        }
    }

    public Box<T> filter(BooleanCondition<? super T> bc) {
        if (equals(Box.empty())) {
            return this;
        } else {
            if (bc.test(t)) {
                return this;
            } else {
                return Box.empty();
            }
        }
    }

    public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
        if (equals(Box.empty())) {
            return Box.empty();
        } else {
            U newT = transformer.transform(t);
            return Box.ofNullable(newT);
        }
    }
}
