package model;

public class User {

    private final String name;
    private Long id;

    public User(String name) {
        this.name = name;
    }

    public void setID(long sequence) {
        this.id = sequence;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isSameUsername(String name) {
        return this.name.equals(name);
    }
}