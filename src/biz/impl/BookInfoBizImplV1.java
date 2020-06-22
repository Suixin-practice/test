package biz.impl;


import java.util.Map;

import Entity.BookInfo;
import Util.FileUtil;
import biz.BookInfoBiz;

/**
 * 图书业务类的第一版实现类
 * @author 40623
 *
 */
public class BookInfoBizImplV1 implements BookInfoBiz{
	private static final long serialVersionUID = 8548729036430590874L;

	@Override
	public boolean add(BookInfo bookInfo) {
		//1,将所有的bookInfoMap获得
		//2.向Map中添加bookInfo对象
		//3.将对象重写写回文件
		if(null==bookInfo) return false;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null==bookInfoMap) return false;
		if(bookInfoMap.containsKey(bookInfo.getIsbn())) {
			return false;//不能添加已经存在的ISBN
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean del(BookInfo bookInfo) {
		if(null==bookInfo) return false;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null==bookInfoMap) return false;
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())) {
			return false;//不能删除不存在的ISBN
		}
		bookInfoMap.remove(bookInfo.getIsbn());
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return false;
	}

	@Override
	public BookInfo update(BookInfo bookInfo) {
		if(null==bookInfo) return null;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null==bookInfoMap) return null;
		if(!bookInfoMap.containsKey(bookInfo.getIsbn())) {
			return null;//不能修改不存在的ISBN
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return bookInfo;
	}

	@Override
	public BookInfo findById(String id) {
		//如果一个方法再子类中没有意义，手动抛出异常
		throw new UnsupportedOperationException("BookInfoBiz中不支持根据ID查询的操作，请使用findByIsbn(isbn)方法");
	}

	@Override
	public Map<String, BookInfo> findAll() {
		// TODO Auto-generated method stub
		return FileUtil.readBookInfoMap();
	}

	@Override
	public boolean outStore(String isbn, int outCount) {
		//取出要出库的书籍信息
		BookInfo bookInfo = findByIsbn(isbn);
		if(null == bookInfo) return false;//没有找到要出库的书籍
		Map<String, BookInfo> bookInfoMap= findAll();
		if(null == bookInfoMap) return false;
		if(outCount>bookInfo.getInStoreCount()) {
			//出库的数量不能大于库存
			return false;
		}
		//实现出库操作
		bookInfo.setInStoreCount(bookInfo.getInStoreCount()-outCount);
		//将更改过的书籍信息放回集合中
		bookInfoMap.put(isbn, bookInfo);
		//再文件中更新所有的书籍信息
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean inStore(String isbn, int inCount) {
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		//判断集合中是否存在isbn信息，不存在需要使用新增
		if(!bookInfoMap.containsKey(isbn)) return false;
		//根据出版号，获得集合中对应的书籍对象
		BookInfo bookInfo = bookInfoMap.get(isbn);
		bookInfo.setInStoreCount(bookInfo.getInStoreCount()+inCount);
		bookInfoMap.put(isbn, bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public BookInfo findByIsbn(String isbn) {
		if(null==isbn||"".equals(isbn)) return null;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return null;
		if(!bookInfoMap.containsKey(isbn)) return null;
		return bookInfoMap.get(isbn);
	}
	
}
