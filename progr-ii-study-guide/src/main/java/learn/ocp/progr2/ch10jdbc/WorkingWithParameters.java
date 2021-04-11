package learn.ocp.progr2.ch10jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WorkingWithParameters {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";

        try (Connection conn = DriverManager.getConnection(url)) {
            register(conn, 6, 1, "Edith");
        }
    }

    private static void register(Connection conn, int key, int type, String name) throws SQLException {
        var insertSql = "INSERT INTO names (id, species_id, name) VALUES (?, ?, ?)";

        try (var ps = conn.prepareStatement(insertSql)) {
            ps.setInt(1, key);
            ps.setInt(2, type);
            ps.setString(3, name);
            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) inserted");
        }
    }
}
