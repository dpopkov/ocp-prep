package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class GettingDbConnection {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DerbyZooDb.URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM names");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }
}
