package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie initTrie) {
        trie = initTrie;
    }

    public int load(String... strings) {
        int errors = 0;
        for (String string: strings) {
            String[] words = string.split(" ");
            for (String w: words) {
                if (w.length() > 2) {
                    try {
                        trie.add(new Tuple(w, w.length()));
                    }
                    catch (IndexOutOfBoundsException e) {
                        errors += 1;
                    }
                }
            }
        }
        return errors;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            return null;
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            return null;
        }
        int maxLength = k + pref.length() - 1;
        ArrayList<String> filtered = new ArrayList();
        for (String string: trie.wordsWithPrefix(pref)) {
            if (string.length() <= maxLength) {
                filtered.add(string);
            }
        }
        return filtered;
    }

    public int size() {
        return trie.size();
    }
}
