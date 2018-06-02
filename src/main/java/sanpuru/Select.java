//package sanpuru;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//
//import oracle.jdbc.OraclePreparedStatement;
//import oracle.xdb.XMLType;
//
//public class Select {
//    static String url = "jdbc:oracle:thin:@oracle-test.xxx.ap-northeast-1.rds.amazonaws.com:1521:orcl";
//    static String uid = "scott";
//    static String pwd = "tiger";
//    static String sql = "SELECT SYS_NC_ROWINFO$ FROM PO_SL_BIX_TABLE";
//    public static void main(String[] args) throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection(url, uid, pwd);
//        OraclePreparedStatement sqlStatement = (OraclePreparedStatement)conn.prepareStatement(sql);
//        ResultSet result = sqlStatement.executeQuery();
//        while (result.next()) {
//            XMLType xmltype = (XMLType)result.getObject(1);
//            String xml = xmltype.getStringVal();
//            System.out.println("xml=" + xml);
//        }
//        conn.close();
//    }
//}
