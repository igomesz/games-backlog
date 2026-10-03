import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Properties loadProperties (){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection (){
        if (conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}
