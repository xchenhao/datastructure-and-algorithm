package datastructure.set;

import com.tools.file.FileInfo;
import com.tools.file.Files;
import datastructure.linear.TimeTool;
import org.junit.Test;

public class SetTest {

    @Test
    public void test() {
        Set<Integer> set = new ListSet<>();
        set.add(10);
        set.add(11);
        set.add(11);
        set.add(12);
        set.add(10);

        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    @Test
    public void tes2() {
        Set<Integer> set = new TreeSet<>();
        set.add(12);
        set.add(10);
        set.add(11);
        set.add(11);
        set.add(10);

        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    private static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }

        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }

        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }

    @Test
    public void test3() {
        FileInfo filInfo = Files.read("C:\\Program Files\\Java\\jdk1.8.0_291\\src\\java\\util", new String[]{"java"});
        System.out.println("文件数量：" + filInfo.getFiles());
        System.out.println("代码数量：" + filInfo.getLines());

        String[] words = filInfo.words();
        System.out.println("单词数量：" + words.length);

        TimeTool.check("ListSet", new TimeTool.Task() {
            @Override
            public void execute() {
                testSet(new ListSet<>(), words);
            }
        });

        TimeTool.check("ListSet", new TimeTool.Task() {
            @Override
            public void execute() {
                testSet(new TreeSet<>(), words);
            }
        });
    }

}
