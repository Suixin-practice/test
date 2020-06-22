package exception;
/**
 * 自定义异常信息,此操作没有权限
 * @author 40623
 *
 */
public class NoSuchOptPermissionException extends RuntimeException{
	private static final long serialVersionUID = 6911123393230753993L;
	public NoSuchOptPermissionException() {
		super();
		System.out.println("此操作没有权限异常");
	}
	public NoSuchOptPermissionException(String message) {
		super(message);
	}
}
