package service;

import dao.BaseDao;
import dao.user.UserDao;
import pojo.User;

import java.sql.Connection;

//service层调用dao层
public class UserService {
    private UserDao userDao;
    public UserService(){
        userDao = new UserDao();
    }

    public User login(String id, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }

        //密码如果匹配，返回该user，否则返回null
        if(null != user){
            if(!user.getPassword().equals(password))
                user = null;
        }
        return user;
    }
}
