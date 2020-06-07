
public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextfirst;
    private int nextlast;
    private T[] items;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    @Override
    public void addFirst(T item) {
        if (nextfirst == nextlast) {
            T[] newitems = (T[]) new Object[items.length * 2];
            int backLength = items.length - nextfirst - 1;
            int backPosition = newitems.length - backLength;
            System.arraycopy(items, 0, newitems, 0, nextfirst);
            System.arraycopy(items, nextfirst + 1, newitems, backPosition, backLength);
            nextfirst = backPosition - 1;
            items = newitems;
        }

        items[nextfirst] = item;
        size = size + 1;
        if (nextfirst == 0) {
            nextfirst = items.length - 1;
        } else {
            nextfirst = nextfirst - 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (nextfirst == nextlast) {
            T[] newitems = (T[]) new Object[items.length * 2];
            int backLength = items.length - nextfirst - 1;
            int backPostion = newitems.length - backLength;
            System.arraycopy(items, 0, newitems, 0, nextfirst);
            System.arraycopy(items, nextfirst + 1, newitems, backPostion, backLength);
            nextfirst = backPostion - 1;
            items = newitems;
        }

        items[nextlast] = item;
        size = size + 1;
        if (nextlast == items.length - 1) {
            nextlast = 0;
        } else {
            nextlast = nextlast + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = nextfirst;
        for (int i = 0; i < size; i++) {
            if (start == items.length - 1) {
                start = 0;
            } else {
                start = start + 1;
            }
            System.out.print(items[start] + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (nextfirst == items.length - 1) {
            nextfirst = 0;
        } else {
            nextfirst = nextfirst + 1;
        }
        T result = items[nextfirst];
        items[nextfirst] = null;
        size -= 1;

        if (size < items.length / 4 && items.length >= 16) {
            T[] newitems = (T[]) new Object[items.length / 2];
            if (nextfirst > nextlast) {
                int backLength = items.length - nextfirst - 1;
                int backPostion = newitems.length - backLength;
                System.arraycopy(items, 0, newitems, 0, nextlast);
                System.arraycopy(items, nextfirst + 1, newitems, backPostion, backLength);
                nextfirst = backPostion - 1;
            } else {
                System.arraycopy(items, nextfirst + 1, newitems, 0, nextlast - nextfirst - 1);
                nextlast = nextlast - nextfirst - 1;
                nextfirst = newitems.length - 1;

            }
            items = newitems;

        }
        return result;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextlast == 0) {
            nextlast = items.length - 1;
        } else {
            nextlast = nextlast - 1;
        }
        T result = items[nextlast];
        items[nextlast] = null;
        size -= 1;

        if (size < items.length / 4 && items.length >= 16) {
            T[] newitems = (T[]) new Object[items.length / 2];
            if (nextfirst > nextlast) {
                int backLength = items.length - nextfirst - 1;
                int backPostion = newitems.length - backLength;
                System.arraycopy(items, 0, newitems, 0, nextlast);
                System.arraycopy(items, nextfirst + 1, newitems, backPostion, backLength);
                nextfirst = backPostion - 1;
            } else {
                System.arraycopy(items, nextfirst + 1, newitems, 0, nextlast - nextfirst - 1);
                nextlast = nextlast - nextfirst - 1;
                nextfirst = newitems.length - 1;
            }
            items = newitems;

        }

        return result;
    }

    @Override
    public T get(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        }
        int backLength = items.length - nextfirst - 1;
        if (index < backLength) {
            return items[nextfirst + 1 + index];
        } else {
            return items[index - backLength];
        }

    }

//    public static void main(String[] args) {
//        ArrayDeque<String> arr = new ArrayDeque<>();
//        arr.addFirst("abc");
//        arr.addFirst("def");
//    }
}
