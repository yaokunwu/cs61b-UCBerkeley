
public class LinkedListDeque<T> implements Deque<T> {
    private ItemList sentinel;
    private int size = 0;

    private class ItemList {
        T item;
        ItemList next;
        ItemList before;


        private ItemList(T data, ItemList before, ItemList next) {
            this.item = data;
            this.next = next;
            this.before = before;
        }

    }

    public LinkedListDeque() {
        sentinel = new ItemList(null, null, null);
        sentinel.before = sentinel;
        sentinel.next = sentinel;

    }

    @Override
    public void addFirst(T item) {
        ItemList added = new ItemList(item, sentinel, sentinel.next);
        sentinel.next.before = added;
        sentinel.next = added;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        ItemList added = new ItemList(item, sentinel.before, sentinel);
        sentinel.before.next = added;
        sentinel.before = added;
        size += 1;
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
        ItemList temp = this.sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next.next.before = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removed = sentinel.before.item;
        sentinel.before = sentinel.before.before;
        sentinel.before.next = sentinel;
        size -= 1;
        return removed;
    }

    @Override
    public T get(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        }
        int i = 0;
        ItemList temp = sentinel;
        while (i < index + 1) {
            temp = temp.next;
            i++;
        }
        T result = temp.item;
        return result;
    }

    public T getRecursive(int index) {
        T result = getRecursive(index, sentinel.next);
        return result;
    }

    private T getRecursive(int index, ItemList curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }


//    public static void main(String[] args) {
//        LinkedListDeque<Integer> add = new LinkedListDeque<>();
//        add.addFirst(10);
//        add.addLast(50);
        //System.out.println(add.sentinel.before.item);
        //System.out.println(add.isEmpty());
        //System.out.println(add.size());
        //add.printDeque();
        //int a=add.removeLast();
//        int a =add.getRecursive(2);
//        System.out.println(a);
//    }

}
