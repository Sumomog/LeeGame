package game.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SQLiteDB {
	public static void main(String[] args) {

		class Person {
			public Integer id = 999901;
			public String name = "name01";
		}
		String sql = "delete from person";
		List<Person> dtos = new ArrayList<>();
		dtos.add(new Person());
		SQLiteDB.getConn("jdbc:sqlite:test.sqlite3");
		execute(sql);
        sql = "insert into person (id, name) values(?, ?)";
		SQLiteDB.executeBatch(sql, dtos);

		sql = "SELECT*FROM person";
		dtos = SQLiteDB.getPstmte2Dto(sql, Person.class);

		System.out.println("Person.id  :"+dtos.get(0).id);
		System.out.println("Person.name:"+dtos.get(0).name);
		System.out.println();
		
		if(true)return;

		System.out.println(byte.class.getName());
		System.out.println(short.class.getName());
		System.out.println(int.class.getName());
		System.out.println(Byte.class.getName());
		System.out.println(Short.class.getName());
		System.out.println(Integer.class.getName());
		System.out.println(String.class.getName());
	}
	private static Connection conn;

	/**
     * ドライバの登録
     */
    public static void dbInit() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * pragmaの設定
     * ここで3.のjornal_modeとsync_modeを設定している
     */
    public static Properties getProperties() {
        Properties prop = new Properties();
        prop.put("journal_mode", "MEMORY");
        prop.put("sync_mode", "OFF");
        return prop;
    }
    /**
     * コネクション取得
     */
    public static Connection getConn(String dbHeader) {
    	if (conn != null) return conn;
        dbInit();
        try {
        	conn = DriverManager.getConnection(dbHeader, getProperties());
        	conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return conn;
    }
    /**
     * SQL実施
     */
    public static void execute(String sql) {
        try {
			conn.prepareStatement(sql).execute();
            conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    /**
     * データベースに追加する処理
     * synchronizedを入れないとマルチスレッドでガンガン書き込んだときにdeadlockを起こす
     */
    public static synchronized <T> void executeBatch(String sql, List<? extends T> dtos) {
        PreparedStatement pstmt;
        try {
            // 一括でデータを追加するための準備
            pstmt = conn.prepareStatement(sql);
            for (T t : dtos) {
        		setDto2Pstmt(pstmt, t);
        		pstmt.addBatch();
            }
            // 実際のデータベース追加処理はここで行われる
            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/**
     * パラメータ設定
     */
    public static void setDto2Pstmt(PreparedStatement pstmt, Object obj) {
		try {
        	int pi = 0;
	    	for (Field f : obj.getClass().getDeclaredFields()) {
	    		pi++;
	    		f.setAccessible(true);
		    	switch(f.getType().getName()) {
		    	case "byte" :
		    	case "java.lang.Byte" :
		    		pstmt.setByte(pi, (byte) f.get(obj));
		    		break;
		    	case "short" :
		    	case "java.lang.Short" :
		    		pstmt.setShort(pi, (short) f.get(obj));
		    		break;
		    	case "int" :
		    	case "java.lang.Integer" :
		    		pstmt.setInt(pi, (int) f.get(obj));
		    		break;
		    	case "long" :
		    	case "java.lang.Long" :
		    		pstmt.setLong(pi, (long) f.get(obj));
		    		break;
		    	case "java.lang.String" :
		    		pstmt.setString(pi, (String) f.get(obj));
		    		break;
		    	default:
		    		System.err.println(f.getType().getName() + " は処理対象外");
		    	}
	    	}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	/**
     * DTOの取得
     * @return DTOリスト
     */
    public static <T> List<T> getPstmte2Dto(String sql, Class<T> cls) {
        PreparedStatement pstmt;
        List<T> ret = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            //5000兆とかセットすれば一回で全部取得する
            pstmt.setFetchSize(1000);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	T obj = cls.getDeclaredConstructor().newInstance();
            	int pi = 0;
    	    	for (Field f : cls.getDeclaredFields()) {
    	    		pi++;
    	    		f.setAccessible(true);
    		    	switch(f.getType().getName()) {
    		    	case "byte" :
    		    	case "java.lang.Byte" :
    		    		f.set(obj, rs.getByte(pi));
    		    		break;
    		    	case "short" :
    		    	case "java.lang.Short" :
    		    		f.set(obj, rs.getShort(pi));
    		    		break;
    		    	case "int" :
    		    	case "java.lang.Integer" :
    		    		f.set(obj, rs.getInt(pi));
    		    		break;
    		    	case "long" :
    		    	case "java.lang.Long" :
    		    		f.set(obj, rs.getLong(pi));
    		    		break;
    		    	case "java.lang.String" :
    		    		f.set(obj, rs.getString(pi));
    		    		break;
    		    	default:
    		    		System.err.println(f.getType().getName() + " は処理対象外");
    		    	}
    	    	}
    	    	ret.add(obj);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}


//public class SQLiteDB {
//    private static Object place;
//	/**
//     * ドライバの登録
//     */
//    public static void dbInit() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * pragmaの設定
//     * ここで3.のjornal_modeとsync_modeを設定している
//     */
//    public static Properties getProperties() {
//        Properties prop = new Properties();
//        prop.put("journal_mode", "MEMORY");
//        prop.put("sync_mode", "OFF");
//        return prop;
//    }
//    /**
//     * データベースに追加する処理
//     * synchronizedを入れないとマルチスレッドでガンガン書き込んだときにdeadlockを起こす
//     */
//    public static synchronized void putTweet2SQL(File dbFile, List<Status> tweet) {
//        Statement stmt;
//        String dbHeader = "jdbc:sqlite:" + dbFile.getAbsolutePath();
//        PreparedStatement pstmt;
//        dbInit();
//        try (Connection conn = DriverManager.getConnection(dbHeader, getProperties())) { //try-with-resources
//            conn.setAutoCommit(false);
//            stmt = conn.createStatement();
//            //データベースが無かったら作成
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tweets (tweet_id INTEGER PRIMARY KEY, user_id INTEGER, user_screen_name TEXT,tweet_text TEXT)");
//            //ツイートID，ユーザID，スクリーン名，ツイートテキスト
//            //2.一括でデータを追加するための準備
//            pstmt = conn.prepareStatement("INSERT OR IGNORE INTO tweets VALUES (?, ?, ?, ?)");
//            for (Status status : tweet) {
////                place = status.getPlace().getFullName();
////                pstmt.setLong(1, status.getId());
////                pstmt.setLong(2, status.getUser().getId());
////                pstmt.setString(3, status.getUser().getScreenName());
////                pstmt.setString(4, status.getText());
////                pstmt.addBatch();//1.処理に追加
//            }
//            pstmt.executeBatch();//1.実際のデータベース追加処理はここで行われる
//            conn.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * ツイートの取得
//     * 巨大データを引っ張ってくるとヒープ不足になるのでその場合は適当に書き換える
//     * @return ツイートリスト
//     */
//    public static List<Status> getTweetsFromSQL(File dbFile) {
//        String dbHeader = "jdbc:sqlite:" + dbFile.getAbsolutePath();
//        PreparedStatement pstmt;
//        List<Status> userDetails = new ArrayList<>();//適当なリスト
//        dbInit();
//        try (Connection conn = DriverManager.getConnection(dbHeader, getProperties())) {
//            pstmt = conn.prepareStatement("SELECT * FROM tweets");
//            pstmt.setFetchSize(1000);//5000兆とかセットすれば一回で全部取得する
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {//ツイートID，ユーザID，スクリーン名，ツイートテキスト
//                Status status = new Status();
////                status.setId(rs.getLong(1));
////                status.setUserId(rs.getLong(2));
////                status.setScreenName(rs.getString(3));
////                status.setTweetText(rs.getString(4));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userDetails;
//    }
//}
