/**
 * 
 */
package model;

/**
 * @author 陈起廷
 * @version 2019年4月29日
 */
public class Commodity {
	/**
	 * 商品Id，描述信息、商品库存总数、价格、照片、购买量、
	 * 商品类型（数码产品、运动健康、礼品箱包、鞋靴、钟表、其他）、
	 * 商品发布人、检查状态（授权、发布审核、禁止发布）
	 */
     private String Id;
     private String Name;
     private String Message;
     private int Sum;
     private double Price;
     private String Photo;
     private int Buy_Sum;
     private String Type;
     private String Owner;
     private String Condition;
     
     public Commodity(String id, String name, String message, int sum, 
    		 double price, String photo,
    		 int buy_Sum, String type,
			 String owner, String condition) {
		Id = id;
		Message = message;
		Sum = sum;
		Price = price;
		Photo = photo;
		Buy_Sum = buy_Sum;
		Type = type;
		Owner = owner;
		Condition = condition;
		Name = name;
	}
	/**
      * 
      * get和set方法
      */
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getSum() {
		return Sum;
	}
	public void setSum(int sum) {
		Sum = sum;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public int getBuy_Sum() {
		return Buy_Sum;
	}
	public void setBuy_Sum(int buy_Sum) {
		Buy_Sum = buy_Sum;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getCondition() {
		return Condition;
	}
	public void setCondition(String check) {
		Condition = check;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		Name = name;
	}
}
