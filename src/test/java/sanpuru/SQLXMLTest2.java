//package sanpuru;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.SQLXML;
//import java.sql.Statement;
//
//import oracle.jdbc.pool.OracleDataSource;
// 
//public class SQLXMLTest
// {
// 
//  public static void main(String[] args) 
//  {
//  
//  Connection conn = null;
//  Statement stmt = null;
//  ResultSet rs = null;
//  PreparedStatement ps = null;
//  
//  String xml = "<?xml version=\"1.0\"?>\n" +
//    "<oldjoke>\n" +
//    "<burns>Say <quote>goodnight</quote>, Gracie.</burns>\n" +
//    "<allen><quote>Goodnight, Gracie.</quote></allen>\n" +
//    "<applause/>\n" +
//    "</oldjoke>";
// 
//  try
//  {
// 
//     OracleDataSource ods = new OracleDataSource();
//     ods.setURL("jdbc:oracle:thin:@//localhost:1521/orcl");
//     ods.setUser("scott");
//     ods.setPassword("tiger");
//     conn = ods.getConnection();
// 
//     ps = conn.prepareStatement("insert into x values (?, ?)");
//     ps.setString(1, "string to string");
//     SQLXML x = conn.createSQLXML();
//     x.setString(xml);
//     ps.setSQLXML(2, x);
//     ps.execute();
//     x.free();
//     stmt = conn.createStatement();
//     rs = stmt.executeQuery("select * from x");
//     while (rs.next()) 
//     {
// 
//       System.out.println(rs.getString(1) + "\n" + rs.getSQLXML(2).getString());
//     }
// 
//     rs.close();
//     ps.close();    
//  }
// 
//  catch (SQLException e){e.printStackTrace ();}
// 
//  }
//}
