package serviciu;

import database.DatabaseConnection;
import model.Card;
import model.Utilizator;

import java.sql.*;

public class UtilizatorService {
    private static UtilizatorService singleInstance = null;

    private UtilizatorService() {
    }

    public static UtilizatorService getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new UtilizatorService();
        }
        return singleInstance;
    }

    public void createUtilizator(Utilizator utilizator) {
        String insertUtilizatorSql = "INSERT INTO Utilizatori(id, nume, adresa, numarTelefon, card_id) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUtilizatorSql);
            preparedStatement.setInt(1, utilizator.getId());
            preparedStatement.setString(2, utilizator.getNume());
            preparedStatement.setString(3, utilizator.getAdresa());
            preparedStatement.setString(4, utilizator.getNumarTelefon());
            preparedStatement.setInt(5, utilizator.getCardId(utilizator.getCard()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilizator readUtilizator(int id) {
        String selectSql = "SELECT * FROM Utilizatori WHERE id = ?";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUtilizator(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateUtilizator(Utilizator utilizator) {
        String updateSql = "UPDATE Utilizatori SET nume = ?, adresa = ?, numarTelefon = ?, card_id = ? WHERE id = ?";
        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, utilizator.getNume());
            preparedStatement.setString(2, utilizator.getAdresa());
            preparedStatement.setString(3, utilizator.getNumarTelefon());
            preparedStatement.setInt(4, utilizator.getCardId(utilizator.getCard()));
            preparedStatement.setInt(5, utilizator.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUtilizator(int id) {
        String deleteSql = "DELETE FROM Utilizatori WHERE id = ?";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Utilizator mapToUtilizator(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            int idCard = resultSet.getInt("card_id");
            CardService cardService = CardService.getSingleInstance();
            Card card = cardService.readCard(idCard);
            return new Utilizator(
                    resultSet.getInt("id"),
                    resultSet.getString("nume"),
                    resultSet.getString("adresa"),
                    resultSet.getString("numarTelefon"),
                    card
            );
        }

        return null;
    }
}
