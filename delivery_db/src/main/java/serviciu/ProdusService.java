package serviciu;

import database.DatabaseConnection;
import model.Produs;

import java.sql.*;

public class ProdusService {
    private static ProdusService singleInstance = null;

    private ProdusService() {}

    public static ProdusService getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new ProdusService();
        }
        return singleInstance;
    }

    public void createProdus(Produs produs) throws SQLException {
        String insertProdusSql = "INSERT INTO Produse(nume, pret) VALUES (?, ?)";
        Connection connection = DatabaseConnection.getSingleInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(insertProdusSql);
        preparedStatement.setString(1, produs.getNume());
        preparedStatement.setDouble(2, produs.getPret());

        preparedStatement.executeUpdate();
    }

    public Produs readProdus(int id) throws SQLException {
        String selectProdusSql = "SELECT * FROM Produse WHERE id = ?";
        Connection connection = DatabaseConnection.getSingleInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(selectProdusSql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        return mapToProdus(resultSet);
    }

    public void updateProdus(Produs produs) throws SQLException {
        String updateProdusSql = "UPDATE Produse SET nume = ?, pret = ? WHERE id = ?";
        Connection connection = DatabaseConnection.getSingleInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(updateProdusSql);
        preparedStatement.setString(1, produs.getNume());
        preparedStatement.setDouble(2, produs.getPret());
        preparedStatement.setInt(3, produs.getId());

        preparedStatement.executeUpdate();
    }

    public void deleteProdus(int id) throws SQLException {
        String deleteProdusSql = "DELETE FROM Produse WHERE id = ?";
        Connection connection = DatabaseConnection.getSingleInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(deleteProdusSql);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    private Produs mapToProdus(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nume = resultSet.getString("nume");
            double pret = resultSet.getDouble("pret");
            return new Produs(id, nume, pret);
        } else {
            return null;
        }
    }
}
