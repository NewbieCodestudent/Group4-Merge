package test.com.album.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.com.activity.model.ActivityDB_Oracle;
import test.com.board.model.BoardDB_Oracle;
import test.com.board.model.BoardVO;

public class AlbumDAOimpl implements AlbumDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public AlbumDAOimpl() {
		try {
			Class.forName(ActivityDB_Oracle.DRIVER_NAME);
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
	public int insert(AlbumVO vo) {
		System.out.println("insert()....");
		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(AlbumDB_Oracle.URL, AlbumDB_Oracle.USER, AlbumDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_INSERT);
			pstmt.setLong(1, vo.getClub_id());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getFname());
			pstmt.setString(4, vo.getWriter());
			// 입력 결과 값을 flag에 저장
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
	public int update(AlbumVO vo) {
		System.out.println("update()....");
		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(AlbumDB_Oracle.URL, AlbumDB_Oracle.USER, AlbumDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getFname());
			pstmt.setLong(3, vo.getAlbum_id());
			// 입력 결과 값을 flag에 저장
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
	public int delete(AlbumVO vo) {
		System.out.println("delete()....");
		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(AlbumDB_Oracle.URL, AlbumDB_Oracle.USER, AlbumDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_DELETE);
			pstmt.setLong(1, vo.getAlbum_id());
			// 입력 결과 값을 flag에 저장
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
	public List<AlbumVO> selectAll(AlbumVO vo, String order) {
		System.out.println("selectAll()....");

		List<AlbumVO> vos = new ArrayList<AlbumVO>();

		try {
			conn = DriverManager.getConnection(AlbumDB_Oracle.URL, AlbumDB_Oracle.USER, AlbumDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			// order의 값에 따라 오름차순, 내림차순으로 select한 구문이 출력
			System.out.println("order: " + order);
			if (order.equalsIgnoreCase("desc")) {
				pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_SELECT_ALL_DESC);
			} else if (order.equalsIgnoreCase("asc")) {
				pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_SELECT_ALL_ASC);
			}
			pstmt.setLong(1, vo.getClub_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AlbumVO vo1 = new AlbumVO();
				// AlbumVO의 앨범 정보를 vo객체에 저장
				vo1.setAlbum_id(rs.getLong("album_id"));
				vo1.setClub_id(rs.getLong("club_id"));
				vo1.setTitle(rs.getString("title"));
				vo1.setFname(rs.getString("fname"));
				vo1.setWdate(rs.getTimestamp("wdate"));
				vo1.setWriter(rs.getString("writer"));
				// 정보를 담은 객체를 List<AlbumVO>에 저장
				vos.add(vo1);
			}
			System.out.println("vos: " + vos);
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
	public List<AlbumVO> searchList(long club_id, String searchKey, String searchWord) {
		System.out.println("selectAll()....");

		List<AlbumVO> vos = new ArrayList<AlbumVO>();

		try {
			conn = DriverManager.getConnection(AlbumDB_Oracle.URL, AlbumDB_Oracle.USER, AlbumDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			// order의 값에 따라 오름차순, 내림차순으로 select한 구문이 출력
			if (searchKey.equalsIgnoreCase("title")) {
				pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_SEARCHlIST_TITLE);
			} else if (searchKey.equalsIgnoreCase("writer")) {
				pstmt = conn.prepareStatement(AlbumDB_Oracle.SQL_ALBUM_SEARCHlIST_WRITER);
			}
			pstmt.setLong(1, club_id);
			pstmt.setString(2, "%" + searchWord + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AlbumVO vo1 = new AlbumVO();
				// AlbumVO의 앨범 정보를 vo객체에 저장
				vo1.setAlbum_id(rs.getLong("album_id"));
				vo1.setClub_id(rs.getLong("club_id"));
				vo1.setTitle(rs.getString("title"));
				vo1.setFname(rs.getString("fname"));
				vo1.setWdate(rs.getTimestamp("wdate"));
				vo1.setWriter(rs.getString("writer"));
				// 정보를 담은 객체를 List<AlbumVO>에 저장
				vos.add(vo1);
			}
			System.out.println("vos: " + vos);
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

}
