/**
 * 
 */
package service;

import model.Page;
import model.User;

/**
 * @author 陈起廷
 * @version 2019年5月1日
 * 独属于用户表的用户服务
 */
public interface UserService {
   /**
    * 用户登录
    * @param id
    * @param password
    * @return
    */
   public String login(String id, String password);
   /**
    * 用户注册 
    * @param user
    * @return
    */
   public boolean register(User user);
   /**
    * 用户更换头像
    * @param id
    * @param photo
    * @return
    */
   public boolean updatePhoto(String id, String photo);
   /**
    * 查询某个用户
    * @param id
    * @return
    */
   public User queryUser(String id);
   /**
    * 修改用户信息
    * @param user
    * @return
    */
   public boolean update(User user);
   /**
    * 修改用户密码
    * @param id
    * @param password
    * @return
    */
   public boolean revison(String id,String password,String email);
   /**
    * 返回所有用户的信息并分页显示
    * @param p
    * @return
    */
   public Page queryAllUsers(Page p);
   /**
    * 修改用户购买权限
    * @param id
    * @return
    */
   public boolean updateUserSell(String id, String sell);
}
