import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreeMapTest {

    private TreeMap<Integer, String> test1;
    private TreeMap<String, Double> test2;

    @BeforeEach
    void setUp() {
        test1 = new TreeMap<Integer, String>();
        test2 = new TreeMap<String, Double>();

        test1.put(7, "hello");
        test1.put(13, "world");
        test1.put(2, "green");
        test1.put(9, "candle");
        test1.put(-1, "talk");
        test1.put(10, "lucky");
        test1.put(4, "numbers");
        test1.put(21, "blue");
        test1.put(8, "alright");
    }

    @Test
    void size() {
        assertEquals(9, test1.size());
        assertEquals(0, test2.size());
    }

    @Test
    void clear() {
        test1.clear();
        assertEquals(0, test1.size());

        test2.clear();
        assertEquals(0, test2.size());
    }

    @Test
    void get() {
        assertEquals("hello", test1.get(7));
        assertEquals("talk", test1.get(-1));
        assertEquals("lucky", test1.get(10));
        assertEquals(null, test1.get(6));
        assertThrows(IllegalArgumentException.class, () -> test1.get(null));

        assertEquals(null, test2.get("Wendy"));
        assertThrows(IllegalArgumentException.class, () -> test2.get(null));
    }

    @Test
    void put() {
        test1.put(6, "cloud");
        assertEquals(10, test1.size());
        assertEquals("cloud", test1.get(6));

        test1.put(7, "bye");
        assertEquals(10, test1.size());
        assertEquals("bye", test1.get(7));

        assertThrows(IllegalArgumentException.class, () -> test1.put(null, "moon"));
        assertThrows(IllegalArgumentException.class, () -> test1.put(17, null));

        test2.put("Jake", 12.87);
        test2.put("Betty", 14.99);
        test2.put("Tommy", 13.45);
        test2.put("Wendy", 12.59);
        test2.put("Amanda", 19.88);
        test2.put("Key", 20.08);
        test2.put("Elle", 20.05);
        assertEquals(7, test2.size());
        assertEquals(12.59, test2.get("Wendy"));
    }

    @Test
    void containsKey() {
        assertEquals(true, test1.containsKey(7));
        assertEquals(true, test1.containsKey(-1));
        assertEquals(true, test1.containsKey(10));
        assertEquals(false, test1.containsKey(6));

        assertEquals(false, test2.containsKey("Eric"));
        assertThrows(IllegalArgumentException.class, () -> test2.containsKey(null));
    }

    @Test
    void toKeyArray() {
        Integer[] keys1 = test1.toKeyArray(new Integer[0]);
        assertEquals(9, keys1.length);
        assertArrayEquals(new Integer[] {-1, 2, 4, 7, 8, 9, 10, 13, 21}, keys1);

        Integer[] keys2 = test1.toKeyArray(new Integer[9]);
        assertEquals(9, keys2.length);
        assertArrayEquals(new Integer[] {-1, 2, 4, 7, 8, 9, 10, 13, 21}, keys2);

        Integer[] keys3 = test1.toKeyArray(new Integer[12]);
        assertEquals(12, keys3.length);
        assertArrayEquals(new Integer[] {-1, 2, 4, 7, 8, 9, 10, 13, 21, null, null, null}, keys3);

        String[] stringKeys1 = test2.toKeyArray(new String[0]);
        assertEquals(0, stringKeys1.length);

        String[] stringKeys2 = test2.toKeyArray(new String[3]);
        assertEquals(3, stringKeys2.length);
        assertArrayEquals(new String[] {null, null, null}, stringKeys2);

        test2.put("Jake", 12.87);
        test2.put("Betty", 14.99);
        test2.put("Tommy", 13.45);
        test2.put("Wendy", 12.59);
        test2.put("Jake", 13.49);
        test2.put("Amanda", 19.88);
        String[] stringKeys3 = test2.toKeyArray(new String[5]);
        assertEquals(5, stringKeys3.length);
        assertArrayEquals(new String[] {"Amanda", "Betty", "Jake", "Tommy", "Wendy"}, stringKeys3);

        assertThrows(IllegalArgumentException.class, () -> test2.toKeyArray(null));
    }
}
