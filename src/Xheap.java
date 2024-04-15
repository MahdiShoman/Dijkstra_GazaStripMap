class Xheap<T extends Comparable<T>> {
    private Object[] array;
    private int heapSize;

    public Xheap(int size) {
        array = new Object[size];
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    public void insert(T data) {
        if (!isFull()) {
            array[heapSize + 1] = data;
            heapSize++;
            int i = getParent(heapSize);
            int c = heapSize;
            while (i > 0 && ((Comparable<T>) array[i]).compareTo((T) array[c]) > 0) {
                T tmp = (T) array[c];
                array[c] = array[i];
                array[i] = tmp;
                c = i;
                i = getParent(c);
            }
        } else {
            System.out.println("Full");
        }
    }

    public T removeMin() {
        if (isEmpty()) {
            return null;
        } else {
            T min = (T) array[1];
            int p = 1;
            int c = getMinChild(1);

            while (c > 0) {
                T tmp = (T) array[c];
                array[c] = array[p];
                array[p] = tmp;

                p = c;
                c = getMinChild(c);
            }

            T tmp = (T) array[heapSize];
            array[heapSize] = array[p];
            array[p] = tmp;

            heapSize--;
            return min;
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == array.length - 1;
    }

    private int getParent(int i) {
        if (i <= heapSize) return i / 2;
        return -1;
    }

    private int getLeftChild(int i) {
        if (i * 2 <= heapSize) return i * 2;
        return -1;
    }

    private int getRightChild(int i) {
        if (i * 2 + 1 <= heapSize) return i * 2 + 1;
        return -1;
    }

    private int getMinChild(int p) {
        int l = getLeftChild(p);
        int r = getRightChild(p);
        if (r < 0) return l;
        if (l < 0) return r;
        if (((Comparable<T>) array[r]).compareTo((T) array[l]) < 0) l = r;

        return l;
    }

    public void printHeap() {
        for (int i = 1; i <= heapSize; i++) {
            System.out.println(array[i]);
        }
    }
}
