package utility;

import java.util.LinkedList;

public class CustomQueue<T> extends LinkedList<T> {
    public T pullAndReturnToEnd() {
        T element = poll();
        add(element);
        return element;
    }
}
