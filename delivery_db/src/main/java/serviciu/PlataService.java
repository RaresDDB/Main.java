package serviciu;

import database.DatabaseConnection;
import model.Card;
import model.Plata;

import java.sql.*;

public class PlataService {

    private static PlataService singleInstance = null;

    private PlataService() { }

    public static PlataService getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new PlataService();
        }
        return singleInstance;
    }

    public void createPlata(Plata plata) throws SQLException {
        String insertPlataSql = "INSERT INTO Plati(data, valoare, card_id) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = DatabaseConnection.getSingleInstance().getConnection().prepareStatement(insertPlataSql);
        preparedStatement.setDate(1, new Date(plata.getData().getTime()));
        preparedStatement.setDouble(2, plata.getValoare());
        preparedStatement.setInt(3, plata.getCardId(plata.getCard()));

        preparedStatement.executeUpdate();
    }

    public Plata readPlata(int id) {
        String selectSql = "SELECT * FROM Plati WHERE id = ?";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPlata(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updatePlata(Plata plata) throws SQLException {
        String updatePlataSql = "UPDATE Plati SET data = ?, valoare = ?, card_id = ? WHERE id = ?";

        PreparedStatement preparedStatement = DatabaseConnection.getSingleInstance().getConnection().prepareStatement(updatePlataSql);
        preparedStatement.setDate(1, new Date(plata.getData().getTime()));
        preparedStatement.setDouble(2, plata.getValoare());
        preparedStatement.setInt(3, plata.getCardId(plata.getCard()));
        preparedStatement.setInt(4, plata.getId());

        preparedStatement.executeUpdate();
    }

    public void deletePlata(int id) throws SQLException {
        String deletePlataSql = "DELETE FROM Plati WHERE id = ?";

        PreparedStatement preparedStatement = DatabaseConnection.getSingleInstance().getConnection().prepareStatement(deletePlataSql);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    private Plata mapToPlata(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            Date data = resultSet.getDate("data");
            double valoare = resultSet.getDouble("valoare");
            int idCard = resultSet.getInt("card_id");

            CardService cardService = CardService.getSingleInstance();
            Card card = cardService.getById(idCard);

            return new Plata(id, data, valoare, card);
        }
        return null;
    }
}
