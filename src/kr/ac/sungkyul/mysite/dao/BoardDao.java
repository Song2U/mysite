package kr.ac.sungkyul.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.mysite.vo.BoardVo;

public class BoardDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into board values(seq_board.nextval, ?, ?, sysdate, 0, nvl((select max(group_no) from board), 0) + 1, 1, 1, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public BoardVo get(Long contentNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = new BoardVo();

		try {
			conn = getConnection();

			String sql = "select no, title, content, user_no from board where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, contentNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long userNo = rs.getLong(4);

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userNo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select board.no, board.title, board.content, to_char(board.reg_date, 'yyyy-mm-dd pm hh:mi:ss'), board.VIEW_COUNT, USERS.NAME, Board.user_no from BOARD, users where BOARD.USER_NO=USERS.NO order by BOARD.NO desc";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				Long viewCount = rs.getLong(5);
				String userName = rs.getString(6);
				Long userNo = rs.getLong(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setViewCount(viewCount);
				vo.setUserName(userName);
				vo.setUserNo(userNo);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from BOARD where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String title = vo.getTitle();
			String content = vo.getContent();
			Long no = vo.getNo();

			String sql = "update BOARD set title=?, CONTENT=? where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setLong(3, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewCount(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set view_count=view_count+1 where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int totalBoard() {
		int total = 0;

		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.createStatement();
			String sql = "select count(*) from board";

			rs = pstmt.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return total;
	}
	public List<BoardVo> getList(Integer page, Integer row){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		try {
			conn = getConnection();
			
			String sql = "select no, title, content, reg_date, view_count, name, user_no, depth "
					+ "from (select a.*, ROWNUM as rn "
					+ "from (select b.no, b.title, b.content, to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss') as reg_date, "
					+ "b.view_count, b.group_no, b.order_no, b.depth, u.NAME, b.user_no from board b, users u "
					+ "where b.USER_NO = u.NO order by group_no desc, order_no asc) a "
					+ "where a.title like '%%') where rn >= ? and rn <= ? order by group_no desc, order_no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				Long viewCount = rs.getLong(5);
				String name = rs.getString(6);
				Long userNo = rs.getLong(7);
				Long depth = rs.getLong(8);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}