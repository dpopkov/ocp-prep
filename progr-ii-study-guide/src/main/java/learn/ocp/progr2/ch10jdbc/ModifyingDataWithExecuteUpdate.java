package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class ModifyingDataWithExecuteUpdate {

    public static void main(String[] args) throws SQLException {
        var insertSql = "INSERT INTO exhibits VALUES (10, 'Deer', 3)";
        var updateSql = "UPDATE exhibits SET name = '' WHERE name = 'None'";
        var deleteSql = "DELETE FROM exhibits WHERE id = 10";

        try (Connection conn = DriverManager.getConnection(DerbyZooDb.URL)) {
            try (var ps = conn.prepareStatement(insertSql)) {
                int result = ps.executeUpdate();
                System.out.println("result = " + result);
            }
            try (var ps = conn.prepareStatement(updateSql)) {
                int result = ps.executeUpdate();
                System.out.println("result = " + result);
            }
            try (var ps = conn.prepareStatement(deleteSql)) {
                int result = ps.executeUpdate();
                System.out.println("result = " + result);
            }
        }
    }
}
