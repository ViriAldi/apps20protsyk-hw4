package ua.edu.ucu.linked.structures;

public class Node<T> {
    private T item;
    private Node<T> next;
    private Node<T> previous;

    public void setNext(Node node) {
        next = node;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setPrev(Node<T> node) {
        previous = node;
    }

    public Node<T> getPrev() {
        return previous;
    }

    public T getItem() {
        return item;
    }

    public void setItem(Object newItem) {
        item = (T) newItem;
    }
}
