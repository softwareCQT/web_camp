package Dao;

import java.util.List;

import model.photo;

public interface Picture {
	public boolean delete(photo P);  //删除照片
	
	public boolean add(photo P);  //添加照片
	
	public int photoTotal(String ID);//查询用户照片的总数据量

	public List<photo> queryphoto(String ID); //查询个人的全部照片
}
