package com.sds.icto.mysite.servlet.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.vo.BoardVo;
import com.sds.icto.mysite.vo.MemberVo;
import com.sds.icto.web.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException,
			IOException {
		
		// 세션 객체를 가져옴
		HttpSession session = request.getSession( false );
		if( session == null ) {
			response.sendRedirect( "/mysite/board" );
			return;
		}

		// 세션 객체에 저장된 MemberVo 객체를 꺼냄
		MemberVo memberVo = ( MemberVo ) session.getAttribute( "authMember" );
		if( memberVo == null ) {
			response.sendRedirect( "/mysite/board" );
			return;
		}
		
		// 폼 입력 값
		
		// 게시물 번호
		Long no = 0L;
		try {
			no = Long.parseLong( request.getParameter( "no" ) ); 
		} catch( NumberFormatException ex ) {
			System.out.println( "UpdateAction - parameter 'no' fails" );
			response.sendRedirect( "/mysite/board?a=view&no=" + no );
			return;
		}
		
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );

		// VO 값 담기
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMemberNo( memberVo.getNo() );

		// update
		BoardDao dao = new BoardDao();
		dao.update( vo );
		
		response.sendRedirect( "/mysite/board?a=view&no=" + no );		
		
	}

}
