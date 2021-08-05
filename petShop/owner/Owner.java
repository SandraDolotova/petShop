package owner;

public class Owner {

    private int id;
    private final String name;

    public Owner(String name) {
        this.name = name;
    }

    public Owner(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return id + " \t " + name;
    }
}
