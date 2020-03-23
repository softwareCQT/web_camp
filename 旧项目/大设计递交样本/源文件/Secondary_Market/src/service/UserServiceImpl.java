/**
 * 
 */
package service;

import dao.CommodityDao;
import dao.CommodityDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import model.Page;
import model.User;
import util.DbUtil;

/**
 * @author 陈起廷
 * @version 2019年5月1日
 */
public class UserServiceImpl implements UserService {
   /**
    * new一个dao层的方法来进行调用
    */
	UserDao method = new UserDaoImpl();
	/**
	 * 登录成功则返回权限，不然为null
	 */
	@Override
	public String login(String id, String password) {
		return method.login(id, password);
	}
    /**
     * 注册操作,控制层检验一下数据是否出错
     */
	@Override
	public boolean register(User user) {
		//判断是否存在此用户,
		if (method.isExist(user.getId()))
	    {
			return false;
		}
		else
		{
			return method.register(user);
		}
	}
	/**
	 * 传入的照片在控制层已经被读取并存储，此处只需把相对位置传给数据库便可
	 */
	@Override
	public boolean updatePhoto(String id, String photo) {
		return method.uploadPhoto(id, photo);
	}
	/**
	 * 若返回空，则说明数据库没有此人
	 */
	@Override
	public User queryUser(String id)
	{   
		return method.queryUser(id);
	}
	/**
	 * 修改用户信息，需要传进可以修改的用户数据
	 */
	@Override
	public boolean update(User user) {
		return method.update(user);
	}
    /**
     * 密码要规范,由控制层判断
     */
	@Override
	public boolean revison(String id, String password,String email) {
		return method.revison(id, password, email);
	}

	@Override
	public Page queryAllUsers(Page p) {
      String sql = "select count(1) from User where Priority='普通用户'";
      //设置总页数
	  p.setTotalData(DbUtil.totalData(sql, null));
	  p.setList(method.queryAllUser(p.getCurrentPage(), p.getPageSize()));
	  return p;
	}
	/**
	 * 管理员功能
	 * 修改并返回是否修改成功
	 * 且用户不具有销售权限时他的商品全部都要下架，即该用户的所有商品字段全部要修改为"禁止发售"
	 * 用户被重新赋予销售权限时全部商品可以再次被审核
	 */
	@Override
	public boolean updateUserSell(String id ,String sell) {
	   //修改用户的销售权限为无，他本来上传的商品要全部下架
		//查询该用户在商品表中是否上传有商品
	   String sql = "select count(1) from Commodity where Owner=?";
	   Object[] obj = new Object[] {id};
	   int result = DbUtil.totalData(sql, obj);
	   //对商品是否可售进行操作
	   CommodityDao method2 = new CommodityDaoImpl();
	   final String flag = "否";
	   //判断管理员的操作
	   if ( flag.equals(sell))
	   {  //修改用户销售权限成功
			  if (method.updateUserSell(id, sell))
			  {   //result为0则该用户没有上传过商品,
				  if (result == 0)
				  {
					  return true;
				  }
				  else
				  {
					  //否则需要修改他上传的所有商品的权限字段
					  return method2.UpAndDown("禁止发售", id);
				  }	  
			  }
			  else
			  {
				  return false;
			  }
	   }
	   else 
	   {
		   if (method.updateUserSell(id, sell))
			  {   //result为0则该用户没有上传过商品,
				  if (result == 0)
				  {
					  return true;
				  }
				  else
				  {
					  //否则需要修改该用户上传的所有商品的权限字段为'审核中'
					  return method2.UpAndDown("审核中", id);
				  }	  
			  }
		 else
	    {
		   return false;
		}
     }
  }
}
