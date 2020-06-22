package Entity;
import java.io.Serializable;

/**
 * 真实的图书对象（读者借书实际借Book对象，Book包含bookInfo对象
 * @author 40623
 *
 */
public class Book implements Serializable{
	private static final long serialVersionUID = 1220648456845451310L;
	private String isbn;
	private String bookId; //再图书馆中某一本的内部编号
//	private boolean isCanBorrow;
	private BookState state;//是否可借
	private BookInfo bookInfo;
	
	@Override
	public boolean equals(Object obj) {
		//在这里验证传入的obj对象是不是同一本书
		//两个对象的BOOKID是否相同
		if(null==obj) return false;
		if(!(obj instanceof Book)) return false;
		Book book = (Book)obj;
		return bookId.equals(book.getBookId());
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public BookState getState() {
		return state;
	}
	public void setState(BookState state) {
		this.state = state;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	
	
}
/**
 * 图书状态：可借，不可借，缺货，维修
 * @author 40623
 *
 */
enum BookState{
	可借,不可借,缺货,维修
}