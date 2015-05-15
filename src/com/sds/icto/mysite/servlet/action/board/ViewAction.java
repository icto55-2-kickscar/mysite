package com.sds.icto.mysite.servlet.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.vo.BoardVo;
import com.sds.icto.web.Action;
import com.sds.icto.web.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException,
			IOException {
		Long boardNo = 0L;
		
		try {
			boardNo = Long.parseLong( request.getParameter( "no" ) ); 
		} catch( NumberFormatException ex ) {
			System.out.println( "BoardServlet:ViewAction - parameter fails" );
			response.sendRedirect( "/mysite/board" );
			return;
		}
		
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.getBoardVo( boardNo );
		if( vo == null ) {
			response.sendRedirect( "/mysite/board" );
			return;	
		}
		
		request.setAttribute( "vo", vo );
		WebUtil.forward( "/views/board/view.jsp" , request, response);
	}

}
