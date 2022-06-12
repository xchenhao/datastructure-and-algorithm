## 数据结构与算法

>参考
>
>https://ke.qq.com/course/385223
>https://github.com/rogertan30/Love-Leetcode
>https://github.com/szluyu99/Data_Structure_Note
>https://blog.csdn.net/weixin_43734095/article/details/104847976
>https://blog.csdn.net/weixin_43734095/article/details/104598981

---
- 斐波那契数列：`datastructure.linear.Fibonacci`
- 线性结构
  + 动态数组：`datastructure.linear.ArrayList`
  + 链表
    + 单向链表：
      * `datastructure.linear.SingleDirectionLinkedList`
      * `datastructure.linear.SingleDirectionLinkedList2` 虚拟头节点优化版本
      * 应用: `com.leetcode.linkedlist.Solution`
        - 反转链表（递归、迭代）
        - 判断链表是否有环
    + 双向链表：`datastructure.linear.DoubleDirectionLinkedList`
    + 单向循环链表：`datastructure.linear.CircleSingleDirectionLinkedList`
    + 双向循环链表：
      * `datastructure.linear.CircleDoubleDirectionLinkedList`
      * 应用：约瑟夫问题
    + 静态链表（仅做了解）
  + 栈
    + `datastructure.linear.Stack` 继承
    + `datastructure.linear.Stack2` 组合
    + 应用：浏览器的前进和后退/软件的撤销和恢复/有效的括号
  + 队列
    + `datastructure.linear.Queue`
      * 应用：用两个栈实现队列
    + 双端队列：`datastructure.linear.Deque`
    + 循环队列：`datastructure.linear.CircleQueue`
    + 循环双端队列：`datastructure.linear.CircleDeque`
- 树形结构
  + 二叉树：完全二叉树、满二叉树
    + 二叉搜索树：`datastructure.tree.BinarySearchTree`
      + 平衡二叉搜索树：`datastructure.tree.BalancedBinarySearchTree`
        * AVL 树：`datastructure.tree.AVLTree`
        * 红黑树（平衡二叉 B 树，四阶 B 树）：`datastructure.tree.RedBlackTree`
  + 多叉树：B 树
- 集合：`datastructure.tree.Set`
  + `datastructure.set.ListSet`
  + `datastructure.set.TreeSet`
- 映射：`datastructure.map.Map`
  + `datastructure.map.TreeMap`
  + `datastructure.map.TreeSet`
- 哈希表：`datastructure.hashmap.HashMap`、`datastructure.hashmap.LinkedHashMap`
- 堆：二叉堆 `datastructure.heap.BinaryHeap`
- 优先级队列：`datastructure.heap.PriorityQueue`
- 哈夫曼树（了解）
- Trie：`datastructure.trie.Trie`
