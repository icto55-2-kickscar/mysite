package com.sds.icto.mysite.servlet.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.vo.BoardVo;
import com.sds.icto.web.Action;
import com.sds.icto.web.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException,
			IOException {
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.fetchList();
		
		request.setAttribute( "list", list );
		WebUtil.forward( "/views/board/list.jsp", request, response );
	}
}
