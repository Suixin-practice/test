package Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Entity.Book;
import Entity.BookInfo;
import Entity.User;
import Util.FileUtil;
import auth.Role;
import biz.BookInfoBiz;
import biz.impl.BookInfoBizImplV1;
import view.BookView;

public class BookTest {
	public static void main(String[] args) {
//		TestModel();
//		TestBiz();
//		TestIn_Out();
//		TestRole();
		TestUser();
	}
	public static void TestUser() {
		User user = new User(new Role("齐天大圣","oparator"));
		user.setUserName("孙悟空");
		user.setPassWord("123456");
		Set<User> userSet= new HashSet<User>();
		userSet.add(user);
		FileUtil.SaveUser(userSet);
		
		new BookView();
	}
	public static void TestRole() {
		Role role1 = new Role();
		System.out.println(role1.getName()+"\t"+role1.getKey());
		System.out.println("权限集合"+role1.getPermisstions());
		System.out.println(role1.inStore("123", 3));
	}
	public static void TestIn_Out(){
		Scanner input = new Scanner(System.in);
		BookInfoBiz bookInfoBiz = new BookInfoBizImplV1();
		//随机为某种书籍增加
		BookInfo bookInfo = new BookInfo();
		bookInfo.setIsbn("123-456");
		bookInfo.setName("三生三世");
		for(int i=0;i<5;i++) {
			Book book = new Book();
			book.setBookId((i+1)+"");
			book.setIsbn("123-456");
			bookInfo.addBook(book);
		}
		System.out.println(bookInfo.getInStoreCount());
		Map<String, BookInfo> infoMap = new HashMap<String, BookInfo>();
		infoMap.put(bookInfo.getIsbn(), bookInfo);
		//写入到文件中
		FileUtil.SaveBookInfoMap(infoMap);
		//入库操作
		System.out.println("扫描要入库的图书出版号：");
		String isbn = input.next();
		boolean isInStoreSuccess = bookInfoBiz.inStore(isbn, 10);
		System.out.println("入库是否成功"+isInStoreSuccess);
		BookInfo currBookInfo = bookInfoBiz.findByIsbn(isbn);
		System.out.println("当前库存"+currBookInfo.getInStoreCount());
		
//		boolean isOutStoreSuccess = bookInfoBiz.outStore(isbn, 10);
//		currBookInfo = bookInfoBiz.findByIsbn(isbn);
//		System.out.println("出库是否成功："+isOutStoreSuccess);
//		System.out.println("当前库存："+currBookInfo.getInStoreCount());
	}
	public static void TestBiz() {
		BookInfoBiz bookInfoBiz = new BookInfoBizImplV1();
//		bookInfoBiz.findById("123");
		
	}
	public static void TestModel() {
		BookInfo bookInfo = new BookInfo();
		bookInfo.setIsbn("123-456");
		bookInfo.setName("三生三世");
		Book book = new Book();
		book.setIsbn("123-456");//相当于录入图书的ISBN信息
		bookInfo.addBook(book);
		Map<String, BookInfo> infoMap = new HashMap<String, BookInfo>();
		infoMap.put(bookInfo.getIsbn(),bookInfo );
		FileUtil.SaveBookInfoMap(infoMap);
		System.out.println(book.getBookInfo().getName());
		
		infoMap = FileUtil.readBookInfoMap();
		System.out.println("从文件中读取出的文件信息：");
		for(String isbn: infoMap.keySet()) {
			System.out.println(isbn);
		}
	}
}
