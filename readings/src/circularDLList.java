public class circularDLList<abc> {
    public class IntNode {
        public abc item;
        public IntNode next;
        public IntNode prev;
        public IntNode(abc i, IntNode n) {
            item = i;
            next = n;
        }
    }

    //初始化
    private final IntNode sentinel;//添加sentinel node
    private IntNode first;
    private IntNode last;
    private int size;
    public circularDLList(abc x) {
        first = new IntNode(x,null);
        last = first;
        sentinel = new IntNode(x,first);//添加sentinel node
        first.prev = sentinel;
        last.next = sentinel;
        size = 1;//记录size
    }

    //创建空circularDLList
    public circularDLList() {
        first = null;
        sentinel = new IntNode(null,null);//添加sentinel node
        last = sentinel;//添加sentinel后修改
        last.next = sentinel;
        size = 0;
    }

    //添加首个元素
    public void addFirst(abc x) {
        first = new IntNode(x, first);
        first.next.prev = first;
        size++;//记录size
    }

    //添加剩下的元素
    public void addLast(abc x) {
        // IntNode p = sentinel;
        // while (p.next != null) {
        //     p = p.next;
        // }
        // p.next = new IntNode(x,null);
        last.next = new IntNode(x,null);
        last.next.prev = last;
        last = last.next;
        last.next = sentinel;
        size++;
    }

    //返回第一个元素
    public abc getFirst() {
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
        circularDLList<Integer> list = new circularDLList(15);
        circularDLList<String> list2 = new circularDLList();
        list2.addLast("hello");
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(16);
        System.out.println(list2.sentinel.next.item);
        System.out.println(list.first.next.next.next.next.next.next.item);
        System.out.println(list.size());
    }
}


