public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int front;
    private int rear;
    private int capacity;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        this.capacity = items.length;
        size = 0;
        rear = this.capacity / 2;
        front = this.capacity / 2;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, front + 1, a, 0, size);
        items = a;
        front = front + capacity / 2;
        rear = rear + capacity / 2;
    }

    /** Inserts X into the front of the list. */
    public void addFirst(Item x){
        if (front == 0) {
            resize(size + 8);
        }
        items[front] = x;
        front = (front - 1) % this.capacity;
        rear = (rear + 1) % this.capacity;
    }
    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (rear == (this.capacity - 1)) {
            resize(this.capacity + 8);
        }

        items[rear + 1] = x;
        rear = rear + 1;
        size = size + 1;
    }

    /** Returns true if the deque is empty, false otherwise. */
    public boolean isEmpty(){
        if(front == rear){
            return true;
        }
        return false;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        for(int i = front; i <= rear; i = i + 1 ){
            System.out.print(items[i]);
            System.out.print(' ');
        }
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[rear];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        if(isEmpty()){
            return null;
        }
        return items[front + 1 + i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public Item removeFirst(){
        if(isEmpty()){
            return null;
        }
        Item x = items[front + 1];
        items[front + 1] = null;
        front = front + 1;
        size = size - 1;
        return x;
    }
    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        Item x = items[rear];
        items[rear] = null;
        rear = rear - 1;
        size = size - 1;
        return x;
    }
}
