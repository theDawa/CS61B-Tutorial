public class LinkedListDeque{
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
            System.out.println(size);
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private IntNode sentback;
    private IntNode p;
    private IntNode i;
    private int size;
    private IntNode j;
    /** Creates an empty SLList. */
    public LinkedListDeque() {
        sentinel = new IntNode(63, null);
        sentback = new IntNode(63, null);
        sentback = sentinel;
        p = sentback;
        i = sentinel;
        j = sentinel;
        size = 0;
    }

    public LinkedListDeque(int x) {
        sentinel = new IntNode(63, null);
        sentback = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        sentback.next = sentinel.next;
        p = sentback;
        i = sentinel;
        j = sentinel;
        size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        p = p.next;
        i = i.next;
        p.next = i;
        size = size + 1;

    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    public int getLast() {
        return sentback.next.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size = size + 1;
        sentback.next = new IntNode(x, sentback.next);
        p = p.next;
        i = i.next;
        i.next = p;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Returns the ith element of the list. */
    public int getRecursive(int index){

        if(index == 0){
            return j.next.item;
        }
        j = j.next;
        return getRecursive(index - 1);
    }

    public static void main(String[] args) {
        LinkedListDeque L = new LinkedListDeque(15);
        L.addFirst(10);
        L.addLast(15);
        int a = L.getRecursive(1);
    }

}

