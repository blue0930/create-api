package ren.xiangmu.creatorapi.utils;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtils {

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Timestamp now() {
		//FIXME 需要从数据库获取
		return new Timestamp(new Date().getTime());
	}
}
