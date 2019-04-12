package chen.Dao;

import java.util.List;

import chen.model.User;

public interface UserDao {
	    // 用户登录
		public  Boolean login(User user);

		// 修改密码
		public  boolean updateUser(User user) throws Exception;

		// 添加用户
		public  boolean addUser(User user) throws Exception;
		
        public List<User> QueryAll();
		// 判断用户是否存在
		public boolean userIDIsExist(String ID) throws Exception;
		
		public User Query(String ID);
		
		//判断用户的权限  1代表普通用户，2代表管理员，3代表超级管理员
		public int userPriority(String name);
		
		public Boolean Beupdate(int priority, String ID);
		
} 
