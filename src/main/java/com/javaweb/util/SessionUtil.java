package com.javaweb.util;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	private static SessionUtil sessionUtil = null;
//	hàm kiểm tra xem session đã đc khởi tạo hay chưa. nếu rồi thì trả về cái cũ và ngược lại
	public static SessionUtil getInstance() {
		if(sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}
//	END hàm kiểm tra xem session đã đc khởi tạo hay chưa. nếu rồi thì trả về cái cũ và ngược lại
	
//	hàm thêm data vào session
	public void putValue(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
//	END hàm thêm data từ session
	
//	hàm lấy data từ session
	public Object getValue(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
//	END lấy thêm data từ session
	
//	hàm xóa data trong session
	public void removeValue(HttpServletRequest request, String key) {
		request.removeAttribute(key);
	}
//	END hàm xóa data trong session
}
