package test.com.clubmember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import test.com.club.model.ClubVO;

public class ClubMemberDAOimpl implements ClubMemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ClubMemberDAOimpl() {
		System.out.println("ClubMemberDAOimpl()");
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			Class.forName(ClubMemberDB_oracle.DRIVER_NAME);
			System.out.println("Driver successed...");

//         jdbcConnectionTest();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void jdbcConnectionTest() {
		try {
			conn = DriverManager.getConnection(ClubMemberDB_oracle.URL, ClubMemberDB_oracle.USER,
					ClubMemberDB_oracle.PASSWORD);
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
	public int insert(ClubMemberVO vo) {
		System.out.println("insert()...");
		System.out.println(vo);
		// 1.
		int flag = 0;

		// 2.
		try {
			// 3.
			conn = DriverManager.getConnection(ClubMemberDB_oracle.URL, ClubMemberDB_oracle.USER,
					ClubMemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			// 4.
			pstmt = conn.prepareStatement(ClubMemberDB_oracle.SQL_INSERT);
			// 5.
			pstmt.setLong(1, vo.getClub_id());
			pstmt.setString(2, vo.getMember_id());
//         pstmt.setInt(3, vo.getResult()); 
//         pstmt.setInt(4, vo.getStatus());
//         pstmt.setDate(5, vo.getCmdate());
//         pstmt.setInt(6, vo.getCmtype());

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
	public int update(ClubMemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClubMemberVO selectOne(ClubMemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClubMemberVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClubMemberVO> searchList(String searchKey, String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete_one(ClubMemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete_all(ClubVO vo) {
		System.out.println("delete_all()...");
		System.out.println(vo);
		// 1.
		int flag = 0;

		// 2.
		try {
			// 3.
			conn = DriverManager.getConnection(ClubMemberDB_oracle.URL, ClubMemberDB_oracle.USER,
					ClubMemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			// 4.
			pstmt = conn.prepareStatement(ClubMemberDB_oracle.SQL_DELETE_ALL);
			// 5.
			pstmt.setLong(1, vo.getClub_id());

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
}