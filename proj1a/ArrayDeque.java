public class ArrayDeque<T> { //T is generics
    /*
    For arrays of length 16 or more, 
    your usage factor should always be at least 25%. 
    For smaller arrays, your usage factor can be arbitrarily low.
    */
    private T[] items;

    private int size;

    private int nextFirst;
    private int nextLast;

    private final int INITIAL_CAPACITY = 8;//The starting size of your array should be 8.

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = INITIAL_CAPACITY-1;
        nextLast = 0;
    }

    private int onePlus(int x) {
        return (x + 1)%items.length;
    }
    private int oneMinus(int x) {
        return (x - 1 + items.length)%items.length;
    }
    
    public void resize() {
        if(size==items.length) {
            resize(items.length * 2);
        }
        if(items.length > 8 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    public void resize(int NEW_CAPCITY) {
        T[] temp = items;
        items = (T[]) new Object[NEW_CAPCITY];

        int curFirst = onePlus(nextFirst);
        int curLast = oneMinus(nextLast);

        for(int i=curFirst;i!=curLast;i=(i+1)%temp.length){
            items[nextLast] = temp[i];
            nextLast = onePlus(nextLast);
        }
        items[nextLast] = temp[curLast];
        nextLast = onePlus(nextLast);
    }

    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = oneMinus(nextFirst);
        size+=1;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size+=1;
    }
    public boolean isEmpty() {
        if(size!=0)return false;
        return true;
    }

    public int size() {
        return size;
    }   
    public void printDeque() {
        int curFirst = onePlus(nextFirst);
        for(int i=curFirst;i!=nextLast;i = onePlus(i)) {
            System.out.print(items[i]+" ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        if(isEmpty())return null;
        resize();
        nextFirst = onePlus(nextFirst);
        T ret = items[nextFirst];
        items[nextFirst] = null;
        size-=1;
        return ret;
    }

    public T removeLast() {
        if(isEmpty())return null;
        resize();
        nextLast = oneMinus(nextLast);
        T ret = items[nextLast];
        size-=1;
        return ret;
    }

    public T get(int index) {
        if(index>=size)return null;
        index = (index+1+nextFirst)%items.length;
        return items[index];
    }
}