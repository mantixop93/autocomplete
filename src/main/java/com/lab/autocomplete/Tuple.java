package com.lab.autocomplete;

/**
 * Created by Mantixop on 1/18/16.
 */
public class Tuple <T> {
    private String key;
    private T value;

    public Tuple(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {return key;}

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;

        Tuple<?> tuple = (Tuple<?>) o;

        if (key != null ? !key.equals(tuple.key) : tuple.key != null) return false;
        return !(value != null ? !value.equals(tuple.value) : tuple.value != null);

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
