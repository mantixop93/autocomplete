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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
