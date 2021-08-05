package toys;

import pet.PetType;

public class PetToy {

    private int id;
    private String material;
    private PetType pet_type;
    private double price;

    public PetToy(int id, String material, PetType pet_type, double price) {
        this.id = id;
        this.material = material;
        this.pet_type = pet_type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getMaterial() {
        return material;
    }

    public PetType getPet_type() {
        return pet_type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + "\t" +  material + "\t" + price;

    }
}
