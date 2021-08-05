package pet;

import meal.MealDBService;
import meal.PetMeal;
import toys.PetToy;
import toys.ToyDBService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PetController {
    PetDBService petDBService = new PetDBService();
    MealDBService mealDBService = new MealDBService();
    ToyDBService toyDBService = new ToyDBService();

    private String petListTitle = " id\t name\t age \ttype \t owner\n";
    private String petTypeList = " id\t value\n";

    public void showAllPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            pets = petDBService.getAllPets();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        String message = petListTitle
                + pets.stream()
                .map(Pet::toString)
                .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog
                (null, message);
    }

    public void viewPet() {
        String petId = JOptionPane.showInputDialog(null, "Enter pet Id");
        Pet pet = null;
        ArrayList<PetMeal> petMeals = new ArrayList<PetMeal>();
        ArrayList<PetToy> petToys = new ArrayList<PetToy>();

        try {
            pet = petDBService.getPet(Integer.parseInt(petId));
            petToys = toyDBService.findToysByPetType(pet.getType());
            petMeals = mealDBService.findMealsByPetType(pet.getType());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        String message = createPetProfileUI(pet, petMeals, petToys);
        JOptionPane.showMessageDialog(null, message);
    }

    private String createPetProfileUI(Pet pet, ArrayList<PetMeal> petMeals, ArrayList<PetToy> petToys) {
        String petInfo = String.format("\n%s's Profile" + "\nSpecial id: %o" + "\nAge: %o" + "\nType: %s",
                pet.getName(), pet.getId(), pet.getAge(), pet.getType().getValue());
        String ownerInfo = "\n\nOwner information" + "\nname: " + pet.getOwner().getName();
        //  StringBuilder message = new StringBuilder(petInfo).append(ownerInfo);

        // message.append("\n\nFood information\n");
        StringBuilder foodInfo = new StringBuilder("\n\nFood information\n");
        for (PetMeal meal : petMeals) {
            foodInfo.append("\n")
                    .append(meal.getId()).append(" \t ")
                    .append(meal.getName()).append(" \t ")
                    .append(meal.getWeight()).append("\n");
        }
        // message.append("\n\n Toy information");
        StringBuilder toyInfo = new StringBuilder("\n\n Toy information\n");
        for (PetToy toy : petToys) {
            toyInfo.append(toy.getId()).append(" \t ")
                    .append(toy.getMaterial()).append(" \t ")
                    .append(toy.getPrice()).append("\n");
        }
        //  return message.toString();
        return petInfo + ownerInfo + foodInfo + toyInfo;
    }

    public void removePetFromOwner() {
        try {
            int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet Id"));
            petDBService.removeOwnerFromPet(petId);
            JOptionPane.showMessageDialog(null, "Sad to see you go :(");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while removing pet");
        }
    }

    public void assignPetToOwner() {
        try {
            int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet Id"));
            int ownerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Owner Id"));
            petDBService.addOwnerToPet(ownerId, petId);
            JOptionPane.showMessageDialog(null, "Pet adoption complete :)");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occured while removing pet");
        }
    }

    public void addNewPet() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter pet name: ");
            int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet's age: "));
            int petType = Integer.parseInt(JOptionPane.showInputDialog("Enter pet type id from the list:",viewAllPetTypes()));
            petDBService.addPet(name, age, petType);
            JOptionPane.showMessageDialog(null, "New pet has been added");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error. New pet was not added");
        }
    }

    public void addNewPetType() {
        try {
            String petType = JOptionPane.showInputDialog(null, "Insert new pet type: ");
            petDBService.addPetType(petType);
            JOptionPane.showMessageDialog(null, "New pet type has been added");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error. New pet type was not added");
        }
    }

    public void removePetType() {
        try {
            int petTypeId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet type Id"));
            petDBService.removePetTypeFromList(petTypeId);
            JOptionPane.showMessageDialog(null, "Pet type was removed from the list");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error. Pet type was not removed");
        }
    }

    public Component viewAllPetTypes() {
       ArrayList<PetType> petTypes = new ArrayList<>();
        try {
            petTypes = petDBService.showPetType();
        }catch (SQLException e){ e.printStackTrace(); }
        String message = petTypeList + petTypes.stream().map(PetType::toString).collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(null, message);
        return null;
    }
}