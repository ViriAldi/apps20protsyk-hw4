package ua.edu.ucu.mutable;

import ua.edu.ucu.immutable.ImmutableLinkedList;


public class Queue<T> {
    private ImmutableLinkedList<T> items;

    public Queue() {
        items = new ImmutableLinkedList();
    }

    public T peek() {
        return items.getFirst();
    }

    public T dequeue() {
        T item = peek();
        items = items.removeFirst();
        return item;

    }

    public void enqueue(T item) {
        items = items.addLast(item);
    }

    public int size() {
        return items.size();
    }

}
