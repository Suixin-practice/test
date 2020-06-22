package Entity;
import java.io.Serializable;

/**
 * ��ʵ��ͼ����󣨶��߽���ʵ�ʽ�Book����Book����bookInfo����
 * @author 40623
 *
 */
public class Book implements Serializable{
	private static final long serialVersionUID = 1220648456845451310L;
	private String isbn;
	private String bookId; //��ͼ�����ĳһ�����ڲ����
//	private boolean isCanBorrow;
	private BookState state;//�Ƿ�ɽ�
	private BookInfo bookInfo;
	
	@Override
	public boolean equals(Object obj) {
		//��������֤�����obj�����ǲ���ͬһ����
		//���������BOOKID�Ƿ���ͬ
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
 * ͼ��״̬���ɽ裬���ɽ裬ȱ����ά��
 * @author 40623
 *
 */
enum BookState{
	�ɽ�,���ɽ�,ȱ��,ά��
}