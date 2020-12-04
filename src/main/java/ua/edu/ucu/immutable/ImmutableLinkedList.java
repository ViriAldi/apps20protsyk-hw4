package ua.edu.ucu.immutable;


import ua.edu.ucu.iterators.LinkedListIterator;
import ua.edu.ucu.linked.structures.Node;

import java.util.Arrays;

public class ImmutableLinkedList<T> implements ImmutableList<T> {
    private final Node<T> head;
    private final Node<T> tail;
    private final LinkedListIterator<T> iterator;
    private final int size;

    public ImmutableLinkedList() {
        size = 0;
        head = new Node<>();
        tail = head;
        iterator = new LinkedListIterator<T>(head);
    }

    public ImmutableLinkedList(T[] initItems) {
        ImmutableLinkedList<T> initList = new ImmutableLinkedList<T>(
        ).addAll(initItems);
        head = initList.head;
        size = initList.size;
        tail = initList.tail;
        iterator = initList.iterator;

    }

    private ImmutableLinkedList(Node<T> initHead, Node<T> initTail, int initSize) {
        size = initSize;
        head = initHead;
        iterator = new LinkedListIterator<T>(initHead);
        tail = initTail;

    }

    private void validateIndex(int index)
            throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
    }

    private ImmutableLinkedList<T> createJoined(
            int firstEnd, int secondStart, Object[] objects
    ) {
        iterator.restart();
        int newSize = firstEnd + size - secondStart + objects.length;
        Node<T> currNode = new Node<>();
        Node<T> prevNode;
        Node<T> newHead = currNode;
        for (int i = 0; i < firstEnd; i++) {
            currNode.setItem(iterator.getNext());
            currNode.setNext(new Node<T>());
            prevNode = currNode;
            currNode = currNode.getNext();
            currNode.setPrev(prevNode);
        }

        for (Object obj: objects) {
            currNode.setItem(obj);
            currNode.setNext(new Node<T>());
            prevNode = currNode;
            currNode = currNode.getNext();
            currNode.setPrev(prevNode);
        }

        iterator.restart();

        for (int i = 0; i < secondStart; i++) {
            iterator.getNext();
        }


        while (iterator.hasMore()) {
            currNode.setItem(iterator.getNext());
            currNode.setNext(new Node<T>());
            prevNode = currNode;
            currNode = currNode.getNext();
            currNode.setPrev(prevNode);
        }

        if (newSize != 0) {
            currNode = currNode.getPrev();
        }

        return new ImmutableLinkedList<T>(newHead, currNode, newSize);
    }

    public ImmutableLinkedList<T> add(T e) {
        return createJoined(size, size, new Object[]{e});
    }

    public ImmutableLinkedList<T> add(int index, T e) {
        validateIndex(index);
        return createJoined(index, index, new Object[]{e});
    }

    public ImmutableLinkedList<T> addAll(T[] c) {
        return createJoined(size, size, c);
    }

    public ImmutableLinkedList<T> addAll(int index, T[] c) {
        validateIndex(index);
        return createJoined(index, index, c);
    }

    public T get(int index) {
        iterator.restart();
        validateIndex(index);
        for (int i = 0; i < index; i++) {
            iterator.getNext();
        }
        return iterator.getNext();
    }

    public ImmutableLinkedList<T> remove(int index) {
        validateIndex(index);
        return createJoined(index, index+1, new Object[]{});
    }

    public ImmutableLinkedList<T> set(int index, T e) {
        validateIndex(index);
        return createJoined(index, index+1, new Object[]{e});
    }

    public int indexOf(T e) {
        iterator.restart();
        for (int i = 0; i < size; i++) {
            if (iterator.getNext() == e) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public ImmutableLinkedList<T> clear() {
        return new ImmutableLinkedList<>();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        iterator.restart();
        for (int i = 0; i < size; i++) {
            array[i] = iterator.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    public ImmutableLinkedList<T> addFirst(Object e) {
        return createJoined(0, 0, new Object[] {e});
    }

    public ImmutableLinkedList<T> addLast(Object e) {
        return createJoined(size, size, new Object[]{e});
    }

    public T getLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException(
                    "Getting element from empty list"
            );
        }
        return tail.getItem();
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException(
                    "Getting element from empty list"
            );
        }
        return head.getItem();
    }

    public ImmutableLinkedList<T> removeFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException(
                    "Removing element of empty list"
            );
        }
        return createJoined(0, 1, new Object[]{});
    }

    public ImmutableLinkedList<T> removeLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException(
                    "Removing element of empty list"
            );
        }
        return createJoined(size - 1, size, new Object[]{});
    }
}
