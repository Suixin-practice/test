package biz;

import biz.impl.BookInfoBizImplV1;
import biz.impl.UserBizImplV1;

/**
 * ҵ�񹤳���
 * @author 40623
 *
 */
public class BizFactory {
	/**
	 * ����ҵ������,�����Ӧ��ҵ�����ʵ��
	 * @param bizName
	 * @return
	 */
	public static Biz getBiz(String bizName) {
		switch (bizName.toLowerCase()) {
		case "bookinfobiz":
			return new BookInfoBizImplV1();
		case "userbiz":
			return new UserBizImplV1();
		default:
			return null;
		}
	}
}
