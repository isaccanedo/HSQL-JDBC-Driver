import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) throws Exception {
    Connection     conn = getHSQLConnection();
    Statement st = conn.createStatement();
//    st.executeUpdate("drop table survey;");
    st.executeUpdate("create table survey (id int,name varchar(30));");
    st.executeUpdate("insert into survey (id,name ) values (1,'nameValue')");
    
    
    PreparedStatement pstmt = null;
    ParameterMetaData paramMetaData = null;
    String query = "select * from survey where id > ? ";

    System.out.println("conn=" + conn);
    pstmt = conn.prepareStatement(query);
    paramMetaData = pstmt.getParameterMetaData();
    if (paramMetaData == null) {
      System.out.println("fornecedor db NÃO oferece suporte ParameterMetaData");
    } else {
      System.out.println("db vendor supports ParameterMetaData");
      // descubra o número de parâmetros dinâmicos
      int paramCount = paramMetaData.getParameterCount();
      System.out.println("paramCount=" + paramCount);
    }

    pstmt.close();
    conn.close();

  }

  private static Connection getHSQLConnection() throws Exception {
    Class.forName("org.hsqldb.jdbcDriver");
    System.out.println("Driver Loaded.");
    String url = "jdbc:hsqldb:data/tutorial";
    return DriverManager.getConnection(url, "sa", "");
  }

  public static Connection getMySqlConnection() throws Exception {
    String driver = "org.gjt.mm.mysql.Driver";
    String url = "jdbc:mysql://localhost/demo2s";
    String username = "oost";
    String password = "oost";

    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }

  public static Connection getOracleConnection() throws Exception {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:caspian";
    String username = "mp";
    String password = "mp2";

    Class.forName(driver); // carregar driver Oracle
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }
}
