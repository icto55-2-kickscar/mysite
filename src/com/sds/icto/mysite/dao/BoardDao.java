package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sds.icto.mysite.vo.BoardVo;

public class BoardDao {

	public void insert(BoardVo vo) throws ClassNotFoundException, SQLException {

		Connection conn = getConnection();

		String sql = "insert into board values(board_no_seq.nextval,?,?,?,?,0,SYSDATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getMemberNo());
		pstmt.setString(4, vo.getMemberName());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void update(BoardVo vo) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();

		String sql = "update board set title=?, content=? where no = ? and member_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getNo());
		pstmt.setLong(4, vo.getMemberNo());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void delete(BoardVo vo) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();

		String sql = "delete from board where no = ? and member_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, vo.getNo());
		pstmt.setLong(2, vo.getMemberNo());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public BoardVo getBoardVo(BoardVo vo) throws ClassNotFoundException,
			SQLException {
		BoardVo boardVo = null;

		Connection conn = getConnection();

		String sql = "select * from board where no = ? and member_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, vo.getNo());
		pstmt.setLong(2, vo.getMemberNo());

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			Long memberNo = rs.getLong(4);
			String memberName = rs.getString(5);
			Integer viewCount = rs.getInt(6);
			String regDate = rs.getString(7);

			boardVo = new BoardVo();
			boardVo.setNo(no);
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setMemberNo(memberNo);
			boardVo.setMemberName(memberName);
			boardVo.setViewCount(viewCount);
			boardVo.setRegDate(regDate);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return boardVo;
	}

	public BoardVo getBoardVo(Long boardNo) throws ClassNotFoundException,
			SQLException {
		BoardVo vo = null;

		Connection conn = getConnection();

		String sql = "select * from board where no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, boardNo);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			Long memberNo = rs.getLong(4);
			String memberName = rs.getString(5);
			Integer viewCount = rs.getInt(6);
			String regDate = rs.getString(7);

			vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setMemberName(memberName);
			vo.setViewCount(viewCount);
			vo.setRegDate(regDate);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return vo;
	}

	public List<BoardVo> fetchList() throws ClassNotFoundException,
			SQLException {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		String sql = "select * from board";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			Long memberNo = rs.getLong(4);
			String memberName = rs.getString(5);
			Integer viewCount = rs.getInt(6);
			String regDate = rs.getString(7);

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setMemberName(memberName);
			vo.setViewCount(viewCount);
			vo.setRegDate(regDate);

			list.add(vo);
		}

		rs.close();
		stmt.close();
		conn.close();

		return list;
	}

	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");
		return conn;
	}
}
