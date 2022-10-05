package test.com.comment.model;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.com.board.model.BoardVO;

public class CommentDAOimpl implements CommentDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public CommentDAOimpl() {
		System.out.println("CommentDAOimpl()....");
		try {
			Class.forName(CommentDB_Oracle.DRIVER_NAME);
			System.out.println("Driver Successed...");
//			jdbcConnectionTest();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	private void jdbcConnectionTest() {
//		try {
//			conn = DriverManager.getConnection(CommentDB_Oracle.URL, CommentDB_Oracle.USER, CommentDB_Oracle.PASSWORD);
//			System.out.println(conn.isClosed() ? "Conn Failled...." : "Conn Success....");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	@Override
	public int insert(CommentVO vo) {
		System.out.println("insert()....");
		System.out.println(vo);

		int flag = 0;
		try {
			conn = DriverManager.getConnection(CommentDB_Oracle.URL, CommentDB_Oracle.USER, CommentDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(CommentDB_Oracle.SQL_COMMENT_INSERT);

//			"INSERT INTO CMT (COMMENT_ID,BOARD_ID,COMMENTER,CONTENT,CDATE)VALUES(SEQ_CMT.nextval,?,'?','?', TO_DATE(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";

			pstmt.setLong(1, vo.getBoard_id());
			pstmt.setString(2, vo.getCommenter());
			pstmt.setString(3, vo.getComment());

			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public int delete_one(CommentVO vo) { // 댓글 삭제
		int flag = 0;
		try {
			conn = DriverManager.getConnection(CommentDB_Oracle.URL, CommentDB_Oracle.USER, CommentDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(CommentDB_Oracle.SQL_COMMENT_DELETE_ONE);

			pstmt.setLong(1, vo.getComment_id());
			flag = pstmt.executeUpdate();
			System.out.println("flag: " + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public int delete_all(BoardVO vo) { // 댓글 삭제
		int flag = 0;
		try {
			conn = DriverManager.getConnection(CommentDB_Oracle.URL, CommentDB_Oracle.USER, CommentDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(CommentDB_Oracle.SQL_COMMENT_DELETE_ALL);
			
			pstmt.setLong(1, vo.getBoard_id());
			flag = pstmt.executeUpdate();
			System.out.println("flag: " + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public CommentVO selectOne(CommentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentVO> selectAll(long board_id) {
		System.out.println("selectAll()....");

		List<CommentVO> vos = new ArrayList<CommentVO>();

		try {
			conn = DriverManager.getConnection(CommentDB_Oracle.URL, CommentDB_Oracle.USER, CommentDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			// order의 값에 따라 오름차순, 내림차순으로 select한 구문이 출력
			pstmt = conn.prepareStatement(CommentDB_Oracle.SQL_COMMENT_SELECT_ALL);
			pstmt.setLong(1, board_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentVO vo = new CommentVO();
				// CommentDB의 게시글 정보를 vo객체에 저장
				vo.setComment_id(rs.getLong("comment_id"));
				vo.setBoard_id(rs.getLong("board_id"));
				vo.setCommenter(rs.getString("commenter"));
				vo.setComment(rs.getString("content"));
				vo.setCdate(rs.getTimestamp("cdate"));
				// 정보를 담은 객체를 List<BoardVO>에 저장
				vos.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vos;
	}

	@Override
	public boolean isCommenter(CommentVO vo) {
		System.out.println("isWriter()....");
		boolean flag = false;
		
		try {
			conn = DriverManager.getConnection(CommentDB_Oracle.URL, CommentDB_Oracle.USER, CommentDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(CommentDB_Oracle.SQL_COMMENT_IS_COMMENTER);
			pstmt.setLong(1, vo.getComment_id());
			pstmt.setString(2, vo.getCommenter());
			rs = pstmt.executeQuery();
			if (rs.next()) { flag = true; }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}