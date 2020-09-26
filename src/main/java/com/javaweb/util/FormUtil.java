package com.javaweb.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> clazz, HttpServletRequest request) {
		T object = null;
		try {
			object = clazz.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (Exception e) {
			System.out.println("BeanUtil Error:");
			System.out.println(e.getMessage());
		}
		return object;
	}
//	cách 2 tương tự như trên nhưng truyền vào là 1 object đã được khởi tạo
	@SuppressWarnings("unchecked")
	public static <T> T toModel2(T object, HttpServletRequest request) {
		try {
			BeanUtils.populate(object, request.getParameterMap());
		} catch (Exception e) {
			System.out.println("BeanUtil Error:");
			System.out.println(e.getMessage());
		}
		return object;
	}
// END cách 2 tương tự như trên nhưng truyền vào là 1 object đã được khởi tạo
}
