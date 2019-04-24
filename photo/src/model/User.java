package model;

import java.util.List;

//±‡–¥”√ªß¿‡
public class User {
   public User(String id, String password, String sex, String name) {
		this.id = id;
		this.password = password;
		this.sex = sex;
		this.name = name;
	}
   public User() {}
   public User(String id, String sex, String name) {
		this.id = id;
		this.sex = sex;
		this.name = name;
	}
   private String id;        
   private String password;
   private String sex;
   private String name;
   private List<photo> photo;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<photo> getPhoto() {
	return photo;
}
public void setPhoto(List<photo> photo) {
	this.photo = photo;
}
   
}
