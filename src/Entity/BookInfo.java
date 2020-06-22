package Entity;
/**
 * ��ͼ���ISBNΪΨһ��ʶ��ͼ����Ϣ��
 * ĳһ��ISBN���Ӧ�ܶ���ʵ���ڵ�ͼ�����
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
	private String type;	//���Դ������ļ��л��
	private String auther;
	private String name;
	private String publisher;
	private int inStoreCount;
	private Date publishDate;
	private double price;
	//ͨ������bookifo��book֮����һ�Զ��ϵ
	private List<Book> bookList;
	
	//���幹�췽������дtostring��equals�Ȳ���
	/**
	 * ��ISBN����ͼ����Ϣ�������ʵ��ͼ�����
	 * @param book
	 */
	public void addBook(Book book) {
		if(null==bookList) {
			bookList = new ArrayList<>();
		}
		if(book==null) return;
		if(!isbn.equals(book.getIsbn())) return;
		//�����ͬһ����Ͳ���Ҫ�����
		if(bookList.contains(book)) return;
		//��Ϊ����ӵ�ͼ���������ͼ����Ϣ
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
