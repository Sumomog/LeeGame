/**
 * http://www.techscore.com/blog/2015/03/27/3%E5%88%86%E3%81%A7%E6%A7%8B%E7%AF%89%EF%BC%81sqlite%E3%82%92%E8%89%B2%E3%80%85%E3%81%AA%E7%92%B0%E5%A2%83%E3%81%A7%E5%8B%95%E3%81%8B%E3%81%97%E3%81%A6%E3%81%BF%E3%82%8B/
 * https://www.dbonline.jp/sqlite/
 */
package sanpuru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteSample {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/path/to/hoge.db");
            statement = connection.createStatement();
            String sql = "select * from fruits";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}