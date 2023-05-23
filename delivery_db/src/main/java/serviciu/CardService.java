package serviciu;

import database.DatabaseConnection;
import model.Card;

import java.sql.*;

public class CardService {
    private static CardService singleInstance = null;

    private CardService() {
    }

    public static CardService getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new CardService();
        }
        return singleInstance;
    }

    public int createCard(Card card) {
        String insertCardSql = "INSERT INTO Carduri(numar, dataExpirare, numeDetinator, cvv) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCardSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, card.getNumar());
            preparedStatement.setString(2, card.getDataExpirare());
            preparedStatement.setString(3, card.getNumeDetinator());
            preparedStatement.setString(4, card.getCvv());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Card readCard(int id) {
        String selectSql = "SELECT * FROM Carduri WHERE id = ?";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCard(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Card getById(int id) throws SQLException {
        String selectCardSql = "SELECT * FROM Carduri WHERE id = ?";

        PreparedStatement preparedStatement = DatabaseConnection.getSingleInstance().getConnection().prepareStatement(selectCardSql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String numar = resultSet.getString("numar");
            String dataExpirare = resultSet.getString("dataExpirare");
            String numeDetinator = resultSet.getString("numeDetinator");
            String cvv = resultSet.getString("cvv");

            return new Card(id, numar, dataExpirare, numeDetinator, cvv);
        } else {
            return null;
        }
    }

    public void updateCard(Card card) {
        String updateSql = "UPDATE Carduri SET numar = ?, dataExpirare = ?, numeDetinator = ?, cvv = ? WHERE id = ?";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, card.getNumar());
            preparedStatement.setString(2, card.getDataExpirare());
            preparedStatement.setString(3, card.getNumeDetinator());
            preparedStatement.setString(4, card.getCvv());
            preparedStatement.setInt(5, card.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCard(int id) {
        String deleteSql = "DELETE FROM Carduri WHERE id = ?";

        Connection connection = DatabaseConnection.getSingleInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Card mapToCard(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Card(
                    resultSet.getInt("id"),
                    resultSet.getString("numar"),
                    resultSet.getString("dataExpirare"),
                    resultSet.getString("numeDetinator"),
                    resultSet.getString("cvv")
            );
        }

        return null;
    }
}
