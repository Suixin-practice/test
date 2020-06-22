package biz;
/**
 * ����ҵ����ĸ��ӿ�
 * ����������ҵ���ͨ�ò���
 * @author 40623
 *
 */

import java.io.Serializable;
import java.util.Map;

public interface Biz<T> extends Serializable{//ʹ�÷��ͱ��
	/** ͨ�õ���ӷ���*/
	public boolean add(T t);
	/**ͨ�õ�ɾ������*/
	public boolean del(T t);
	/**ͨ�õ��޸����ݷ���*/
	public T update(T t);
	/**ͨ��Id��ѯ*/
	public T findById(String id);
	/**��ѯ�������ݣ���MAP��ʽ����*/
	public Map<String, T> findAll();
	
}
