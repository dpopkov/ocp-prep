package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class ReturningOutParameter {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(HsqlZooDb.URL)) {
            String callSql = "{?= call magic_number(?)}";

            try (CallableStatement cs = conn.prepareCall(callSql)) {
                cs.registerOutParameter(1, Types.INTEGER);
                cs.execute();
                System.out.println("magic_number = " + cs.getInt("num"));
            }
        }
    }
}
