package exception;
/**
 * �Զ����쳣��Ϣ,�˲���û��Ȩ��
 * @author 40623
 *
 */
public class NoSuchOptPermissionException extends RuntimeException{
	private static final long serialVersionUID = 6911123393230753993L;
	public NoSuchOptPermissionException() {
		super();
		System.out.println("�˲���û��Ȩ���쳣");
	}
	public NoSuchOptPermissionException(String message) {
		super(message);
	}
}
