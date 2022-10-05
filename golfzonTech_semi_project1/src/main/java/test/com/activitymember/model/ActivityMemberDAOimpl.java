package test.com.activitymember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.com.activity.model.ActivityVO;

public class ActivityMemberDAOimpl implements ActivityMemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public ActivityMemberDAOimpl() {
		System.out.println("BoardDAOimpl()....");
		try {
			Class.forName(ActivityMemberDB_Oracle.DRIVER_NAME);
			System.out.println("Driver Successed...");
			jdbcConnectionTest();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void jdbcConnectionTest() {
		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println(conn.isClosed() ? "Conn Failled...." : "Conn Success....");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int insert(ActivityMemberVO vo) {
		System.out.println("insert()....");
		System.out.println(vo);

		int flag = 0;
		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_INSERT);

//			"INSERT INTO ACT_MEMBER (AM_ID,MEMBER_ID,ACT_ID,QUALIFIED, APPROVED, AMTYPE) "
//			+ "VALUES(SEQ_ACT_MEMBER.nextval,?,?,?,?,?)";
			
//			vo1.setAct_id(act_id);
//			vo1.setMember_id(act_leader);
//			vo1.setQualified(1);
//			vo1.setApproved(1);
//			vo1.setAmtype(1);

			pstmt.setString(1, vo.getMember_id());
			pstmt.setLong(2, vo.getAct_id());
			pstmt.setInt(3, vo.getQualified());
			pstmt.setInt(4, vo.getApproved());
			pstmt.setInt(5, vo.getAmtype());

			flag = pstmt.executeUpdate();
			System.out.println("flag = "+flag);
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
	public int update(ActivityMemberVO vo) {
		System.out.println("update()....");
//		System.out.println(vo);

		int flag = 0;
		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_UPDATE);

//			"INSERT INTO ACT_MEMBER (AM_ID,MEMBER_ID,ACT_ID,QUALIFIED, APPROVED, AMTYPE) "
//			+ "VALUES(SEQ_ACT_MEMBER.nextval,?,?,?,?,?)";
			
//			vo1.setAct_id(act_id);
//			vo1.setMember_id(act_leader);
//			vo1.setQualified(1);
//			vo1.setApproved(1);
//			vo1.setAmtype(1);

			pstmt.setInt(1, vo.getApproved());
			pstmt.setLong(2, vo.getAm_id());

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
	public int deleteOne(ActivityMemberVO vo) {
		System.out.println("deleteOne()....");
//		System.out.println(vo);

		int flag = 0;
		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_DELETE_ONE);
			pstmt.setLong(1, vo.getAm_id());

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
	public int deleteAll(ActivityVO vo) {
			System.out.println("deleteAll()....");
//			System.out.println(vo);

			int flag = 0;
			try {
				conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
				System.out.println("conn Successed...");
				pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_DELETE_ALL);
				pstmt.setLong(1, vo.getAct_id());

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
	public ActivityMemberVO selectOne(ActivityMemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityMemberVO> selectALL_joined(ActivityMemberVO vo) {
		System.out.println("selectAll()....");

		List<ActivityMemberVO> vos = new ArrayList<ActivityMemberVO>();

		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_JOIN);
			pstmt.setLong(1, vo.getAct_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ActivityMemberVO vo1 = new ActivityMemberVO();
				// BoardDB의 게시글 정보를 vo객체에 저장
				vo1.setAct_id(rs.getLong("act_id"));
				vo1.setAm_id(rs.getInt("am_id"));
				vo1.setMember_id(rs.getString("member_id"));
				vo1.setApproved(rs.getInt("approved"));
				vo1.setQualified(rs.getInt("qualified"));
				vo1.setAmtype(rs.getInt("amtype"));
				// 정보를 담은 객체를 List<BoardVO>에 저장
				vos.add(vo1);
				
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
	public List<ActivityMemberVO> selectALL_not_joined(ActivityMemberVO vo) {
		System.out.println("selectAll()....");

		List<ActivityMemberVO> vos = new ArrayList<ActivityMemberVO>();

		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_NOT_JOIN);
			pstmt.setLong(1, vo.getAct_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ActivityMemberVO vo1 = new ActivityMemberVO();
				// BoardDB의 게시글 정보를 vo객체에 저장
				vo1.setAct_id(rs.getLong("act_id"));
				vo1.setAm_id(rs.getInt("am_id"));
				vo1.setMember_id(rs.getString("member_id"));
				vo1.setApproved(rs.getInt("approved"));
				vo1.setQualified(rs.getInt("qualified"));
				vo1.setAmtype(rs.getInt("amtype"));
				// 정보를 담은 객체를 List<BoardVO>에 저장
				vos.add(vo1);
				
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
	public boolean isActLeader(ActivityMemberVO vo) {
		System.out.println("isActLeader()....");
		boolean flag = false;

		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_IS_ACT_LEADER);
			pstmt.setLong(1, vo.getAct_id());
			pstmt.setString(2, vo.getMember_id());
			rs = pstmt.executeQuery();
			while (rs.next()) { flag = true; }
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
	public ActivityMemberVO getAppliedMember(ActivityMemberVO vo) {
		System.out.println("getAppliedMember()....: ");
		ActivityMemberVO vo1 = new ActivityMemberVO();

		try {
			conn = DriverManager.getConnection(ActivityMemberDB_Oracle.URL, ActivityMemberDB_Oracle.USER, ActivityMemberDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityMemberDB_Oracle.SQL_ACT_MEMBER_GET_APPLIED_MEMBER);
			pstmt.setLong(1, vo.getAct_id());
			pstmt.setString(2, vo.getMember_id());
			rs = pstmt.executeQuery();
			while (rs.next()) { 
//				System.out.println("rs.getLong(\"am_id\"): "+rs.getLong("am_id"));
				vo1.setAm_id(rs.getLong("am_id"));
				vo1.setMember_id(rs.getString("member_id"));
			}
			
//			System.out.println("GetActMember: "+vo1);
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
}
