package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class CallingProcedureWithoutParameters {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(HsqlZooDb.URL)) {
            String callSql = "{call read_e_names()}";

            try (CallableStatement cs = conn.prepareCall(callSql);
                 ResultSet rs = cs.executeQuery()) {
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
