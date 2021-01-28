public class ArrayDeque<T> { //T is generics
    /*
    For arrays of length 16 or more, 
    your usage factor should always be at least 25%. 
    For smaller arrays, your usage factor can be arbitrarily low.
    */
    private T[] items;

    private int size;//the length of the array
    //items.length:the capacity of the array

    private int nextFirst;
    private int nextLast;

    private final int INITIAL_CAPACITY = 8;//The starting size of your array should be 8.

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }


    private int onePlus(int x) {
        return (x + 1) % items.length;
    }
    private int oneMinus(int x) {
        return (x - 1 + items.length) % items.length;
    }
    

    private void resize(int NEW_CAPACITY) {
        T[] temp = (T[]) new Object[NEW_CAPACITY];

        int curFirst = onePlus(nextFirst);

        for(int i=0;i<size;i++) {
            temp[i] = items[curFirst];
            curFirst = onePlus(curFirst);
        }
        
        items = temp;
        nextFirst = items.length-1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if(size==items.length) {
            resize(size*2);
        }
        items[nextFirst] = item;
        nextFirst = oneMinus(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if(size==items.length) {
            resize(size*2);
        }
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size++;
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
        nextFirst = onePlus(nextFirst);
        T ret = items[nextFirst];
        items[nextFirst] = null;
        size-=1;
        if(items.length>=16&&size<(items.length)/4) {
            resize(items.length/2);
        }
        return ret;
    }

    public T removeLast() {
        if(isEmpty())return null;
        nextLast = oneMinus(nextLast);
        T ret = items[nextLast];
        size-=1;
        if(items.length>=16&&size<(items.length)/4) {
            resize(items.length/2);
        }
        return ret;
    }

    public T get(int index) {
        if(index>=size)return null;
        index = (index+1+nextFirst)%items.length;
        return items[index];
    }
}