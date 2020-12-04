package ua.edu.ucu.iterators;

import ua.edu.ucu.linked.structures.Node;


public class LinkedListIterator<T> implements Iterator<T> {
    private Node<T> head;
    private Node<T> currNode;

    public LinkedListIterator(Node<T> initHead) {
        currNode = initHead;
        head = initHead;

    }

    public boolean hasMore() {
        return currNode.getItem() != null;
    }

    public void restart() {
        currNode = head;
    }

    public T getNext() {
        T item = currNode.getItem();
        currNode = currNode.getNext();
        return item;
    }

}
