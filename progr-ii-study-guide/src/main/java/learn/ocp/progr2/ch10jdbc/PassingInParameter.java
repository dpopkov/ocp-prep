package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class PassingInParameter {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(HsqlZooDb.URL)) {
            String callSql = "{call read_names_by_letter(?)}";

            try (CallableStatement cs = conn.prepareCall(callSql)) {
                cs.setString("prefix", "Z");

                try (ResultSet rs = cs.executeQuery()) {
                    while (rs.next()) {
                        System.out.printf("%d : %d : %s%n",
                                rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3)
                        );
                    }
                }
            }
        }
    }
}
