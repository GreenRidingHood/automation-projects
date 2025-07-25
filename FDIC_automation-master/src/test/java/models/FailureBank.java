package models;

public class FailureBank {
    private int id;
    private String name;

    public FailureBank(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Failurebank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
