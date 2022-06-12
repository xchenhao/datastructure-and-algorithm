package datastructure.trie;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

    @Test
    public void test() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);

        Assert.assertEquals(trie.size(), 5);
        Assert.assertTrue(trie.startsWith("do"));
        Assert.assertTrue(trie.startsWith("c"));
        Assert.assertTrue(trie.startsWith("ca"));
        Assert.assertTrue(trie.startsWith("cat"));
        Assert.assertTrue(trie.startsWith("cata"));
        Assert.assertFalse(trie.startsWith("hehe"));
        Assert.assertEquals((int)trie.get("小码哥"), 5);
        Assert.assertEquals((int)trie.remove("cat"), 1);
        Assert.assertEquals((int)trie.remove("catalog"), 3);
        Assert.assertEquals((int)trie.remove("cast"), 4);
        Assert.assertEquals(trie.size(), 2);
        Assert.assertTrue(trie.startsWith("小"));
        Assert.assertTrue(trie.startsWith("do"));
        Assert.assertFalse(trie.startsWith("c"));
    }
}
