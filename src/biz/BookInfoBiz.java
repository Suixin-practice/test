package biz;

import Entity.BookInfo;

/**
 * ͼ��ҵ����Ľӿ�
 * ��ɾ�Ĳ�
 * @author 40623
 *
 */
public interface BookInfoBiz extends Biz<BookInfo>{
	public BookInfo findByIsbn(String isbn);
	/**
	 * ����
	 * @param isbn
	 * @param outCount ��������
	 * @return �Ƿ�ɹ�
	 */
	public boolean outStore(String isbn,int outCount);
	/**
	 * ���
	 * @param isbn
	 * @param inCount�������
	 * @return �Ƿ�ɹ�
	 */
	public boolean inStore(String isbn,int inCount);
}
