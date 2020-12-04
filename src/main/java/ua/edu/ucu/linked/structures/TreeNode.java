package ua.edu.ucu.linked.structures;

public class TreeNode<T> {
    private T item;
    private int treeType;
    private TreeNode<T>[] next;

    public TreeNode(T initItem, int initType) {
        item = initItem;
        treeType = initType;
        next = new TreeNode[treeType];
    }

    public void setNext(int i, T item) {
        next[i] = new TreeNode(item, treeType);
    }

    public TreeNode<T> getNext(int i) throws IndexOutOfBoundsException {
        return next[i];
    }

    public T getItem() {
        return item;
    }

    public void setItem(T newItem) {
        item = newItem;
    }
}
