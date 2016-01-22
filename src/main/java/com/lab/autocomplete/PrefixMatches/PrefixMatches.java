package com.lab.autocomplete.PrefixMatches;

import com.lab.autocomplete.Trie.RWayTrie;
import com.lab.autocomplete.Trie.Tuple;
import com.lab.autocomplete.Trie.Trie;
import java.util.Iterator;

/**
 * Created by Mantixop on 1/20/16.
 */

public class PrefixMatches {

    private Trie<Integer> trie;
    private static final int DEFAULT_PREFIX_LENGTH = 3;
    private static final int MIN_WORD_LENGTH = 2;

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
            if ((words[count].length() > 2) && !(trie.contains(words[count]))) {
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
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private String next;
                    private Iterator<String> trieIterator;
                    {
                        trieIterator = trie.wordsWithPrefix(pref).iterator();
                        next = skipToFirst();
                    }

                    public boolean hasNext() {
                        if (next != null) {
                            return true;
                        }
                        return false;
                    }

                    private String skipToFirst(){
                        String result = null;
                        while (trieIterator.hasNext()) {
                            result = trieIterator.next();
                            if (result.length() >=  MIN_WORD_LENGTH) {
                                break;
                            }
                        }
                        return result;

                    }

                    private String getNext() {
                        String nextWord = trieIterator.next();
                        if (nextWord.length() > pref.length() + k - 1) {
                            return null;
                        }
                        return nextWord;
                    }

                    public String next() {
                        String result = next;
                        next = getNext();
                        return result;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<String> wordsWithPrefix(final String pref) {
        return wordsWithPrefix(pref, DEFAULT_PREFIX_LENGTH);
    }

}
