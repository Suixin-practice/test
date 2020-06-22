package biz;
/**
 * 所有业务类的父接口
 * 定义了所有业务的通用操作
 * @author 40623
 *
 */

import java.io.Serializable;
import java.util.Map;

public interface Biz<T> extends Serializable{//使用泛型编程
	/** 通用的添加方法*/
	public boolean add(T t);
	/**通用的删除方法*/
	public boolean del(T t);
	/**通用的修改数据方法*/
	public T update(T t);
	/**通过Id查询*/
	public T findById(String id);
	/**查询所有内容，以MAP形式返回*/
	public Map<String, T> findAll();
	
}
