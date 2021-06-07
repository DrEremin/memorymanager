import impl.memory.*;
import impl.memorymanager.LruCache;
import impl.memorymanager.MemoryManager;

public class Main {
    public static void main(String[] args) {

        Memory memory = new Memory(20);
        MemoryManager memoryManager =
                new MemoryManager(memory, 6);

        System.out.println(memory);
        System.out.println("memoryManager.malloc(10):");
        memoryManager.malloc(10);
        System.out.println("cache: " + memoryManager.cashToString());

        System.out.println(memory);
        System.out.println("memoryManager.malloc(5):");
        memoryManager.malloc(5);
        System.out.println("cache: " + memoryManager.cashToString());
        System.out.println(memory);

        System.out.println("memoryManager.free(10):");
        memoryManager.free(10);
        System.out.println("cache: " + memoryManager.cashToString());
        System.out.println(memory);






        /*String[] strings = new String[50];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(i);
            System.out.print(strings[i] + " ");
        }
        System.out.println();
        LruCache<String> cache = new LruCache<>(8);
        for (int i = 0, index = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++, index++) {
                cache.put(strings[index]);
                System.out.print(index + " ");
            }
            index += 3;
        }
        System.out.println();
        System.out.println(cache);
        for (int i = 0, index = 34; i < 3; i++, index++) {
            cache.put(strings[index]);
        }
        System.out.println(cache);
        System.out.println(cache.getLast());*/
    }
}
