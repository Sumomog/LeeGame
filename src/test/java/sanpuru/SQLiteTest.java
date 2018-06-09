package sanpuru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteTest {
	static Connection con;
	static int COUNT = 1;
    private static final String DATA = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

	public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:test.sqlite3");
        Statement st = con.createStatement();
        st.executeUpdate("drop table if exists person");
        st.executeUpdate("create table person (id integer primary key, name string)");
        executeQuery();
        con.close();
	}
    private static void executeQuery() throws Exception {
        executeQuery(con.getMetaData().getDatabaseProductName());
    }

    private static void executeQuery(String databaseName) throws Exception {

        boolean isCassandra = databaseName.contains("Cassandra");
        boolean isAutoCommit = isCassandra;

        System.out.printf("%-14s", databaseName);
        if (!isAutoCommit) {
            con.setAutoCommit(false);
        }

        long insertStart = System.currentTimeMillis();
        PreparedStatement insertPs = con.prepareStatement("insert into person (id, name) values(?, '" + DATA + "')");
        for (int i = 0; i < COUNT; i++) {
            insertPs.setInt(1, i);
            insertPs.executeUpdate();
            if (!isAutoCommit && i % 10000 == 0) {
                con.commit();
            }
        }
        if (!isAutoCommit) {
            con.commit();
        }
        double insertSec = (double) (System.currentTimeMillis() - insertStart) / 1000;

        long selectStart = System.currentTimeMillis();
        PreparedStatement selectPs = con.prepareStatement("select * from person where id = ?");
        for (int i = 0; i < COUNT; i++) {
            selectPs.setInt(1, i);
            selectPs.executeQuery().next();
        }
        double selectSec = (double) (System.currentTimeMillis() - selectStart) / 1000;

        String countSql = "select count(1) from person";
        if (isCassandra) {
            countSql += " limit 100000000";
        }
        ResultSet rs = con.createStatement().executeQuery(countSql);
        rs.next();
        logProcessTime(rs.getInt(1), insertSec, selectSec);
    }

    private static void logProcessTime(long count, double insertSec, double selectSec) {

        System.out.printf("%4d万件 ", count / 10000);
        System.out.printf("%7.2f秒 ", insertSec);
        System.out.printf("%7.2f秒", selectSec);
        System.out.println();
    }
}
