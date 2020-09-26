package com.javaweb.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.UserModel;
import com.javaweb.service.INewsService;
@WebServlet(urlPatterns = {"/trang-chu", "/thoat"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 7812242845740990735L;
	
	@Inject
	INewsService newsSV;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel model = new UserModel();
		model.setFullName("Hai Nonggggg");
		request.setAttribute("model", model);
		
//		newsSV.testinject();
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
