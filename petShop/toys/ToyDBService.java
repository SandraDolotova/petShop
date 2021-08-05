package toys;

import dataBase.DBHandler;
import dataBase.Queries;
import meal.PetMeal;
import pet.PetType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToyDBService {
    DBHandler dbHandler = new DBHandler();

    public ArrayList<PetToy> findToysByPetType(PetType petType) throws SQLException {
        ArrayList<PetToy> petToys = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_TOYS_BY_FIELD_NAME);
        preparedStatement.setInt(1, petType.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            PetToy petToy = new PetToy(
                    resultSet.getInt("id"),
                    resultSet.getString("material"),
                    new PetType(resultSet.getInt("pet_type_id"), resultSet.getString("pet_type_value")),
                    resultSet.getDouble("price")
            );
            petToys.add(petToy);
        }
        resultSet.close();
        preparedStatement.close();
        return petToys;
    }
}

