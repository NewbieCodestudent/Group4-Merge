package test.com.club.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubDAOimpl implements ClubDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ClubDAOimpl() {
		System.out.println("ClubDAOimpl()");
		Connection conn = null;
		PreparedStatement pstmt = null;


		ResultSet rs = null;

		try {
			Class.forName(ClubDB_oracle.DRIVER_NAME);
			System.out.println("Driver successed...");

//			jdbcConnectionTest();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void jdbcConnectionTest() {
		try {
			conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
			System.out.println("conn successed...");
			String sql = "SELECT VERSION() AS VERSION";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("version"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
		} // end finally
	}
	@Override
	public int insert(ClubVO vo) {
		System.out.println("insert()...");
		System.out.println(vo);
		// 1.
		int flag = 0;

		// 2.
		try {
			// 3.
			conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			// 4.
			pstmt = conn.prepareStatement(ClubDB_oracle.SQL_INSERT);
			// 5.
			pstmt.setString(1, vo.getClub_leader());
			pstmt.setString(2, vo.getClub_name());
			pstmt.setString(3, vo.getClub_desc());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getAge());
			pstmt.setString(6, vo.getLocation());
			pstmt.setInt(7, vo.getClose());
			pstmt.setString(8, vo.getClub_img());
			

			// 6.
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
		} // end finally
		return flag;
	}

	@Override
	public int update(ClubVO vo) {
		System.out.println("update()...");
		System.out.println(vo);
		
		int flag = 0;
		
		try {
			conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
			System.out.println("conn successed...");
			

			pstmt = conn.prepareStatement(ClubDB_oracle.SQL_UPDATE);
			pstmt.setString(1, vo.getClub_name());
			pstmt.setString(2, vo.getClub_desc());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getAge());
			pstmt.setString(5, vo.getLocation());
			pstmt.setInt(6, vo.getClose());
			pstmt.setString(7, vo.getClub_img());
			
			pstmt.setLong(8, vo.getClub_id());
			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		} // end finally
		
		
		return flag;
	}

	@Override
	public int delete(ClubVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClubVO selectOne(ClubVO vo) {
		System.out.println("selectOne()...");

		// 2.
		try {
			conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
			// 3.
			System.out.println("conn successed...");
			
			// 4.
			pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SELECT_ONE);
			pstmt.setLong(1, vo.getClub_id());
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			while (rs.next()) {
				vo.setClub_id(rs.getLong("club_id"));
				vo.setClub_leader(rs.getString("club_leader"));
				vo.setClub_name(rs.getString("club_name"));
				vo.setClub_desc(rs.getString("club_desc"));
				vo.setGender(rs.getString("gender"));
				vo.setAge(rs.getString("age"));
				vo.setLocation(rs.getString("location"));
				vo.setClose(rs.getInt("close"));
				vo.setCdate(rs.getDate("cdate"));
				vo.setClub_img(rs.getString("club_img"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
		} // end finally
		return vo;
	}

	@Override
	public List<String> selectGender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectAge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClubVO> selectAll() {
		System.out.println("selectAll()...");
		// 1.
		List<ClubVO> vos = new ArrayList<ClubVO>();

		// 2.
		try {
			conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
			// 3.
			System.out.println("conn successed...");
			
			// 4.
			pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SELECT_ALL_new);
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			while (rs.next()) {
				ClubVO vo = new ClubVO();
				
				vo.setClub_id(rs.getLong("club_id"));
				vo.setClub_leader(rs.getString("club_leader"));
				vo.setClub_name(rs.getString("club_name"));
				vo.setCdate(rs.getDate("cdate"));
				vo.setClub_img(rs.getString("club_img"));
			
				vos.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally
		return vos;
	}


	@Override
	public List<ClubVO> searchList(String searchKey, String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
