package learn.ocp.progr2.ch10jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadingDataWithExecuteQuery {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";

        var sql = "SELECT id, name FROM names";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (var ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.printf("%2d : %s%n", rs.getInt(1), rs.getString(2));
                }
            }
        }
    }
}
