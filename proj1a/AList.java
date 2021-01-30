public class AList<Item> {
    private Item[] items;
    private int size;

    public AList() {
        items = (Item[]) new Object[100]; //an array
        size = 0;
    }

     /** Resizes the underlying array to the target capacity. */
    private void resize(int capcity) {
        Item[] a = (Item[]) new Object[capcity];
        //System.arraycopy(src, srcPos, dest, destPos, length);
        System.arraycopy(items, 0, a, 0, size);//a[0:size-1] = item[0:size-1]
        items = a;
    }


    public void addLast(Item x) {
        if(size==items.length) {
            resize(size+1);
            //resize(size+100);//speedtest
        }
        items[size] = x;
        size+=1;
    }

    public Item getLast() {
        return items[size-1];
    }

    public Item get(int i) {
        return items[i];
    }

    public Item removeLast() {
        Item x = getLast();
        items[size-1] = null;//can be skipped
        size-=1;
        return x;
    }
}