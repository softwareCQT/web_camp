package chen.model;

public class User {

  private String ID;
  private String PWD;
  private int priority;
  private String name;
  //四大构造方法
  public User(String iD, String pWD, int priority, String name) {
		ID = iD;
		PWD = pWD;
		this.priority = priority;
		this.name = name;
	}
  public User(String iD,String name ,int priority ) {
		ID = iD;
		this.priority = priority;
		this.name = name;
	}

  public User(String iD, String pWD) {
		ID = iD;
		PWD = pWD;
	}
  public User(String iD, String pWD,String name)
  {
	  ID = iD;
	  this.name = name;
	  PWD = pWD;
  }
  public User() {}
  
  public String getID() {
	return ID;
  }
  public void setID(String iD) {
	ID = iD;
  }
  public String getPWD() {
	return PWD;
  }
  public void setPWD(String pWD) {
	PWD = pWD;
 }
  public int getPriority() {
	return priority;
  }
  public void setPriority(int priority) {
	this.priority = priority;
 }
  public String getName() {
	return name;
 }
  public void setName(String name) {
	this.name = name;
}
}
