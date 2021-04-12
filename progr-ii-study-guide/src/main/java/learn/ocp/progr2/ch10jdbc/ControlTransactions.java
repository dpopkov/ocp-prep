package learn.ocp.progr2.ch10jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;

public class ControlTransactions {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(DerbyZooDb.URL);
        try (conn) {
            if (conn.getMetaData().supportsTransactions()) {
                System.out.println("DB supports transactions");

                conn.setAutoCommit(false);
                System.out.println("Set AutoCommit false");

                String insertSql = "INSERT INTO names (id, species_id, name) VALUES (?, ?, ?)";
                try (var ps = conn.prepareStatement(insertSql)) {
                    ps.setInt(1, 9);
                    ps.setInt(2, 1);
                    ps.setString(3, "Esmeralda3");
                    ps.executeUpdate();
                }
                System.out.println("Insert executed");
                Savepoint savepoint = conn.setSavepoint();

                String updateSql = "UPDATE names SET name = ? WHERE id = ?";
                try (var ps = conn.prepareStatement(updateSql)) {
                    ps.setString(1, "Eco");
                    ps.setInt(2, 7);
                    ps.executeUpdate();
                }
                System.out.println("Update executed");

                conn.rollback(savepoint);
                System.out.println("Rollback executed");

                conn.commit();
                System.out.println("Connection commit executed");
            } else {
                System.out.println("DB does not support transactions");
            }
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Connection rollback executed");
            e.printStackTrace();
        }
    }
}
