package biz.impl;


import java.util.Map;

import Entity.BookInfo;
import Util.FileUtil;
import biz.BookInfoBiz;

/**
 * ͼ��ҵ����ĵ�һ��ʵ����
 * @author 40623
 *
 */
public class BookInfoBizImplV1 implements BookInfoBiz{
	private static final long serialVersionUID = 8548729036430590874L;

	@Override
	public boolean add(BookInfo bookInfo) {
		//1,�����е�bookInfoMap���
		//2.��Map�����bookInfo����
		//3.��������дд���ļ�
		if(null==bookInfo) return false;
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null==bookInfoMap) return false;
		if(bookInfoMap.containsKey(bookInfo.getIsbn())) {
			return false;//��������Ѿ����ڵ�ISBN
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
			return false;//����ɾ�������ڵ�ISBN
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
			return null;//�����޸Ĳ����ڵ�ISBN
		}
		bookInfoMap.put(bookInfo.getIsbn(), bookInfo);
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return bookInfo;
	}

	@Override
	public BookInfo findById(String id) {
		//���һ��������������û�����壬�ֶ��׳��쳣
		throw new UnsupportedOperationException("BookInfoBiz�в�֧�ָ���ID��ѯ�Ĳ�������ʹ��findByIsbn(isbn)����");
	}

	@Override
	public Map<String, BookInfo> findAll() {
		// TODO Auto-generated method stub
		return FileUtil.readBookInfoMap();
	}

	@Override
	public boolean outStore(String isbn, int outCount) {
		//ȡ��Ҫ������鼮��Ϣ
		BookInfo bookInfo = findByIsbn(isbn);
		if(null == bookInfo) return false;//û���ҵ�Ҫ������鼮
		Map<String, BookInfo> bookInfoMap= findAll();
		if(null == bookInfoMap) return false;
		if(outCount>bookInfo.getInStoreCount()) {
			//������������ܴ��ڿ��
			return false;
		}
		//ʵ�ֳ������
		bookInfo.setInStoreCount(bookInfo.getInStoreCount()-outCount);
		//�����Ĺ����鼮��Ϣ�Żؼ�����
		bookInfoMap.put(isbn, bookInfo);
		//���ļ��и������е��鼮��Ϣ
		FileUtil.SaveBookInfoMap(bookInfoMap);
		return true;
	}

	@Override
	public boolean inStore(String isbn, int inCount) {
		Map<String, BookInfo> bookInfoMap = findAll();
		if(null == bookInfoMap) return false;
		//�жϼ������Ƿ����isbn��Ϣ����������Ҫʹ������
		if(!bookInfoMap.containsKey(isbn)) return false;
		//���ݳ���ţ���ü����ж�Ӧ���鼮����
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
