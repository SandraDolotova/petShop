package meal;

import pet.PetType;

public class PetMeal {
   private int id;
   private PetType pet_type;
   private String name;
   private double weight;

    public PetMeal(int id, PetType pet_type, String name, double weight) {
        this.id = id;
        this.pet_type = pet_type;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public PetType getPet_type() {
        return pet_type;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return id + "\t" + name + "\t" + weight;
    }
}
