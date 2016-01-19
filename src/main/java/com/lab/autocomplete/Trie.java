package com.lab.autocomplete;

/**
 * Created by Mantixop on 1/18/16.
 */
public interface  Trie <T>{

    // Добавляет в com.lab.autocomplete.Trie пару слово - term, и его вес - weight.
    // В качестве веса используйте длину слова
    public void add(Tuple<T> tuple);

    // есть ли слово в com.lab.autocomplete.Trie
    public boolean contains(String word);

    // удаляет слово из com.lab.autocomplete.Trie
    public boolean delete(String word);

    // итератор по всем словам, обход в ширину
    public Iterable<String> words();

    // итератор по всем словам, начинающимся с pref, обход в ширину
    public Iterable<String> wordsWithPrefix(String pref);

    // к-во слов в com.lab.autocomplete.Trie
    public int size();

}
