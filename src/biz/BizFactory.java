package biz;

import biz.impl.BookInfoBizImplV1;
import biz.impl.UserBizImplV1;

/**
 * 业务工厂类
 * @author 40623
 *
 */
public class BizFactory {
	/**
	 * 根据业务名称,获得相应的业务类的实现
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
