package deque;

import java.util.*;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }
        if (size() == 1) {
            return get(0);
        }
        T ret = get(0);
        for (int i = 0; i < size(); i++) {
            T tmp = get(i);
            if (comp.compare(tmp, ret) > 0) {
                ret = tmp;
            }
        }
        return ret;
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        if (size() == 1) {
            return get(0);
        }
        T ret = get(0);
        for (int i = 0; i < size(); i++) {
            T tmp = get(i);
            if (c.compare(tmp, ret) > 0) {
                ret = tmp;
            }
        }
        return ret;
    }
}
