public class SLList {
    public static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    //初始化
    private final IntNode sentinel;//添加sentinel node
    private IntNode first;
    private int size;
    public SLList(int x) {
        first = new IntNode(x,null);
        sentinel = new IntNode(0,first);//添加sentinel node
        size = 1;//记录size
    }

    //创建空SLList
    public SLList() {
        first = null;
        sentinel = new IntNode(0,null);//添加sentinel node
        size = 0;
    }

    //添加首个元素
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size++;//记录size
    }

    //添加剩下的元素
    public void addLast(int x) {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x,null);
        size++;
    }

    //返回第一个元素
    public int getFirst() {
        //return first.item
        return sentinel.next.item;//添加sentinel后修改
    }

    //返回size
    /*
    private static int size(IntNode x) {
        if (x.next == null) {
            return 1;
        }
        return 1 + size(x.next);
    }
     */

    public int size() {
        return size;
    }
    public static void main(String[] args) {
        SLList list = new SLList(15);
        SLList list2 = new SLList();
        list2.addLast(10);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(16);
        System.out.println(list2.getFirst());
        System.out.println(list.first.next.next.next.next.item);
        System.out.println(list.size());
    }
}


