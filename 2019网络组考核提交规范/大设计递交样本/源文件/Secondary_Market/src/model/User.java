
 
package model;
/**
 * @author 陈起廷
 * @version 2019年4月29日下午7:54:48
 * 对应数据库中的User表
 */
public class User {
	/**
	 * 用户的账号、密码、性别、Email、名字、电话号码、家庭地址
	 * 作为卖家获得的星星、账户余额
	 */
    private String Id;
    private String Password;
    private String Sex;
    private String Email;
    private String Name;
    private String Phone;
    private String Priority;
    private int Star;
    private double Money;
    private String Photo;
    private String Sell;
    /**
     * 返回个人信息构造
     */
	public User(String id, String sex, String email, String name, String phone, String priority,
			int star, String photo, String sell) {
		Id = id;
		Sex = sex;
		Email = email;
		Name = name;
		Phone = phone;
		Priority = priority;
		Star = star;
		Photo = photo;
		Sell = sell;
	}
    //全构造
    public User(String id, String password, String sex, String email, String name, String phone, String priority,
			int star, double money, String photo,String sell) {
		Id = id;
		Password = password;
		Sex = sex;
		Email = email;
		Name = name;
		Phone = phone;
		Priority = priority;
		Star = star;
		Money = money;
		Photo = photo;
		Sell = sell;
		
	}
	/**
     * 注册构造
    */
    public User(String id, String password, String sex, 
    		String email, String name, 
    		String phone, String priority,
    		double money,int star,String sell) {
    	
		Id = id;
		Password = password;
		Sex = sex;
		Email = email;
		Name = name;
		Phone = phone;
		Priority = priority;
		Money = money;
		Star = star;
		Sell = sell;
	}
	
	/**
	 * @param name2
	 * @param email2
	 * @param phone2
	 * @param sex2
	 */
	public User(String name, String email, String phone, String sex) {
		Sex = sex;
		Email = email;
		Name = name;
		Phone = phone;
	}

	public int getStar() {
		return Star;
	}
	public void setStar(int star) {
		Star = star;
	}
	public double getMoney() {
		return Money;
	}
	public void setMoney(double money) {
		Money = money;
	}
	/**
     * 权限分为用户和管理员，游客可以查询网页和商品信息
     * 生成set和get方法
     */
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		this.Phone = phone;
	}
	public String getPriority() {
		return Priority;
	}
	public void setPriority(String priority) {
		Priority = priority;
	}
    public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getSell() {
		return Sell;
	}

	public void setSell(String sell) {
		Sell = sell;
	}

    
}
