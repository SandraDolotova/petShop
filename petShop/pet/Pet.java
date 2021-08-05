package pet;
import owner.Owner;

public class Pet {

   private int id;
   private Owner owner;
   private int age;
   private PetType type;
   private String name;

    public Pet() {
    }

    public Pet(int id, Owner owner, int age, PetType pet_type, String name) {
        this.id = id;
        this.owner = owner;
        this.age = age;
        this.type = pet_type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "\n" + id +
                " \t " + name +
                " \t  " + age + " Year (s)" +
                " \t  " + type.getValue() +
                " \t  "  + owner.getName();
    }

}
