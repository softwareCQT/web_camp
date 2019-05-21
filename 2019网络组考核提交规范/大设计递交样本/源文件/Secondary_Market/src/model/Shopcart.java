/**
 * 
 */
package model;

import java.sql.Timestamp;

/**
 * @author 陈起廷
 * @version 2019年4月30日
 */
public class Shopcart {
	  
      private String Commodity_Id;
      private String User_Id;
      private int Sum;
      private Timestamp Date;
     
      
	    public Shopcart()
	    {
	    	
	    }
	    public Shopcart(String commodity_Id, String user_Id, int sum, Timestamp date) {
			Commodity_Id = commodity_Id;
			User_Id = user_Id;
			Sum = sum;
			Date = date;
		}
		public Shopcart(String commodity_Id, String user_Id) {
			Commodity_Id = commodity_Id;
			User_Id = user_Id;
			
		}
		public String getCommodity_Id() {
			return Commodity_Id;
		}
		public void setCommodity_Id(String commodity_Id) {
			Commodity_Id = commodity_Id;
		}
		public String getUser_Id() {
			return User_Id;
		}
		public void setUser_Id(String user_Id) {
			User_Id = user_Id;
		}
		public int getSum() {
			return Sum;
		}
		public void setSum(int sum) {
			Sum = sum;
		}
		/**
		 * @return date
		 */
		public Timestamp getDate() {
			return Date;
		}
		/**
		 * @param date 要设置的 date
		 */
		public void setDate(Timestamp date) {
			Date = date;
		}
	
      
}
