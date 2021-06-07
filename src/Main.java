import impl.memory.*;
import impl.memorymanager.LruCache;
import impl.memorymanager.MemoryManager;

public class Main {
    public static void main(String[] args) {

        Memory memory = new Memory(10);
        MemoryManager memoryManager = new MemoryManager(memory);
        for (int i = 0; i < memory.getSizeSharedMemory(); i++) {
            System.out.println(memory.getMemoryLocation(i));
        }

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
