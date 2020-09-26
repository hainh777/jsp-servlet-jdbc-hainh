package com.javaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.UserModel;
import com.javaweb.service.IUserService;
import com.javaweb.util.FormUtil;

@WebServlet({ "/dang-nhap", "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	@Inject
	IUserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String alert = request.getParameter("alert");
		String message = request.getParameter("message");
			if(message != null && alert != null) {
				request.setAttribute("message",resourceBundle.getString(message) );
				request.setAttribute("alert", alert);
			}
		RequestDispatcher rd = request.getRequestDispatcher("/views/login/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel loginInfo = FormUtil.toModel(UserModel.class, request);// tự động map các parameter vào thành model bằng name (cách 1)
//		UserModel loginInfo = FormUtil.toModel2(new UserModel(), request); // cách 2. như trên nhưng khác phần Truyền tham số là model đã được khơi tạo
		UserModel model = userService.findByUsernameAndPasswordAndStatus(loginInfo.getUserName(), loginInfo.getPassword(), 1);
		if(model != null) {
			if(model.getRole().getCode().equals("USER")) {
				response.sendRedirect(request.getContextPath()+"/trang-chu");
			}else if(model.getRole().getCode().equals("ADMIN")) {
				response.sendRedirect(request.getContextPath()+"/admin");
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
		}
	}

}

//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	String action = request.getParameter("action");
//	if (action != null && action.equals("login")) {
//		UserModel model = FormUtil.toModel(UserModel.class, request);
//		model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
//		if (model != null) {
//			SessionUtil.getInstance().putValue(request, "USERMODEL", model);
//			if (model.getRole().getCode().equals("USER")) {
//				response.sendRedirect(request.getContextPath()+"/trang-chu");
//			} else if (model.getRole().getCode().equals("ADMIN")) {
//				response.sendRedirect(request.getContextPath()+"/admin-home");
//			}
//		} else {
//			response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
//		}
//	}
//}

