package Dao;

import java.util.List;

import model.User;
import model.photo;

//�û����Խ��еĲ���
public interface userDao {

    
    public boolean update(User Y);// �����û���Ϣ
    
    public boolean login(String ID,String password);//��½�û���֤
    
    public boolean add(User Y);//�����û�
    
    public boolean IsExist(String ID);//����û��治����
    
    public User query(String ID);  //��ѯ��������
    
}
