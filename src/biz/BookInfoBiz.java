package biz;

import Entity.BookInfo;

/**
 * 图书业务类的接口
 * 增删改查
 * @author 40623
 *
 */
public interface BookInfoBiz extends Biz<BookInfo>{
	public BookInfo findByIsbn(String isbn);
	/**
	 * 出库
	 * @param isbn
	 * @param outCount 出库数量
	 * @return 是否成功
	 */
	public boolean outStore(String isbn,int outCount);
	/**
	 * 入库
	 * @param isbn
	 * @param inCount入库数量
	 * @return 是否成功
	 */
	public boolean inStore(String isbn,int inCount);
}
