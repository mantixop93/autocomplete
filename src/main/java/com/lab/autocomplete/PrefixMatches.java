package com.lab.autocomplete;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Mantixop on 1/20/16.
 */

public class PrefixMatches {

    private Trie<Integer> trie;
    private static final int DEFAULT_PREFIX_LENGTH = 3;

    public PrefixMatches() {
        this.trie = new RWayTrie<Integer>();
    }

    public int add(final String... strings) {
        int count = 0;
        for (String word : strings) {
            count += add(word);
        }
        return count;
    }

    private int add(final String string) {
        String[] words = string.split(" ");
        int result = 0;
        for (int count = 0; count < words.length; count++) {
            if (words[count].length() > 2) {
                trie.add(new Tuple<Integer>(words[count],
                        words[count].length()));
                result++;
            }
        }
        return result;
    }

    public boolean contains(final String word) {
        return trie.contains(word);
    }

    public boolean delete(final String word) {
        return trie.delete(word);
    }

    public int size() {
        return trie.size();
    }

    public Iterable<String> wordsWithPrefix(final String pref, final int k) {
        Queue<String> queue = new LinkedList<String>();
        if (pref.length() >= 2) {
            for (String word : trie.wordsWithPrefix(pref)) {
                if ((pref.length() + k) > word.length()) {
                    queue.add(word);
                }
            }
        }
        return queue;
    }

    public Iterable<String> wordsWithPrefix(final String pref) {
        return wordsWithPrefix(pref, DEFAULT_PREFIX_LENGTH);
    }

}
