package owner;

import pet.Pet;
import pet.PetDBService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OwnerController {
    OwnerDBService ownerDBService = new OwnerDBService();
    PetDBService petDBService = new PetDBService();
    private String ownerListTitle = "id \t name\n";

    public void createOwner(){
        try {
            Owner owner = collectOwnerInfo();
            ownerDBService.add(owner);
            JOptionPane.showMessageDialog(null, owner.getName() + "Added successfully");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occured while adding owner");
        }
    }

    private Owner collectOwnerInfo() throws SQLException {
        String name = JOptionPane.showInputDialog(null, "Enter owner name: ");
        return new Owner(name);
    }

    public void removeOwner() {
        try {
            int ownerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Owner Id"));
            ownerDBService.delete(ownerId);
            JOptionPane.showMessageDialog(null, " Owner deleted successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occured while deleting owner. Make sure they dont have any pets assigned and owner exist on the system");
        }
    }
    public void showAllOwners() {
        ArrayList<Owner> owners = new ArrayList<>();
        try {
            owners = ownerDBService.getAllOwners();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        String message = ownerListTitle +
                owners.stream()
                        .map(Owner::toString)
                        .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(null, message);
    }
    public void viewOwner() {
        int ownerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Owner Id"));
        Owner owner = null;
        ArrayList<Pet> pets = new ArrayList<>();

        try {
            owner = ownerDBService.getOwner(ownerId);
            pets = petDBService.findPetByOwnerId(ownerId);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        String message = createOwnerProfileUI(owner, pets);
        JOptionPane.showMessageDialog(null, message);
    }
    private String createOwnerProfileUI(Owner owner, ArrayList<Pet> pets) {
        String ownerInfo = owner.getName() + "'s Profile \nSpecial ID: \t" + owner.getId();

        StringBuilder petsInfo = new StringBuilder("\n\nPets Information\n");
        for (Pet pet : pets) {
            petsInfo.append(pet.getId()).append(" \t ")
                    .append(pet.getName()).append(" \t ")
                    .append(pet.getAge()).append(" \t")
                    .append(pet.getType().getValue()).append("\n");
        }
        return ownerInfo + petsInfo;
    }
}
