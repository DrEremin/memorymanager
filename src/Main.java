import impl.memory.*;

public class Main {
    public static void main(String[] args) {

        Memory memory = new Memory(10);
        for (int i = 0; i < memory.getSizeSharedMemory(); i++) {
            System.out.println(memory.getMemoryLocation(i));
        }
    }
}
