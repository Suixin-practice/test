package Entity;
/**
 * 以图书的ISBN为唯一标识的图书信息类
 * 某一个ISBN会对应很多真实存在的图书对象
 * @author 40623
 *
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookInfo implements Serializable{
	private static final long serialVersionUID = -7763163826233311987L;
	private String isbn;
	private String type;	//可以从配置文件中获得
	private String auther;
	private String name;
	private String publisher;
	private int inStoreCount;
	private Date publishDate;
	private double price;
	//通过分析bookifo和book之间是一对多关系
	private List<Book> bookList;
	
	//定义构造方法，重写tostring，equals等操作
	/**
	 * 向本ISBN类别的图书信息中添加真实的图书对象
	 * @param book
	 */
	public void addBook(Book book) {
		if(null==bookList) {
			bookList = new ArrayList<>();
		}
		if(book==null) return;
		if(!isbn.equals(book.getIsbn())) return;
		//如果是同一本书就不需要添加了
		if(bookList.contains(book)) return;
		//再为已添加的图书对象设置图书信息
		book.setBookInfo(this);
		bookList.add(book);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getInStoreCount() {
		return inStoreCount;
	}
	public void setInStoreCount(int inStoreCount) {
		this.inStoreCount = inStoreCount;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
