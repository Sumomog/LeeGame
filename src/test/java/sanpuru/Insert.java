//package sanpuru;
//
//import java.io.StringReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//
//import oracle.jdbc.OraclePreparedStatement;
//import oracle.xdb.XMLType;
//
//public class Insert {
//    static String url = "jdbc:oracle:thin:@oracle-test.xxx.ap-northeast-1.rds.amazonaws.com:1521:orcl";
//    static String uid = "scott";
//    static String pwd = "tiger";
//    static String xml = "<PurchaseOrder DateCreated=\"2012-10-17T12:17:43.48000Z\"/>";
//    static String sql = "INSERT INTO PO_SL_BIX_TABLE VALUES (?)";
//    public static void main(String[] args) throws Exception {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(new InputSource(new StringReader(xml)));
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection(url, uid, pwd);
//        XMLType xmltype = XMLType.createXML(conn, doc);
//        OraclePreparedStatement sqlStatement = (OraclePreparedStatement)conn.prepareStatement(sql);
//        sqlStatement.setObject(1, xmltype);
//        sqlStatement.execute();
//        System.out.println("Success!");
//        conn.close();
//    }
//}