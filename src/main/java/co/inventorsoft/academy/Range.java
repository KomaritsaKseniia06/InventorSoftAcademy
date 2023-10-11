package co.inventorsoft.academy;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.lang.Object;

public class Range<T> implements Set<T> {

    private final int fromInclusive;
    private final int toInclusive;

    public Range(int fromInclusive, int toInclusive) {
        if (fromInclusive > toInclusive) {
            throw new IllegalArgumentException("Invalid range: fromInclusive > toInclusive");
        }
        this.fromInclusive = fromInclusive;
        this.toInclusive = toInclusive;
    }

    public static Range of(int fromInclusive, int toInclusive) {
        return new Range(fromInclusive, toInclusive);
    }

    public int size() {
        return toInclusive - fromInclusive + 1;
    }

    public boolean isEmpty() {
        return fromInclusive > toInclusive || toInclusive - fromInclusive == 0;
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false; // If the element is null, it can't be in the range
        }

        if (o instanceof Integer) {
            int element = (Integer) o;
            return element >= fromInclusive && element <= toInclusive;
        }

        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Range[]{new Range(fromInclusive, toInclusive)};
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
