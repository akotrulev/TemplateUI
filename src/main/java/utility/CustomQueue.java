package utility;

import java.util.LinkedList;

public class CustomQueue<T> extends LinkedList<T> {
    public T pullElementAndAddItAtTheEnd() {
        T element = poll();
        add(element);
        return element;
    }
}
