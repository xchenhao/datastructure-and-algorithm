package datastructure.hashmap;

import com.tools.file.FileInfo;
import com.tools.file.Files;
import datastructure.hashmap.model.Key;
import datastructure.hashmap.model.SubKey1;
import datastructure.hashmap.model.SubKey2;
import datastructure.linear.TimeTool;
import datastructure.map.Map;
import datastructure.map.TreeMap;
import org.junit.Test;
import org.junit.Assert;

public class HashMapTest {

    private void test1Map(Map<String, Integer> map, String[] words) {
        TimeTool.check(map.getClass().getName(), new TimeTool.Task() {
            @Override
            public void execute() {
                for (String word : words) {
                    Integer count = map.get(word);
                    count = count == null ? 0 : count;
                    map.put(word, count + 1);
                }
                System.out.println(map.size()); // 17188

                int count = 0;
                for (String word : words) {
                    Integer i = map.get(word);
                    count += i == null ? 0 : i;
                    map.remove(word);
                }
                Assert.assertEquals(count, words.length);
                Assert.assertEquals(map.size(), 0);
            }
        });
    }

    @Test
    public void test1() {
        String filepath = "C:\\Program Files\\Java\\jdk1.8.0_291\\src\\java\\util\\concurrent\\atomic";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

        test1Map(new TreeMap<>(), words);
        test1Map(new HashMap<>(), words);
        test1Map(new LinkedHashMap<>(), words);
    }

    @Test
    public void test2() {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        HashMap<Object, Integer> linkedHashMap = new LinkedHashMap<>();

        test2(hashMap);
        test2(linkedHashMap);
    }

    private void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Assert.assertEquals(map.size(), 20);
        Assert.assertEquals((int)(map.get(new Key(4))), 4);
        Assert.assertEquals((int)map.get(new Key(5)), 10);
        Assert.assertEquals((int)map.get(new Key(6)), 11);
        Assert.assertEquals((int)map.get(new Key(7)), 12);
        Assert.assertEquals((int)map.get(new Key(8)),  8);
    }

    @Test
    public void test3() {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        HashMap<Object, Integer> linkedHashMap = new LinkedHashMap<>();

        test3(hashMap);
        test3(linkedHashMap);
    }

    private void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Assert.assertEquals(map.size(), 5);
        Assert.assertEquals((int)map.get(null), 8);
        Assert.assertEquals((int)map.get("jack"), 6);
        Assert.assertEquals(map.get(10), null);
        Assert.assertTrue(map.get(new Object()) == null);
        Assert.assertTrue(map.containsKey(10));
        Assert.assertTrue(map.containsKey(null));
        Assert.assertTrue(map.containsValue(null));
        Assert.assertFalse(map.containsValue(1));
    }

    @Test
    public void test4() {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        HashMap<Object, Integer> linkedHashMap = new LinkedHashMap<>();

        test4(hashMap);
        test4(linkedHashMap);
    }

    private void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Assert.assertTrue(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Assert.assertTrue(map.size() == 19);
        Assert.assertTrue(map.get(new Key(1)) == 6);
        Assert.assertTrue(map.get(new Key(2)) == 7);
        Assert.assertTrue(map.get(new Key(3)) == 8);
        Assert.assertTrue(map.get(new Key(4)) == 4);
        Assert.assertTrue(map.get(new Key(5)) == null);
        Assert.assertTrue(map.get(new Key(6)) == null);
        Assert.assertTrue(map.get(new Key(7)) == null);
        Assert.assertTrue(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    @Test
    public void test5() {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        HashMap<Object, Integer> linkedHashMap = new LinkedHashMap<>();

        test5(hashMap);
        test5(linkedHashMap);
    }

    private void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Assert.assertTrue(map.get(new SubKey1(1)) == 5);
        Assert.assertTrue(map.get(new SubKey2(1)) == 5);
        Assert.assertTrue(map.size() == 20);
    }

}
