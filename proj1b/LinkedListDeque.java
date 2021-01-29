public class LinkedListDeque <T> implements Deque<T>{ 
    private class Node {
        public T item;
        public Node pre;
        public Node nxt;
        public Node(T item,Node pre,Node nxt) {
            this.item = item;
            this.pre = pre;
            this.nxt = nxt;
        }
    }
    
    private Node sentinel;
    private int size;
    
    public LinkedListDeque() {
        //sentinel = new Node(null,null,null);
        sentinel = new Node(null,null,null);
        sentinel.pre = sentinel;
        sentinel.nxt = sentinel;
        size = 0;
    }

    public T getRecursive(int index) {
        if(index>size-1){
            return null;
        }
        else return getRecursiveNext(index,sentinel.nxt).item;
    }

    private Node getRecursiveNext(int index,Node p) {//This is a helper
        if(index==0) {
            return p;
        }
        else {
            p = p.nxt;
            return getRecursiveNext(index-1,p);
        }
    }

    @Override
    public void addFirst(T item) {
        Node a = new Node(item,sentinel,sentinel.nxt);
        sentinel.nxt.pre = a;
        sentinel.nxt = a;
        size+=1;
    }

    @Override
    public void addLast(T item) {
        Node a = new Node(item,sentinel.pre,sentinel);
        sentinel.pre.nxt = a;
        sentinel.pre = a;
        size+=1;
    }

    @Override
    public boolean isEmpty() {
        if(size==0)return true;
        return false;
    }

    @Override
    public void printDeque() {
        Node ptr = sentinel;
        while(ptr.nxt!=sentinel) {
            ptr = ptr.nxt;
            System.out.print(ptr.item+" ");
        }
    }

    @Override
    public T removeFirst() {
        if(size==0) {
            return null;
        }
        T ret = sentinel.nxt.item;
        sentinel.nxt.nxt.pre = sentinel;
        sentinel.nxt = sentinel.nxt.nxt;
        size-=1;
        return ret;
    }

    @Override
    public T removeLast() {
        if(size==0) {
            return null;
        }
        T ret = sentinel.pre.item;
        sentinel.pre.pre.nxt = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size-=1;
        return ret;
    }

    @Override
    public T get(int index) { //must use iteration, not recursion.
        Node p = sentinel.nxt;
        if(index>size-1)return null;
        for(int i=0;i<size;i++) {
            if(i==index) {
                return p.item;
            }
            p = p.nxt;
        }
        return null;
    }
    @Override
    public int size() { //must take constant time.
        return size;
    }
}
