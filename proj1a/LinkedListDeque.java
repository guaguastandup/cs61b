public class LinkedListDeque <T>{ //Do not maintain references to items that are no longer in the deque
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
    
    private Node sentinel;//empty in fact,not included in size
    private int size;
    
    public LinkedListDeque() {
        //sentinel = new Node(null,null,null);
        sentinel = new Node(null,null,null);
        sentinel.pre = sentinel;
        sentinel.nxt = sentinel;
        size = 0;
    }

    // public LinkedListDeque(T item) {
    //     sentinel = new Node((T)"null",null,null);
    //     sentinel.nxt = new Node(item,sentinel,sentinel);
    //     sentinel.pre = sentinel.nxt;//the new node
    //     size = 1;
    // }

    public T getRecursive(int index) {//same as get,but uses recursion
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

    public void addFirst(T item) {
        sentinel.nxt = new Node(item,sentinel,sentinel.nxt);
        sentinel.nxt.nxt.pre = sentinel.nxt;
        size+=1;
    }
    public void addLast(T item) {
        sentinel.pre = new Node(item,sentinel.pre,sentinel);
        sentinel.pre.pre.nxt = sentinel.pre;
        size+=1;
    }

    public boolean isEmpty() {
        if(size==0)return true;
        return false;
    }
    public void printDeque() {
        Node ptr = sentinel;
        while(ptr.nxt!=sentinel) {
            ptr = ptr.nxt;
            System.out.print(ptr.item+" ");
        }
    }

    public T removeFirst() {
        if(size==0) {
            return null;
        }
        T ret = sentinel.nxt.item;
        sentinel.nxt = sentinel.nxt.nxt;
        size-=1;
        return ret;
    }
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

    public int size() { //must take constant time.
        return size;
    }
}
