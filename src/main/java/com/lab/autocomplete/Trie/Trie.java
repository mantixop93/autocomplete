package com.lab.autocomplete.trie;

public interface  Trie<T> {

    void add(Tuple<T> tuple);

    boolean contains(String word);

    boolean delete(String word);

    Iterable<String> words();

    Iterable<String> wordsWithPrefix(String pref);

    int size();

}
