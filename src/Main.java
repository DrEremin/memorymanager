import impl.structdata.DuoLinkedList;

public class Main {
    public static void main(String[] args) {
        DuoLinkedList<String> list1 = new DuoLinkedList<>();
        DuoLinkedList<Integer> list2 = new DuoLinkedList<>(1);
        System.out.println("getSizeList():");
        System.out.println("size of list1 = " + list1.getSizeList());
        System.out.println("size of list2 = " + list2.getSizeList());
        list1.pushBack("first");
        list1.pushBack("second");
        System.out.println("pushBack():");
        System.out.println("list1 = " + list1);
        list2.pushFront(2);
        list2.pushFront(3);
        System.out.println("pushFront():");
        System.out.println("list2 = " + list2);
        list1.pushIndex(0, null);
        System.out.println("pushIndex():");
        System.out.println("list1 = " + list1);
        System.out.println("getDataAtIndex(0):");
        System.out.println(list1.getDataAtIndex(0).orElse("null"));
        list1.setDataAtIndex(0, "fourth");
        System.out.println("setDataAtIndex(0):");
        System.out.println("list1 = " + list1);
        System.out.println("popFront(), popBack():");
        list1.popFront();
        list2.popBack();
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        list2.pushBack(null);
        for (int i = 1; i < 10; i++) {
            list2.pushFront(i);
        }
        System.out.println("list2 = " + list2);
        list2.popIndex(1);
        System.out.println("popIndex(1):");
        System.out.println("list2 = " + list2);
        Integer[] array = new Integer[]{1, null, 3, 4, 5, 6};
        System.out.println("DuoLinkedList<>(array), DuoLinkedList<>(DuoLinkedList):");
        System.out.println("list3 = " + new DuoLinkedList<>(array));
        System.out.println("list4 = " + new DuoLinkedList<>(list1));
    }
}
