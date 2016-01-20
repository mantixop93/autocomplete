package com.lab.autocomplete;

import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Mantixop on 1/18/16.
 */

public class RWayTrie<T> implements Trie<T> {

    private static final int R = 26;
    private Node root;

    private static final class Node<T> {
        T value;
        Object[] next = new Object[R];
    }

    public void add(final Tuple<T> tuple) {
        root = add(root, tuple, 0);
    }

    private Node add(final Node node, final Tuple<T> tuple, final int depth) {
        Node currentNode = node;
        if (currentNode == null) {
            currentNode = new Node();
        }
        if (depth == tuple.getKey().length()) {
            currentNode.value = tuple.getValue();
            return currentNode;
        }
        int index = tuple.getKey().charAt(depth) - 'a';
        currentNode.next[index] = add((Node) currentNode.next[index],
                tuple, depth + 1);
        return currentNode;
    }

    public boolean contains(final String word) {
        Node node = contains(root, word, 0);
        if ((node == null) || (node.value == null)) {
            return false;
        }
        return true;
    }

    private Node contains(final Node node, final String key, final int depth) {
        if (node == null) {
            return null;
        }
        if (depth == key.length()) {
            return node;
        }
        int index = key.charAt(depth) - 'a';
        return contains((Node) node.next[index], key, depth + 1);
    }

    public boolean delete(final String word) {
        if (contains(word)) {
            root = delete(root, word, 0);
            return true;
        }
        return false;
    }

    private Node delete(final Node node, final String key, final int depth) {
        if (node == null) {
            return null;
        }
        if (depth == key.length()) {
            node.value = null;
        } else {
            int c = key.charAt(depth) - 'a';
            node.next[c] = delete((Node) node.next[c], key, depth + 1);
        }
        if (node.value != null) {
            return node;
        }
        for (char c = 0; c < R; c++) {
            if (node.next[c] != null) {
                return node;
            }
        }
        return null;
    }

    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    public Iterable<String> wordsWithPrefix(final String pref) {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return new TrieIterator(pref);
            }
        };
    }

    public int size() {
        int size = 0;
        Iterator iterator = words().iterator();
        while (iterator.hasNext()) {
            size++;
            iterator.next();
        }
        return size;
    }

    @Override
    public String toString() {
        return words().toString();
    }

    private class TrieIterator implements Iterator {
        private Queue<Tuple<Node>> queue = new LinkedList<Tuple<Node>>();
        private Tuple<Node> nextNode;

        TrieIterator(final String pref) {
            Node iteratorRoot = contains(root, pref, 0);
            if (iteratorRoot != null) {
                queue.add(new Tuple<Node>(pref, iteratorRoot));
                nextNode = skipToNextNode();
            }
        }

        public boolean hasNext() {
            return nextNode != null ? true : false;
        }

        private Tuple<Node> skipToNextNode() {
            Tuple<Node> tuple = queue.poll();
            while (tuple != null) {
                for (int i = 0; i < R; i++) {
                    Node nextChild = (Node) tuple.getValue().next[i];
                    if (nextChild != null) {
                            queue.add(new Tuple<Node>(tuple.getKey() + (char) (i + 'a'), nextChild));
                    }
                }
                if (tuple.getValue().value != null) {
                    return tuple;
                }
                tuple = queue.poll();
            }
            return null;
        }

        public Object next() {
            if (nextNode != null) {
                String result = nextNode.getKey();
                nextNode = skipToNextNode();
                return result;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
