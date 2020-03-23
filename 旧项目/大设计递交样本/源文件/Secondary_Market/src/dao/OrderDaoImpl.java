/**
 * 
 */
package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.RequestContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Order;
import util.DbUtil;

/**
 * @author 陈起廷
 * @version 2019年4月30日
 */
public class OrderDaoImpl implements OrderDao{
	/**
	 * 订单状态信息有（审核中、审核完成、审核失败、已发货、交易完成、交易取消）
	 * 添加订单
	 */
	@Override
	public boolean addOrder(Order order) {
	    Object[] obj = new Object[] {
	    	order.getId(), order.getCreateTime(), order.getCondition(),
	    	order.getOwner(), order.getBuyer(), order.getSendAddress(),
	    	order.getFromAddress(), order.getPhone(), order.getRelate_Message(),
	    	order.getPrice(),order.getGoodId(),order.getGoodSum()
	    };
	   String sql = "insert into Order1 values(?,?,?,?,?,?,?,?,?,?,?,?)";
	   return DbUtil.update(sql, obj);
	}
    /**
     * 查询所有，卖家和买家都可查询所有订单并分页显示
     */
	@Override
	public List<Order> queryByPage(String id, int currentPage, int pageSize) {
		Object[] obj = new Object[] {id,id,(currentPage-1)*pageSize,pageSize};
		String sql = "select * from Order1"
				+ " where Owner=? or Buyer=? order by Createtime desc limit ?,?";
		List<Order> list = null;
		//调用工具类获取查询结果集合
		ResultSet rs = DbUtil.query(sql, obj);
			try {
				//判断rs是否有数据，如果有则滚回初始位置进行循环，没有则没有生成list，返回null
				if (rs != null && rs.next())
				{   
				 rs.beforeFirst();
				 list = new ArrayList<>();
				 while (rs.next())
				 {   //从结果集合中获取订单的全部信息
					//生成一个order的list集合返回给前台呈现
					 String iD = rs.getString(1);
					 Timestamp createTime = rs.getTimestamp(2);
					 String condition = rs.getString(3);
					 String owner = rs.getString(4);
					 String buyer = rs.getString(5);
					 String sendAddress = rs.getString(6);
					 String fromAddress = rs.getString(7);
					 String phone = rs.getString(8);
					 String relateMessage = rs.getString(9);
					 double price = rs.getDouble(10);
					 String goodId = rs.getString(11);
					 int goodSum = rs.getInt(12);
					 list.add(new Order(
					 iD, createTime, condition, owner, buyer, sendAddress,
					 fromAddress, phone, relateMessage, price, goodId, goodSum
							 ));
				   }
		        }
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally{
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
			}
	return list;
}
   
	/**
	 * 买家修改订单,可以修改收件人的地址、收件人的电话号码、申请购买的信息
	 */
	@Override
	public boolean buyerUpdate(String sendAddress, String phone, 
			String relateMessage, String id) {
		Object[] obj = new Object[] {
				sendAddress, phone,	relateMessage,id
		};
		String sql = "update Order1 set SendAddress=?,"
				+ "Phone=?,Relate_Message=? where Id=?";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 卖家修改订单的发送地址
	 */
	@Override
	public boolean ownerUpdate(String fromAddress,String id) {
		Object[] obj = new Object[] {fromAddress, id};
		String sql = "update Order1 set FromAddress=? where Id=?";
		return DbUtil.update(sql, obj);
	}
    /**
     * 修改订单状态，买家只能修改为（确认收货、交易失败）
     * 卖家只能修改为（审核失败、已发货）
     */
	@Override
	public boolean changeCondition(String condition,String id) {
		Object[] obj = new Object[] {condition, id};
		String sql = "update Order1 set Condition1=? where Id=?";
		return DbUtil.update(sql, obj);
	}
	/**
	 * 返回订单的状态给服务层，方便逻辑分析
	 * id为订单id
	 */
	@Override
	public String nowCondition(String id)
	{   String result = null;
        Object[] obj = new Object[] {id};
        String sql = "select Condition1 from Order1 where Id=?";
        ResultSet rs = DbUtil.query(sql, obj);
       
		try {
				if (rs != null&&rs.next())
				 {
					result = rs.getString(1);
				 }
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return null;
			}finally {
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
			}
		return result;
	}
	/**
	 * 查询一个订单的信息
	 */
	@Override
	public Order query(String id) {
		Object[] obj = new Object[] {id};
		String sql = "select * from Order1 where Id=?";
		//用来返回数据做准备
		Order order = null;
		ResultSet rs = DbUtil.query(sql, obj);
		//调用工具类获取查询结果集合
		try {
			//判断rs是否有数据，如果有则滚回初始位置进行循环，返回null
			if (rs != null && rs.next())
			{   
				rs.beforeFirst();
				while (rs.next())
				{   //从结果集合中获取订单的全部信息
					//生成一个order的list集合返回给前台呈现
					 String iD = rs.getString(1);
					 Timestamp createTime = rs.getTimestamp(2);
					 String condition = rs.getString(3);
					 String owner = rs.getString(4);
					 String buyer = rs.getString(5);
					 String sendAddress = rs.getString(6);
					 String fromAddress = rs.getString(7);
					 String phone = rs.getString(8);
					 String relateMessage = rs.getString(9);
					 double price = rs.getDouble(10);
					 String goodId = rs.getString(11);
					 int goodSum = rs.getInt(12);
					 order = new Order(
						iD, createTime, condition, owner, buyer, sendAddress,
						fromAddress, phone, relateMessage, price, goodId, goodSum
								 );
				}
			 }
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
			}
	 return order;
	}
	/**
	 * 得到xsl文件，并把文件的绝对地址传给服务端
	 */
	@Override
	public String getFile(String id, String url) {
		String sql = "select * from Order1 where Buyer=? or Owner=? order by CreateTime desc";
		//执行查询命令查询信息
		Object[] obj = new Object[] {id,id};
		//定义相对地址给用户
		final String toUserFile = "/DownLoadOrder/"+id+".xlsx";
		//定义文件地址，此处为绝对地址/Secondary_Market/DownLoadOrder
		final String readUrl = url + "/" +id+".xlsx";
		HSSFWorkbook workbook = null;
		ResultSet rs = DbUtil.query(sql, obj);
		try {
			 workbook = new HSSFWorkbook();
			String[] tempo;
			int rowNum = 1;
			try {
			
				//获取Connection对象所连接的数据库的元数据。元数据包括关于数据库的表
				ResultSetMetaData rsm = rs.getMetaData();
				//创建一个名字为(id+Order)的表原名字默认为sheet
				HSSFSheet sheet = workbook.createSheet(id+"Order");
				//getRowCount 获取数据库表行的数量  getColumnCount获取列的数量
				int columnCount = rsm.getColumnCount();
				try {
					//在Excel里创建行,从0开始
					HSSFRow row1 = sheet.createRow(0);
					for (int i = 1;i<=columnCount;i++) 
					{
						//让Excel自动适应列宽
						sheet.autoSizeColumn(i);
						//获取表列头的名字
						String columnName = rsm.getColumnName(i);
						//创建行的单元格,也是从0开始
						HSSFCell cell1 = row1.createCell(i - 1);
						//设置单元格内容
							    cell1.setCellValue(columnName);
							}
					} catch (Exception e) {
					   e.printStackTrace();
					   return null;
					}
				   //循环获取数据库查到的数据
					while (rs.next()) 
					{
						//每循环一次创建一行从 1 开始
						HSSFRow row = sheet.createRow(rowNum);
						tempo = new String[columnCount];
						for (int i = 0; i < columnCount; i++) 
						{
							//创建行的单元格,从0开始
							HSSFCell cell = row.createCell(i);
							//让Excel自动适应列宽
							sheet.autoSizeColumn(i);
							//getMetaData().getColumnName(i);字段名
							String columnName = rs.getMetaData().getColumnName(i + 1);
							tempo[i] = rs.getString(columnName);
							//设置单元格内容
							cell.setCellValue(tempo[i]);
						}
						//每次循环结束后要使数组为空，方便存储数据库表中下一行的数值
						tempo = null;
						//rowNum++ 下一次循环创建的行向下
						rowNum++;
			     }
			} catch (SQLException e) {
					e.printStackTrace();
					return null;
			}finally {
				DbUtil.close(DbUtil.pstmt, DbUtil.conn, rs);
				
			}
		  OutputStream out = new FileOutputStream(readUrl);
		  //保存Excel文件
		  workbook.write(out);
		  out.close();//关闭文件流
		} catch (Exception e) {
		e.printStackTrace();
		return null;
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
    return toUserFile;
  }
}

