package co.inventorsoft.academy;

import java.util.*;
import java.util.function.Function;

public class Range<T extends Comparable<T>> implements Set<T> {
    private final T fromInclusive;
    private final T toInclusive;
    private final Function<T, T> incrementFunction;

    public Range(T fromInclusive, T toInclusive, Function<T, T> incrementFunction) {
        if (fromInclusive.compareTo(toInclusive) > 0) {
            throw new IllegalArgumentException("Invalid range: fromInclusive > toInclusive");
        }
        this.fromInclusive = fromInclusive;
        this.toInclusive = toInclusive;
        this.incrementFunction = incrementFunction;
    }

    public static <T extends Comparable<T>> Range<T> of(T fromInclusive, T toInclusive, Function<T, T> incrementFunction) {
        return new Range<>(fromInclusive, toInclusive, incrementFunction);
    }

    public static Range<Integer> of(int fromInclusive, int toInclusive) {
        return new Range<>(fromInclusive, toInclusive, i -> i + 1);
    }

    public static Range<Float> of(float fromInclusive, float toInclusive) {
        return new Range<>(fromInclusive, toInclusive, f -> f + 1.0f);
    }

    public static Range<Double> of(double fromInclusive, double toInclusive) {
        return new Range<>(fromInclusive, toInclusive, d -> d + 1.0);
    }

    public int size() {
        // Size calculation logic remains the same
        if (fromInclusive instanceof Integer) {
            return ((Integer) toInclusive - (Integer) fromInclusive) + 1;
        } else if (fromInclusive instanceof Double) {
            double from = (Double) fromInclusive;
            double to = (Double) toInclusive;
            return (int) Math.ceil(to - from) + 1;
        } else if (fromInclusive instanceof Float) {
            float from = (Float) fromInclusive;
            float to = (Float) toInclusive;
            return (int) Math.ceil(to - from) + 1;
        } else if (fromInclusive instanceof Character) {
            char from = (Character) fromInclusive;
            char to = (Character) toInclusive;
            return to - from + 1;
        } else {
            throw new IllegalArgumentException("Size calculation is not supported for this data type.");
        }
    }

    public boolean isEmpty() {
        return fromInclusive.compareTo(toInclusive) > 0 || toInclusive.compareTo(fromInclusive) == 0;
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Comparable) {
            @SuppressWarnings("unchecked")
            T element = (T) o;
            return element.compareTo(fromInclusive) >= 0 && element.compareTo(toInclusive) <= 0;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T current = fromInclusive;

            @Override
            public boolean hasNext() {
                return current.compareTo(toInclusive) <= 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the range.");
                }

                T next = current;
                current = incrementFunction.apply(current); // Use the increment function
                return next;
            }
        };
    }

    public Object[] toArray() {
        return null;
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }
}
