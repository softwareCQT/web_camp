/**
 * 
 */
package service;


import dao.CommodityDao;
import dao.CommodityDaoImpl;
import model.Commodity;
import model.Page;
import util.DbUtil;

/**
 * 独属于商品表单的逻辑管理层
 * @author 陈起廷
 * @version 2019年5月1日
 */
public class CommodityServiceImpl implements CommodityService {
    /**
     * 链接dao层的数据
     */
	CommodityDao method = new CommodityDaoImpl();
	/**
	 * 商品id由控制层生成
	 */
	@Override
	public boolean addGood(Commodity goods) {
		if (goods != null)
	    {
			return method.addGood(goods);
	    }
		else 
		{  
			return false;
		}
	}

	@Override
	public boolean deleteGood(String goodsId) {
		if (goodsId != null)
		{	
			return method.deleteGood(goodsId);
		}
		else 
		{
			return false;
		}
	}
    /**
     * 商品卖家可提交,可以在审核未通过前撤回提交
     * 管理员可以审核让商品可发售，也可让商品不能销售
     * 即商品有4个审核状态  ：（允许发售、禁止发售）、（撤回审核、审核中）
     * 其中撤回审核相当于删除提交的商品信息
     */
	@Override
	public boolean sellOrNot(String check, String id) {
		//获取目前商品的返回状态
	    String nowCheck = method.queryCheck(id);
	    if (nowCheck != null)
	    {
	    	switch (nowCheck)
		    {
		      case "允许发售":if ("禁止发售".equals(check))
		                    {   
		    	                return method.sellOrNot(check, id);
		                    }
		      case "审核中":{
			    	           if ("允许发售".equals(check)||"禁止发售".equals(check))
			                   {
			    	                 return method.sellOrNot(check, id);
			                   }
			    	           else if ("撤回申请".equals(check))
			    	           {
			    	        	     return method.deleteGood(id);
			    	           }
			    	           else 
			    	           {
			    	        	      return false;
			    	           }
		                   }
			 default : return false;
		  }
	    }
	   return false;
	}
	/**
	 * 以下分页查询的当前页和数据容量已经在控制层中配置好
	 */
   /**
    * 按名字查询并将模糊数据查询分页
    */
	@Override
	public Page  queryByName(String name, Page page) {
	  name = "%"+name+"%";
	  String sql = "select count(1) from Commodity where Name like ? and Condition1='允许发售'";
	  Object[] obj = new Object[] {name};
	  //调用工具类的查询总数
	  page.setTotalData(DbUtil.totalData(sql, obj));
	  page.setList(
	    method.queryByName(
			   name, page.getCurrentPage(), page.getPageSize()
			   ));
	  //返回page给控制层
	  return page;
	}
	/**
	 * 查询所有商品
	 */
	@Override
	public Page  queryAllGoods(Page page) {
	  String sql = "select count(1) from Commodity where Condition1='允许发售'";
	  //调用工具类的查询总数
	  page.setTotalData(DbUtil.totalData(sql, null));
	  page.setList(
	    method.queryAllGoods(
	    		page.getCurrentPage(), page.getPageSize()
	    		));
	  //返回page给控制层
	  return page;
	}
    
	/**
	 * 按卖家查询商品
	 */
	@Override
	public Page  queryByOwner(String owner, Page page) {
		Object[] obj = new Object[] {owner};		
		String sql = "select count(1) from Commodity where Condition1='允许发售' and owner=?";
		 //调用工具类的查询总数
		page.setTotalData(DbUtil.totalData(sql, obj));
		page.setList(method.queryByOwner(
				owner, page.getCurrentPage(), page.getPageSize()
				));
		return page;
	}
    /**
     * 按商品类型查询商品
     */
	@Override
	public Page queryByType(String type,Page page) {
		Object[] obj = new Object[] {type};		
		String sql = "select count(1) from Commodity where Condition1='允许发售'and type=?";
		 //调用工具类的查询总数
		page.setTotalData(DbUtil.totalData(sql, obj));
		page.setList(method.queryByType(
				type, page.getCurrentPage(), page.getPageSize()
				));
		return page;
		
	}
    /**
     * 返回商品处于"允许发售"和"审核中"的商品给管理员看(禁止出售的商品不返回给管理员看了)
     */
	@Override
	public Page queryByManager(Page page) {
        String sql = "select * from Commodity where Condition1='审核中' or Condition1='允许发售' limit ?,?";
        String sql2 = "select count(1) from Commodity where Condition1='允许发售' or Condition1='审核中'";
        page.setTotalData(DbUtil.totalData(sql2, null));
        page.setList(method.queryByCheck(
        		sql,page.getCurrentPage(),page.getPageSize()
        		));
		return page;
	}

	/**
	 * 查找所有该用户的商品
	 */
	@Override
	public Page queryMyGoods(String userId, Page page) {
		String sql = "select count(1) from Commodity where Owner=?";
		String sql2 = "select * from Commodity where Owner="+userId+" order by buy_Sum desc limit ?,? ";
		page.setTotalData(DbUtil.totalData(sql, new Object[] {userId}));
		page.setList(method.queryByCheck(
				sql2,page.getCurrentPage(),page.getPageSize()
				));
		return page;
	}

	
	@Override
	public Commodity queryForShow(String id) {
		return method.queryForShow(id);
	}
}
