package dao.user;

import dao.BaseDao;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao{
    public User getLoginUser(Connection connection, String id) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        if(connection != null){
            String sql = "select * from users where id=?";
            Object[] params = {id};
            resultSet = BaseDao.executeQuery(connection, sql, params);
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getString("id"));
                user.setPassword(resultSet.getString("password"));
            }
            BaseDao.closeResource(null, preparedStatement, resultSet);
        }
        return user;
    }
}
