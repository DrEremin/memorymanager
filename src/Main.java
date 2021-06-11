import impl.memory.*;
import impl.memorymanager.MemoryManager;

public class Main {
    public static void main(String[] args) {

        Memory memory = new Memory(20);
        MemoryManager memoryManager =
                new MemoryManager(memory, 7);

        System.out.println("Contents of the cache: " + memoryManager.getLruCache());
        System.out.println("memory:");
        System.out.println(memory);

        System.out.println("Call the memoryManager.malloc(10):");
        memoryManager.malloc(10);

        System.out.println("Contents of the cache: " + memoryManager.getLruCache());
        System.out.println("memory:");
        System.out.println(memory);

        System.out.println("Call the memoryManager.malloc(5):");
        memoryManager.malloc(5);

        System.out.println("Contents of the cache: " + memoryManager.getLruCache());
        System.out.println("memory:");
        System.out.println(memory);

        System.out.println("Call the memoryManager.free(10):");
        memoryManager.free(10);

        System.out.println("Contents of the cache: " + memoryManager.getLruCache());
        System.out.println("memory:");
        System.out.println(memory);

        System.out.println("LruCache.get(10): " + memoryManager.getLruCache().get(10));
        System.out.println("LruCache.get(8): "
                + memoryManager.getLruCache().get(8)
                + " default value");
        System.out.println("LruCache.contains(10): " + memoryManager.getLruCache().contains(10));
        System.out.println("LruCache.contains(7): " + memoryManager.getLruCache().contains(7));
    }
}
