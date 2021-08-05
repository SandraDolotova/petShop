package meal;
import dataBase.DBHandler;
import dataBase.Queries;
import pet.PetType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MealDBService {
    DBHandler dbHandler = new DBHandler();

    public ArrayList<PetMeal> findMealsByPetType(PetType petType) throws SQLException {
        ArrayList<PetMeal> petMeals = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_MEALS_BY_FIELD_NAME);
        preparedStatement.setInt(1, petType.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            PetMeal petMeal = new PetMeal(
            resultSet.getInt("id"),
            new PetType(resultSet.getInt("pet_type_id"), resultSet.getString("pet_type_value")),
            resultSet.getString("name"),
            resultSet.getDouble("weight")
            );
            petMeals.add(petMeal);
        }
        resultSet.close();
        preparedStatement.close();
        return  petMeals;
    }
}
