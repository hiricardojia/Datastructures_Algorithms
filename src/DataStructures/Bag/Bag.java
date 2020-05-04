package DataStructures.Bag;

import java.util.Iterator;

/**
 * @Title: 背包
 * @Description: 链表实现
 * @Author: Jia RenHao
 * @Create: 2020-05-01
 * @Version: V1.0
 */
public class Bag<Item> implements Iterable<Item> {
    private Node head;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldNode = head;
        head = new Node();
        head.item = item;
        head.next = oldNode;
    }

    @Override
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
