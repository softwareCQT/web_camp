package Dao;

import java.util.List;

import model.User;
import model.photo;

//用户可以进行的操作
public interface userDao {

    
    public boolean update(User Y);// 更改用户信息
    
    public boolean login(String ID,String password);//登陆用户验证
    
    public boolean add(User Y);//增加用户
    
    public boolean IsExist(String ID);//检查用户存不存在
    
    public User query(String ID);  //查询个人资料
    
}
