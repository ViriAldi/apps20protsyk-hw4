package ua.edu.ucu.tries;

import ua.edu.ucu.linked.structures.TreeNode;
import ua.edu.ucu.mutable.Queue;

import java.util.ArrayList;

public class RWayTrie implements Trie {
    private static int TRIE_TYPE = 26;
    private static char ASCII_CHAR = 'a';

    private TreeNode<Tuple> root;
    private int size = 0;

    public RWayTrie() {
        root = new TreeNode<>(new Tuple(null, 0), TRIE_TYPE);
    }


    public void add(Tuple t) {
        String string = t.term;
        String lastChar = string.substring(string.length() - 1);
        TreeNode<Tuple> treeNode = goTo(string, true);
        if (treeNode.getItem().weight == 0) {
            treeNode.setItem(new Tuple(lastChar, string.length()));
            size += 1;
        }
    }

    private TreeNode<Tuple> goTo(String string, boolean add) {
        TreeNode<Tuple> currNode = root;
        for (int i = 0; i < string.length(); i++) {
            if (currNode.getNext(string.charAt(i) - ASCII_CHAR) == null) {
                if (add) {
                    currNode.setNext(
                            string.charAt(i) - ASCII_CHAR,
                            new Tuple(string.substring(i, i+1), 0));
                }
                else {
                    return null;
                }
            }
            currNode = currNode.getNext(string.charAt(i) - ASCII_CHAR);
        }
        return currNode;

    }

    public boolean contains(String word) {
        TreeNode<Tuple> treeNode = goTo(word, false);
        return treeNode != null && treeNode.getItem().weight != 0;
    }

    public boolean delete(String word) {
        String lastChar = word.substring(word.length() - 1);
        TreeNode<Tuple> lastNode = goTo(word, false);
        boolean deleteResult = lastNode != null;
        if (deleteResult) {
            lastNode.setItem(new Tuple(lastChar, 0));
            size -= 1;
        }
        return deleteResult;
    }

    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    private void words(
            Queue<TreeNode<Tuple>> queue,
            String currWord, ArrayList<String> words
    ) {
        TreeNode<Tuple> currNode = queue.dequeue();
        if (currNode.getItem().weight != 0) {
            words.add(currWord);
        }
        for (int i = 0; i < TRIE_TYPE; i++) {
            if (currNode.getNext(i) != null) {
                queue.enqueue(currNode.getNext(i));
                words(
                        queue,
                        currWord + currNode.getNext(i).getItem().term,
                        words);
            }
        }
    }

    public Iterable<String> wordsWithPrefix(String s) {
        Queue<TreeNode<Tuple>> queue = new Queue<>();
        TreeNode<Tuple> currNode = goTo(s, false);
        ArrayList<String> words = new ArrayList<>();
        if (currNode == null) {
            return words;
        }
        queue.enqueue(currNode);
        words(queue, s, words);
        return words;
    }

    public int size() {
        return size;
    }

}
