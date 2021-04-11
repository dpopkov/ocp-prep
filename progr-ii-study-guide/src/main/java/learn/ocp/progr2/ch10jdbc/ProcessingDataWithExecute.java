package learn.ocp.progr2.ch10jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessingDataWithExecute {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";

        var selectSql = "SELECT id, name FROM exhibits";
        var updateSql = "UPDATE exhibits SET name = '' WHERE name = 'None'";

        try (Connection conn = DriverManager.getConnection(url)) {
            try (var ps = conn.prepareStatement(Math.random() > 0.5 ? selectSql : updateSql)) {
                boolean isResultSet = ps.execute();
                if (isResultSet) {
                    System.out.println("Ran a query");
                    ResultSet rs = ps.getResultSet();
                    while (rs.next()) {
                        System.out.printf("%2d : %s%n", rs.getInt(1), rs.getString(2));
                    }
                } else {
                    System.out.println("Ran an update");
                    int rowsUpdated = ps.getUpdateCount();
                    System.out.println("rowsUpdated = " + rowsUpdated);
                }
            }
        }
    }
}
