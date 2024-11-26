package entities;

public class Entry {
    private int id;
    private int placeholderValue;

    public Entry(int id, int placeholderValue) {
        this.id = id;
        this.placeholderValue = placeholderValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceholderValue() {
        return placeholderValue;
    }

    public void setPlaceholderValue(int placeholderValue) {
        this.placeholderValue = placeholderValue;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", placeholderValue=" + placeholderValue +
                '}';
    }
}
