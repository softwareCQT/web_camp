/**
 * 
 */
package model;

import java.sql.Timestamp;

/**
 * @author 陈起廷
 * @version 2019年4月29日
 */
public class Order {
	/**
	 * 商品Id,订单生成时间、订单状态（审核中、审核完成、审核失败、订单完成）、卖家账号和买家账号
	 * 卖家的发送地址和买家的地址、电话号码、买家发出订单请求的阐述信息
	 */
    private String Id;
    private Timestamp CreateTime;
    private String Condition;
    private String Owner;
    private String Buyer;
    private String SendAddress;
    private String FromAddress;
    private String Phone;
    private String Relate_Message;
    private double Price;
    private String GoodId;
    private int GoodSum;
    
    
	public Order(String id, Timestamp createTime, String condition, String owner, String buyer, String sendAddress,
			String fromAddress, String phone, String relate_Message, double price, String goodId, int goodSum) {
		Id = id;
		CreateTime = createTime;
		Condition = condition;
		Owner = owner;
		Buyer = buyer;
		SendAddress = sendAddress;
		FromAddress = fromAddress;
		Phone = phone;
		Relate_Message = relate_Message;
		Price = price;
		GoodId = goodId;
        GoodSum = goodSum;
	}
	/**
     * 订单生成时间格式为 xxxx-xx-xx xx：xx：xx
     */
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Timestamp getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Timestamp createTime) {
		CreateTime = createTime;
	}
	public String getCondition() {
		return Condition;
	}
	public void setCondition(String condition) {
		Condition = condition;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getBuyer() {
		return Buyer;
	}
	public void setBuyer(String buyer) {
		Buyer = buyer;
	}
	public String getSendAddress() {
		return SendAddress;
	}
	public void setSendAddress(String sendAddress) {
		SendAddress = sendAddress;
	}
	public String getFromAddress() {
		return FromAddress;
	}
	public void setFromAddress(String fromAddress) {
		FromAddress = fromAddress;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getRelate_Message() {
		return Relate_Message;
	}
	public void setRelate_Message(String relate_Message) {
		Relate_Message = relate_Message;
	}
	/**
	 * @return price
	 */
	public double getPrice() {
		return Price;
	}
	/**
	 * @param price 要设置的 price
	 */
	public void setPrice(double price) {
		Price = price;
	}
	public String getGoodId() {
		return GoodId;
	}
	public void setGoodId(String goodId) {
		GoodId = goodId;
	}
	public int getGoodSum() {
		return GoodSum;
	}
	public void setGoodSum(int goodSum) {
		GoodSum = goodSum;
	}
    
    
}
