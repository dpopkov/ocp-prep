package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class WorkingWithInOutParameter {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(HsqlZooDb.URL)) {
            String callSql = "{call double_number(?)}";

            try (CallableStatement cs = conn.prepareCall(callSql)) {
                cs.setInt(1, 8);
                cs.registerOutParameter(1, Types.INTEGER);
                cs.execute();
                System.out.println("double_number = " + cs.getInt("num"));
            }
        }
    }
}
