package owner;

import dataBase.DBHandler;
import dataBase.Queries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OwnerDBService {
    DBHandler dbHandler = new DBHandler();

    public void add(Owner owner) throws SQLException {
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.INSERT_OWNER);
        preparedStatement.setString(1, owner.getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(int ownerId) throws SQLException{
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.DELETE_OWNER);
        preparedStatement.setInt(1, ownerId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<Owner> getAllOwners() throws SQLException{
        ArrayList<Owner> owners = new ArrayList<>();
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_OWNERS);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()){
            owners.add(new Owner(result.getInt("id"), result.getString("name")));
        }
        return owners;
    }

    public Owner getOwner(int ownerId) throws SQLException {
        Owner owner = null;
        PreparedStatement preparedStatement = dbHandler.getConnection().prepareStatement(Queries.SELECT_OWNER);
        preparedStatement.setInt(1, ownerId);
        ResultSet result = preparedStatement.executeQuery();

        if (result.next()){
            owner = new Owner(result.getInt("id"), result.getString("name"));
        }
        return owner;
    }

}
