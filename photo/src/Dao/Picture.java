package Dao;

import java.util.List;

import model.photo;

public interface Picture {
	public boolean delete(photo P);  //ɾ����Ƭ
	
	public boolean add(photo P);  //�����Ƭ
	
	public int photoTotal(String ID);//��ѯ�û���Ƭ����������

	public List<photo> queryphoto(String ID); //��ѯ���˵�ȫ����Ƭ
}
