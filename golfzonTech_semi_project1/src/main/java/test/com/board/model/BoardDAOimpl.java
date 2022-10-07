package test.com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOimpl implements BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDAOimpl() {
		System.out.println("BoardDAOimpl()....");
		try {
			Class.forName(BoardDB_Oracle.DRIVER_NAME);
			System.out.println("Driver Successed...");
//			jdbcConnectionTest();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	private void jdbcConnectionTest() {
//		try {
//			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
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
	public int insert(BoardVO vo) {
		System.out.println("insert()....");
		System.out.println(vo);

		int flag = 0;
		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_INSERT);


			pstmt.setLong(1, vo.getClub_id());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getFname());
			pstmt.setInt(6, vo.getNotice());

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
	public int update(BoardVO vo) {
		System.out.println("update()....");

//		System.out.println(vo.getTitle());
//		System.out.println(vo.getContent());
//		System.out.println(vo.getFname());
//		System.out.println(vo.getNotice());
//		System.out.println(vo.getBoard_id());

		int flag = 0;
		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_UPDATE);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFname());
			pstmt.setInt(4, vo.getNotice());
			pstmt.setLong(5, vo.getBoard_id());

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
	public int delete(BoardVO vo) {
		System.out.println("delete()....");

//		System.out.println(vo.getTitle());
//		System.out.println(vo.getContent());
//		System.out.println(vo.getFname());
//		System.out.println(vo.getNotice());
//		System.out.println(vo.getBoard_id());

		int flag = 0;
		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_DELETE);
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
	public BoardVO selectOne(BoardVO vo) {
		System.out.println("selectOne()....");
		BoardVO vo1 = new BoardVO();

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SELECT_ONE);
			pstmt.setLong(1, vo.getBoard_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// BoardDB의 게시글 정보를 vo객체에 저장
				vo1.setBoard_id(rs.getLong("board_id"));
				vo1.setClub_id(rs.getLong("club_id"));
				vo1.setTitle(rs.getString("title"));
				vo1.setContent(rs.getString("content"));
				vo1.setWriter(rs.getString("writer"));
				vo1.setFname(rs.getString("fname"));
				vo1.setWdate(rs.getTimestamp("wdate"));
				vo1.setNotice(rs.getInt("notice"));
				// 정보를 담은 객체를 List<BoardVO>에 저장
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
		return vo1;
	}

	@Override
	public List<BoardVO> selectAll(long club_id) {
		System.out.println("selectAll()....club_id: " + club_id);
		
		List<BoardVO> vos = new ArrayList<BoardVO>();
		
		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SELECT_ALL);
			pstmt.setLong(1, club_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				// BoardDB의 게시글 정보를 vo객체에 저장
				vo.setBoard_id(rs.getLong("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setClub_id(club_id);
				vo.setWriter(rs.getString("writer"));
				vo.setWdate(rs.getTimestamp("wdate"));
				vo.setNotice(rs.getInt("notice"));
				// 정보를 담은 객체를 List<BoardVO>에 저장
				vos.add(vo);
			}
			System.out.println(vos);
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
	} // end selectAll()
	@Override
	public List<BoardVO> selectAll_notice(long club_id) {
		System.out.println("selectAll()....club_id: " + club_id);

		List<BoardVO> vos = new ArrayList<BoardVO>();

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SELECT_ALL_NOTICE);
			pstmt.setLong(1, club_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				BoardVO vo = new BoardVO();
				// BoardDB의 게시글 정보를 vo객체에 저장
				vo.setBoard_id(rs.getLong("board_id"));
				vo.setClub_id(club_id);
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setFname(rs.getString("fname"));
				vo.setWdate(rs.getTimestamp("wdate"));
				vo.setNotice(rs.getInt("notice"));
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
	} // end selectAll_notice()

	@Override
	public List<BoardVO> selectAll_common(long club_id, String order) {
		System.out.println("selectAll()....");

		List<BoardVO> vos = new ArrayList<BoardVO>();

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			// order의 값에 따라 오름차순, 내림차순으로 select한 구문이 출력
			if (order.equals("asc")) {
				System.out.println("asc....");
				pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SELECT_ALL_COMMONS_ASC);
			} else {
				System.out.println("desc....");
				pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SELECT_ALL_COMMONS_DESC);
			}
			pstmt.setLong(1, club_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				// BoardDB의 게시글 정보를 vo객체에 저장
				vo.setBoard_id(rs.getLong("board_id"));
				vo.setClub_id(club_id);
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setFname(rs.getString("fname"));
				vo.setWdate(rs.getTimestamp("wdate"));
				vo.setNotice(rs.getInt("notice"));
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
	} // end selectAll_common

	@Override
	public List<BoardVO> searchList(long club_id, String searchKey, String searchWord) {
		List<BoardVO> vos = new ArrayList<BoardVO>();
//		System.out.println("searchList()...");
//		System.out.print("searchKey : " + searchKey);
//		System.out.println(", searchWord : " + searchWord);

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
//			System.out.println("conn success ... ");
			if (searchKey.equals("title")) {
				pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SEARCH_LIST_TITLE);
			} else if (searchKey.equals("content")) {
				pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_SEARCH_LIST_CONTENT);
			}
			pstmt.setLong(1, club_id);
			pstmt.setString(2, "%" + searchWord + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_id(rs.getLong("board_id"));
				vo.setClub_id(club_id);
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setFname(rs.getString("fname"));
				vo.setWdate(rs.getTimestamp("wdate"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vos;
	} // end searchList()

	@Override
	public boolean isWriter(BoardVO vo) {
		System.out.println("isWriter()....");
		boolean flag = false;

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_IS_WRITER);
			pstmt.setLong(1, vo.getBoard_id());
			pstmt.setString(2, vo.getWriter());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
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
		return flag;
	}

	@Override
	public boolean isMember(BoardVO vo) {
		System.out.println("isWriter()....");
		boolean flag = false;

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_IS_MEMBER);
			pstmt.setLong(1, vo.getClub_id());
			pstmt.setString(2, vo.getWriter());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
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
		return flag;
	}

	@Override
	public boolean isLeader(BoardVO vo) {
		System.out.println("isLeader()....");
		boolean flag = false;

		try {
			conn = DriverManager.getConnection(BoardDB_Oracle.URL, BoardDB_Oracle.USER, BoardDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(BoardDB_Oracle.SQL_BOARD_IS_LEADER);
			pstmt.setLong(1, vo.getClub_id());
			pstmt.setString(2, vo.getWriter());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
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
		return flag;
	}
} // end class