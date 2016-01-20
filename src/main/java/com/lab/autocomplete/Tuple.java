package com.lab.autocomplete;

/**
 * Created by Mantixop on 1/18/16.
 */
public class Tuple<T> {
    private String key;
    private T value;

    public Tuple(final String key,final  T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple)) {
            return false;
        }

        Tuple<?> tuple = (Tuple<?>) o;

        if (key != null ? !key.equals(tuple.key) : tuple.key != null) {
            return false;
        }
        return !(value != null ? !value.equals(tuple.value) : tuple.value != null);

    }

    @Override
    public final int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
