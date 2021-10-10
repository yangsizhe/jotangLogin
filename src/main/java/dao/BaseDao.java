package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 操作数据库的基类
 */
public class BaseDao {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    /*
    从配置文件里获得Property，初始化数据库连接所需参数
     */
    static {
        Properties params = new Properties();
        InputStream is=BaseDao.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            params.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=params.getProperty("driver");
        url=params.getProperty("url");
        username=params.getProperty("username");
        password=params.getProperty("password");

    }


    /*
    获取数据库连接
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        //加载驱动
        Class.forName(driver);
        //获取连接
        connection = (Connection)DriverManager.getConnection(url, username, password);

        return connection;
    }
    /*
    查询操作
     */
    public static ResultSet executeQuery(Connection connection, String sql,Object[] params) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /*
    关闭所有资源
     */
    public static void closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
