package sanpuru;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB
{
    // 接続文字列
    private static enum             USE_DB            { DB_DERBY , DB_H2 , DB_HSQLDB };
    private static final String[]   JDBC_STRING     = { "jdbc:derby:db/DerbyTest;create=true" ,                     // Derby用のDBフォルダ名
                                                        "jdbc:h2:file:./db/H2DB/H2DBTest"    ,                      // H2用のDBファイル名
                                                        "jdbc:hsqldb:file:db/HSQLDB/HSQLDBTest;shutdown=true"};     // HSQLDB用のDBファイル名

    private static final String[]   USER_NAME       = { "test" , "" , "test" };     // 接続するユーザ名
    private static final String[]   PASSWORD        = { "test" , "" , "test" };     // ユーザ名に対応するパスワード
    private static final int        TRIAL           = 10000;                        // 時間計測用にループを回す回数

    /**
     * 起動関数
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
    {
        // 使用するDBを決定
        USE_DB  use  = USE_DB.DB_DERBY;
        //USE_DB  use  = USE_DB.DB_H2;
        //USE_DB  use  = USE_DB.DB_HSQLDB;

        // 変数の宣言
        Connection  con     = null;     // コネクション
        Statement   stmt    = null;     // ステートメント
        String      sql     = null;     // SQL文字列
        long        time    = 0L;       // 時間計測用

        // DBに接続
        try {
            // 標準出力をファイルに変更
            File        file    = new File( String.format( "%s.log" , use  ) );
            PrintStream out     = new PrintStream( file.getAbsolutePath() );
            System.setOut( out );

            // メッセージ表示
            System.out.println( String.format( "[START]  used DB is %s. trial number is %d." , use , TRIAL ) );

            // コネクションの作成
            con = getConnection( use );

            // SQL発行(CREATE)
            stmt    = con.createStatement();
            sql     = "create table PERSON" +
                      "(" +
                      "     NAME VARCHAR(20) PRIMARY KEY," +
                      "     AGE  INTEGER "+
                      ")";
            stmt.executeUpdate( sql );

            // SQL発行(INSERT)
            time    = System.currentTimeMillis();
            for( int i=0 ; i<TRIAL ; i++ )
            {
                stmt    = con.createStatement();
                sql     = String.format( "insert into PERSON ( NAME , AGE ) values ( 'name%d' , %d )" , i , i );
                stmt.executeUpdate( sql );
            }
            System.out.println( String.format( "[INSERT] %d [ms]" , System.currentTimeMillis() - time ) );

            // SQL発行(UPDATE)
            time    = System.currentTimeMillis();
            for( int i=0 ; i<TRIAL ; i++ )
            {
                stmt    = con.createStatement();
                sql     = String.format( "update PERSON set AGE = 30 where NAME = 'name%d'" , i );
                if( stmt.executeUpdate( sql ) <= 0 ){ throw new Exception("SQLの実行に失敗(UPDATE)"); }
            }
            System.out.println( String.format( "[UPDATE] %d [ms]" , System.currentTimeMillis() - time ) );

            // SQL発行(SLECT)
            time    = System.currentTimeMillis();
            for( int i=0 ; i<TRIAL ; i++ )
            {
                stmt    = con.createStatement();
                sql     = String.format( "select * from PERSON where NAME = 'name%d'" , i );
                if( ! stmt.execute( sql ) ){ throw new Exception("SQLの実行に失敗(SELECT)"); }
            }
            System.out.println( String.format( "[SLECT]  %d [ms]" , System.currentTimeMillis() - time ) );

            // SQL発行(SLECT ALL)
            time    = System.currentTimeMillis();
            for( int i=0 ; i<TRIAL ; i++ )
            {
                stmt    = con.createStatement();
                sql     = "select * from PERSON";
                if( ! stmt.execute( sql ) ){ throw new Exception("SQLの実行に失敗(SELECT ALL)"); }
            }
            System.out.println( String.format( "[SELECT ALL] %d [ms]" , System.currentTimeMillis() - time ) );

            // 結果の処理
            ResultSet   rs      = stmt.getResultSet();
            while( rs.next() )
            {
                // レコード内の値を取得
                String  name    = rs.getString( "NAME" );
                int     age     = rs.getInt( "AGE" );
                System.out.println( String.format( "name is %s , address is %d ." , name , age ) );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            // DBを閉じる
            try {
                if( con != null ){ con.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用するDBを指定してコネクション作成
     * @param use
     * @return
     * @throws Exception
     */
    private static Connection getConnection( USE_DB use ) throws Exception
    {
        // 戻り値用変数
        Connection  con = null;

        // 使用する接続文字列・ユーザ名・パスワードを決定
        int i   = -1;
        switch( use )
        {
        case DB_DERBY:
            i = 0;
            break;
        case DB_H2:
            i = 1;
            break;
        case DB_HSQLDB:
            i = 2;
            break;
        default:
        }

        // 接続文字列・ユーザ名・パスワードを指定してDB接続
        con    = DriverManager.getConnection( JDBC_STRING[i] , USER_NAME[i] , PASSWORD[i] );

        // 戻り値
        return con;
    }

}