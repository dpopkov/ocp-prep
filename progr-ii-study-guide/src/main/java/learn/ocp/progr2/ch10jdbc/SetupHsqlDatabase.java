package learn.ocp.progr2.ch10jdbc;

import java.sql.*;

public class SetupHsqlDatabase {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:hsqldb:file:h-zoo";

        try (Connection conn = DriverManager.getConnection(url)) {

            runUpdate(conn, "DROP PROCEDURE read_e_names IF EXISTS");
            runUpdate(conn, "DROP PROCEDURE read_names_by_letter IF EXISTS");
            runUpdate(conn, "DROP PROCEDURE magic_number IF EXISTS");
            runUpdate(conn, "DROP PROCEDURE double_number IF EXISTS");
            runUpdate(conn, "DROP TABLE names IF EXISTS");
            runUpdate(conn, "DROP TABLE exhibits IF EXISTS");

            runUpdate(conn, "CREATE TABLE exhibits ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL(4,1))");

            runUpdate(conn, "CREATE TABLE names ("
                    + "id INTEGER PRIMARY KEY, "
                    + "species_id integer REFERENCES exhibits (id), "
                    + "name VARCHAR(255))");

            runUpdate(conn, "INSERT INTO exhibits VALUES (1, 'African Elephant', 7.5)");
            runUpdate(conn, "INSERT INTO exhibits VALUES (2, 'Zebra', 1.2)");

            runUpdate(conn, "INSERT INTO names VALUES (1, 1, 'Elsa')");
            runUpdate(conn, "INSERT INTO names VALUES (2, 2, 'Zelda')");
            runUpdate(conn, "INSERT INTO names VALUES (3, 1, 'Ester')");
            runUpdate(conn, "INSERT INTO names VALUES (4, 1, 'Eddie')");
            runUpdate(conn, "INSERT INTO names VALUES (5, 2, 'Zoe')");

            String noParams = "CREATE PROCEDURE read_e_names() "
                    + "READS SQL DATA DYNAMIC RESULT SETS 1 "
                    + "BEGIN ATOMIC "
                    + "DECLARE result CURSOR WITH RETURN FOR SELECT * FROM names; "
                    + "OPEN result; "
                    + "END";

            String inParam = "CREATE PROCEDURE read_names_by_letter(IN prefix VARCHAR(10)) "
                    + "READS SQL DATA DYNAMIC RESULT SETS 1 "
                    + "BEGIN ATOMIC "
                    + "DECLARE result CURSOR WITH RETURN FOR " +
                    " SELECT * FROM names WHERE name LIKE CONCAT(prefix, '%'); "
                    + "OPEN result; "
                    + "END";

            String inOutParam = "CREATE PROCEDURE double_number(INOUT num INT) READS SQL DATA\n" +
                    "  DYNAMIC RESULT SETS 1 " +
                    "  BEGIN ATOMIC " +
                    "  SET num = num * 2; " +
                    "  END";

            String outParam = "CREATE PROCEDURE magic_number(OUT num INT) READS SQL DATA\n" +
                    "  BEGIN ATOMIC " +
                    "  SET num = 42;" +
                    "  END";

            runUpdate(conn, noParams);
            runUpdate(conn, inParam);
            runUpdate(conn, outParam);
            runUpdate(conn, inOutParam);

            printCount(conn);
        }
    }

    private static void runUpdate(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    private static void printCount(Connection conn) throws SQLException {
        String countSql = "SELECT count(*) FROM names";

        try (PreparedStatement ps = conn.prepareStatement(countSql)) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Count: " + rs.getInt(1));
        }
    }
}
