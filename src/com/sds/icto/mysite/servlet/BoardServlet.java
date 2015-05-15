package com.sds.icto.mysite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.servlet.action.board.ActionFactory;
import com.sds.icto.web.Action;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding( "utf-8" );
			
			ActionFactory af = ActionFactory.getInstance();
			
			String a = request.getParameter( "a" ); 
			Action action = af.getAction( a );
			action.execute(request, response);
			
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
}