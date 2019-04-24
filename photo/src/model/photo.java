package model;
 //图片地址和所属用户
public class photo {
	private String url;
    private String user_id;
    public photo(String url, String user_id) {
		this.url = url;
		this.user_id = user_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
