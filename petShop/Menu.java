import owner.OwnerController;
import pet.PetController;

import javax.swing.*;
import java.sql.SQLException;

public class Menu {
    PetController petController = new PetController();
    OwnerController ownerController = new OwnerController();

    public void start() {
        String userChoice = JOptionPane.showInputDialog(null, "Welcome to pet manager\n" +
                "\n1. View All Pets" + "    2. Add Pet"
                + "\n3. View Pet Profile" + "    4. Remove pet from Owner"
                + "\n5. Assign Pet to Owner" + "    6. View All Owners"
                + "\n7. View Owner Profile" + "    8. Add Owner"
                + "\n9. Remove Owner" + "    10. Add Pet Type"
                + "\n11. Remove Pet Type" + "    12. View All Pet Types"
                + "\n13. Exit");

        switch (userChoice) {
            case "1":
                petController.showAllPets();
                break;
            case "2":
                petController.addNewPet();
                break;
            case "3":
                petController.viewPet();
                break;
            case "4":
                petController.removePetFromOwner();
                break;
            case "5":
                petController.assignPetToOwner();
                break;
            case "6":
                ownerController.showAllOwners();
                break;
            case "7":
                ownerController.viewOwner();
                break;
            case "8":
                ownerController.createOwner();
                break;
            case "9":
                ownerController.removeOwner();
                break;
            case "10":
                petController.addNewPetType();
                break;
            case "11":
                petController.removePetType();
                break;
            case "12":
                petController.viewAllPetTypes();
                break;
            case "13":
                System.exit(0);
                break;
        }

        start();

    }
}