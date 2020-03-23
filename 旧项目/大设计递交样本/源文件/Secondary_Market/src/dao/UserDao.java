/**
 * 
 */
package dao;

import java.util.List;

import model.User;

/**
 * @author 陈起廷
 * @version 2019年4月29日
 */
public interface UserDao {
	 /**
	  * 用户登录并返回用户权限
	  * @param id
	  * @param password
	  * @return
	  */
     public String login(String id, String password);
     /**
      * 注册新用户
      * @param user
      * @return
      */
     public boolean register(User user);
     /**
      * 判断是否存在该用户
      * @param iD
      * @return
      */
     public boolean isExist(String iD);
     /**
      * 修改用户信息
      * @param user
      * @return
      */
     public boolean update(User user);
     /**
      * 修改密码 和找回密码都是同一个操作，逻辑处理不一样
      * @param id
      * @param password
     * @param email 
      * @return
      */
     public boolean revison(String id,String password, String email);
     /**
      * 上传头像
      * @param id
      * @param photo
      * @return
      */
     public boolean uploadPhoto(String id,String photo);
     /**
      * 返回账户余额
      * @param id
      * @return
      */
     public double account(String id);
     /**
      * 充值和付款
      * @param id
      * @param money
      * @return
      */
     public boolean payAndcharge(String id,  double money);
     /**
      * 根据用户id查找用户
      * @param id
      * @return
      */
     public User queryUser(String id);

     /**
      * 查找所有用户
      * @param currentPage
      * @param pageSize
      * @return
      */
     public List<User> queryAllUser(int currentPage, int pageSize);
     /**
      * 管理员修改用户决定是否能售卖商品
      * @param id
      * @return
      */
     public boolean updateUserSell(String id, String sell);
}
