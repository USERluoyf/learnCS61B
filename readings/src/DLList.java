public class DLList {
    public static class IntNode {
        public int item;
        public IntNode next;
        public IntNode prev;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    //初始化
    private final IntNode sentFront;//添加sentFront node
    private final IntNode sentBack;
    private IntNode first;
    private IntNode last;
    private int size;
    public DLList(int x) {
        first = new IntNode(x,null);
        last = first;
        sentFront = new IntNode(0,first);//添加sentFront node
        sentBack = new IntNode(0,null);//添加sentBack node
        last.next = sentBack;
        sentBack.prev = last;
        size = 1;//记录size
    }

    //创建空DLList
    public DLList() {
        first = null;
        sentFront = new IntNode(0,null);//添加sentFront node
        sentBack = new IntNode(0,null);//添加sentBack node
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
        last = sentBack.prev;//添加sentFront后修改
        last.next = sentBack;
        size = 0;
    }

    //添加首个元素
    public void addFirst(int x) {
        first = new IntNode(x, first);
        first.next.prev = first;
        size++;//记录size
    }

    //添加剩下的元素
    public void addLast(int x) {
        // IntNode p = sentFront;
        // while (p.next != null) {
        //     p = p.next;
        // }
        // p.next = new IntNode(x,null);
        last.next = new IntNode(x,null);
        last.next.prev = last;
        last = last.next;

        size++;
    }

    //返回第一个元素
    public int getFirst() {
        //return first.item
        return sentFront.next.item;//添加sentFront后修改
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
        DLList list = new DLList(15);
        DLList list2 = new DLList();
        list2.addLast(10);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(16);
        System.out.println(list2.getFirst());
        System.out.println(list.first.next.next.prev.item);
        System.out.println(list.size());
    }
}


