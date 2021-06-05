import impl.structdata.Stack;

public class MainStack {
    public static void main(String[] args) {

        Stack<Integer> stackInt = new Stack<>();
        Stack<String> stackString = new Stack<>("First string");
        System.out.println("Check of stack at emptiness:");
        System.out.println("stackInt " + stackString.isEmpty());
        System.out.println("stackString " + stackString.isEmpty());
        System.out.println("impl.Stack size determination:");
        System.out.println("stackInt " + stackInt.getSize());
        System.out.println("stackString " + stackString.getSize());
        System.out.println("A content of stacks:");
        System.out.println("stackInt = " + stackInt);
        System.out.println("stackString = " + stackString);
        stackInt.push(1);
        stackInt.push(2);
        stackInt.push(3);
        stackString.push("Second string");
        stackString.push("Third string");
        stackString.push(null);
        System.out.println("Were added elements to stacks:");
        System.out.println("stackInt = " + stackInt);
        System.out.println("stackString = " + stackString);
        System.out.println("Calls of method pop() for both stacks:");
        for (int i = stackInt.getSize() + 1; i >= 0; i--) {
            System.out.print("[" + stackInt.pop() + "] ");
        }
        System.out.println();
        for (int i = stackString.getSize() + 1; i >= 0; i--) {
            System.out.print("[" + stackString.pop() + "] ");
            stackInt.push(i);
        }
        System.out.println("\nWere added elements to stackInt:");
        System.out.println("stackInt = " + stackInt);
        stackInt.clear();
        System.out.println("The stackInt was cleared:");
        System.out.println("stackInt = " + stackInt);
    }
}
