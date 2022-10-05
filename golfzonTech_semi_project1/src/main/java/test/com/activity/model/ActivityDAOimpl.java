package test.com.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.com.club.model.ClubVO;

public class ActivityDAOimpl implements ActivityDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public ActivityDAOimpl() {
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
	public Map<Long, Integer> insert(ActivityVO vo) {
		System.out.println("insert()....");
//		System.out.println("vo: "+vo);
		Map<Long, Integer> result = new HashMap<Long, Integer>();
		long act_id = 0L;
		int flag = 0;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_INSERT);

			pstmt.setInt(1, vo.getCc_id());
			pstmt.setLong(2, vo.getClub_id());
			pstmt.setString(3, vo.getAct_leader());
			pstmt.setString(4, vo.getAct_name());
			pstmt.setString(5, vo.getAct_content());
			pstmt.setString(6, vo.getGender());
			pstmt.setInt(7, vo.getAge());
			pstmt.setString(8, vo.getLocation());
			pstmt.setInt(9, vo.getCost());
			pstmt.setTimestamp(10, vo.getRdate());
			pstmt.setTimestamp(11, vo.getAdate());
			pstmt.setString(12, vo.getAct_fname());
			pstmt.setInt(13, vo.getStatus());
			// 입력 결과 값을 flag에 저장
			flag = pstmt.executeUpdate();

			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_FIND_ACT_ID);
			pstmt.setString(1, vo.getAct_leader());
			pstmt.setString(2, vo.getAct_name());
			rs = pstmt.executeQuery();
//			System.out.println("size: " + rs.getFetchSize());
			while (rs.next()) {
				// 방금 생성된 액티비티 ID를 act_id에 저장
				act_id = rs.getLong("act_id");
			}

			// map 타입의 변수 result에 key를 불러온 액티비티 ID를, value를 입력 결과(flag)를 저장한다.
			result.put(act_id, flag);
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
		return result;
	}

	@Override
	public int update(ActivityVO vo) {
		System.out.println("update()....");
		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_UPDATE);
			// UPDATE ACTIVITY SET CC_ID = ?, GENDER = ?, AGE = ?, LOCATION = ?,
			// ACT_CONTENT = ?, COST = ?, RDATE = ?, ADATE = ?, FNAME = ?, ACT_NAME=? WHERE ACT_ID = ?
			pstmt.setInt(1, vo.getCc_id());
			pstmt.setString(2, vo.getGender());
			pstmt.setInt(3, vo.getAge());
			pstmt.setString(4, vo.getLocation());
			pstmt.setString(5, vo.getAct_content());
			pstmt.setInt(6, vo.getCost());
			pstmt.setTimestamp(7, vo.getRdate());
			pstmt.setTimestamp(8, vo.getAdate());
			pstmt.setString(9, vo.getAct_fname());
			pstmt.setString(10, vo.getAct_name());
			pstmt.setLong(11, vo.getAct_id());
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
	public int delete(ActivityVO vo) {
//		System.out.println("delete()....");
//		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_DELETE);
			pstmt.setLong(1, vo.getAct_id());
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
	public int deleteAll(ClubVO vo) {
		System.out.println("deleteAll()....");
//		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_DELETE_ALL);
			pstmt.setLong(1, vo.getClub_id());
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
	public int changeStatusBySize(ActivityVO vo) {
		System.out.println("changeStatusBySize()....");
//		System.out.println("vo: " + vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_CHANGE_STATUS_SIZE);
			pstmt.setInt(1, vo.getStatus());
			pstmt.setLong(2, vo.getAct_id());
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
	public void changeStatusByDate() {
		System.out.println("changeStatusByDate()....");
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_CHANGE_STATUS_DATE);
			pstmt.executeUpdate();
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
	}

	@Override
	public ActivityVO selectOne(ActivityVO vo) {
		System.out.println("selectOne()....");
		ActivityVO vo1 = new ActivityVO();
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ONE);
			pstmt.setLong(1, vo.getAct_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				"select location, rdate, adate, gender, age from activity where act_id = ?";
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo1.setClub_id(rs.getLong("club_id"));
				vo1.setClub_name(rs.getString("club_name"));
				vo1.setCc_id(rs.getInt("cc_id"));
				vo1.setCc_name(rs.getString("cc_name"));
				vo1.setAct_name(rs.getString("act_name"));
				vo1.setAct_content(rs.getString("act_content"));
				vo1.setLocation(rs.getString("location"));
				vo1.setGender(rs.getString("gender"));
				vo1.setAct_fname(rs.getString("fname"));
				vo1.setAge(rs.getInt("age"));
				vo1.setCost(rs.getInt("cost"));
				vo1.setRdate(rs.getTimestamp("rdate"));
				vo1.setAdate(rs.getTimestamp("adate"));
				vo1.setStatus(rs.getInt("status"));
			}
			System.out.println("vo1: " + vo1);
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
	public List<ActivityVO> selectAll(String order, long club_id) {
		System.out.println("selectAll()....order: " + order);

		List<ActivityVO> vos = new ArrayList<ActivityVO>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			if (order.equals("id")) {
				// 최신순으로 정렬
				pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ALL_ID);
			} else if (order.equals("adate")) {
				// 마감일순으로 정렬
				pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ALL_ADATE);
			}
			pstmt.setLong(1, club_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				ActivityVO vo = new ActivityVO();
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo.setAct_id(rs.getLong("act_id"));
				vo.setClub_id(rs.getLong("club_id"));
				vo.setStatus(rs.getInt("status"));
				vo.setAct_fname(rs.getString("fname"));
				vo.setLeader_fname(rs.getString("img_name"));
				vo.setAct_leader(rs.getString("act_leader"));
				vo.setAct_name(rs.getString("act_name"));
				vo.setRdate(rs.getTimestamp("rdate"));
				vo.setAdate(rs.getTimestamp("adate"));
				// 정보를 담은 객체를 List<ActivityVO>에 저장
				vos.add(vo);
			}
//			System.out.println("vos: "+vos);
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
	public List<ActivityVO> selectAllByID(String member_id) {
		System.out.println("selectAllByID()....member_id: " +member_id);

		List<ActivityVO> vos = new ArrayList<ActivityVO>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ALL_BY_ID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				ActivityVO vo = new ActivityVO();
				
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo.setClub_name(rs.getString("club_name"));
				vo.setAct_id(rs.getLong("act_id"));
				vo.setAct_name(rs.getString("act_name"));
				vo.setClub_id(rs.getLong("club_id"));
				vo.setAdate(rs.getTimestamp("adate"));
				vo.setRdate(rs.getTimestamp("rdate"));
				vo.setAct_fname(rs.getString("fname"));
				vo.setStatus(rs.getInt("status"));
				// 정보를 담은 객체를 List<ActivityVO>에 저장
				vos.add(vo);
			}
//			System.out.println("vos: "+vos);
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
	public List<ActivityVO> selectAllByClub(Long club_id) {
		System.out.println("selectAllByClub()....club_id: " +club_id);

		List<ActivityVO> vos = new ArrayList<ActivityVO>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ALL_BY_CLUB);
			pstmt.setLong(1, club_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				ActivityVO vo = new ActivityVO();
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo.setAct_id(rs.getLong("act_id"));
				vo.setAct_name(rs.getString("act_name"));
				vo.setClub_id(rs.getLong("club_id"));
				vo.setAct_leader(rs.getString("act_leader"));
				vo.setAdate(rs.getTimestamp("adate"));
				vo.setRdate(rs.getTimestamp("rdate"));
				vo.setAct_fname(rs.getString("fname"));
				vo.setStatus(rs.getInt("status"));
				// 정보를 담은 객체를 List<ActivityVO>에 저장
				vos.add(vo);
			}
//			System.out.println("vos: "+vos);
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
	public List<ActivityVO> selectAllByLoc(String location) {
		System.out.println("selectAllByLoc()....location: " +location);

		List<ActivityVO> vos = new ArrayList<ActivityVO>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ALL_BY_LOC);
			pstmt.setString(1, location);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				// act_id, CLUB_ID, act_name, act_leader, fname, adate, rdate, img_name

				ActivityVO vo = new ActivityVO();
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo.setAct_id(rs.getLong("act_id"));
				vo.setClub_id(rs.getLong("club_id"));
				vo.setAct_name(rs.getString("act_name"));
				vo.setAct_leader(rs.getString("act_leader"));
				vo.setAct_fname(rs.getString("fname"));
				vo.setAdate(rs.getTimestamp("adate"));
				vo.setRdate(rs.getTimestamp("rdate"));
				vo.setLeader_fname(rs.getString("img_name"));
				vo.setAct_fname(rs.getString("fname"));
				// 정보를 담은 객체를 List<ActivityVO>에 저장
				vos.add(vo);
			}
//			System.out.println("vos: "+vos);
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
	public List<ActivityVO> selectAllByDate() {
		System.out.println("selectAllByDate()....");

		List<ActivityVO> vos = new ArrayList<ActivityVO>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_SELECT_ALL_BY_DATE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ActivityVO vo = new ActivityVO();
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo.setAct_id(rs.getLong("act_id"));
				vo.setClub_id(rs.getLong("club_id"));
				vo.setAct_name(rs.getString("act_name"));
				vo.setAct_leader(rs.getString("act_leader"));
				vo.setAct_fname(rs.getString("fname"));
				vo.setAdate(rs.getTimestamp("adate"));
				vo.setRdate(rs.getTimestamp("rdate"));
				vo.setLeader_fname(rs.getString("img_name"));
				vo.setAct_fname(rs.getString("fname"));
				// 정보를 담은 객체를 List<ActivityVO>에 저장
				vos.add(vo);
			}
//			System.out.println("vos: "+vos);
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
	public List<ActivityVO> searchList(long club_id, String qstmt, String searchWord) {
		List<ActivityVO> vos = new ArrayList<ActivityVO>();
		System.out.println("searchList()...");
		System.out.print("club_id: " + club_id);
		System.out.print(", qstmt : " + qstmt);
		System.out.println(", searchWord : " + searchWord);
		
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
//			System.out.println("conn success ... ");
			pstmt = conn.prepareStatement(qstmt); // 지정된 조건으로 값을 조회한 후, 최신 액비티비 순으로 정렬
			pstmt.setLong(1, club_id);
			pstmt.setString(2, "%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ActivityVO vo = new ActivityVO();
				// ActivityVO의 게시글 정보를 vo객체에 저장
				vo.setAct_id(rs.getLong("act_id"));
				vo.setClub_id(rs.getLong("club_id"));
				vo.setStatus(rs.getInt("status"));
				vo.setAct_fname(rs.getString("fname"));
				vo.setLeader_fname(rs.getString("img_name"));
				vo.setAct_leader(rs.getString("act_leader"));
				vo.setAct_name(rs.getString("act_name"));
				vo.setRdate(rs.getTimestamp("rdate"));
				vo.setAdate(rs.getTimestamp("adate"));
				vo.setLeader_name(rs.getString("name"));
				// 정보를 담은 객체를 List<ActivityVO>에 저장
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
	}

	@Override
	public Map<Long, String> findClubById(ActivityVO vo) {
		// TODO Auto-generated method stub
		System.out.println("findClubById()....id: " + vo.getAct_leader());
		Map<Long, String> map = new HashMap<Long, String>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_FIND_CLUB_BY_ID);
			pstmt.setString(1, vo.getAct_leader());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong("club_id"), rs.getString("club_name"));
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
		return map;
	}

	@Override
	public Map<Long, String> findCC_Id() {
		System.out.println("findCC_Id()....");
		Map<Long, String> map = new HashMap<Long, String>();
//
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_FIND_CC_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong("cc_id"), rs.getString("cc_name"));
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
		return map;
	}

	@Override
	public ActLeaderVO findLeaderById(ActivityVO vo) {
		System.out.println("vo: " + vo);
		System.out.println("findLeaderById()....");
		ActLeaderVO vo1 = new ActLeaderVO();
		String fname = null;
		String name = null;
		String leader_id = null;
		String gender = null;
		int age = 0;
		String birth = null;
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_FIND_LEADER_BY_ID);
			pstmt.setLong(1, vo.getAct_id());
			rs = pstmt.executeQuery();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			while (rs.next()) {
				leader_id = rs.getString("member_id");
				fname = rs.getString("img_name");
				name = rs.getString("name");
				gender = rs.getString("gender");
				birth = format.format(rs.getDate("birth"));
			}
//			System.out.println("birth: " + birth);
			// 생년월일을 만 나이(예: 만 26세)로 환산 후 int형으로 vo에 저장
			LocalDate now = LocalDate.now();
			LocalDate parsedBirthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
			age = now.minusYears(parsedBirthDate.getYear()).getYear();
			if (parsedBirthDate.plusYears(age).isAfter(now)) {
				age = age - 1;
			}

			vo1.setAct_id(vo.getAct_id());
			vo1.setLeader_id(leader_id);
			vo1.setFname(fname);
			vo1.setName(name);
			vo1.setAge(age);
			vo1.setGender(gender);

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
	public boolean isLeader(ActivityVO vo) {
		System.out.println("isLeader()....");
		boolean flag = false;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_IS_WRITER);
			pstmt.setLong(1, vo.getAct_id());
			pstmt.setString(2, vo.getAct_leader());
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
	public boolean isQualified(Long act_id, String member_id, String qstmt) {
		System.out.println("isQualified()....");
		boolean flag = false;

		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(qstmt);
			pstmt.setLong(1, act_id);
			pstmt.setString(2, member_id);
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
	public String createQuery_search(String location, String gender, String searchKey, int age) {
		
		StringBuilder sb = 
				new StringBuilder("select a.act_id,a.club_id, a.status, a.fname, a.act_name, m.name, m.img_name, a.act_leader, a.rdate, a.adate "
						+ "from activity a left outer join member m on a.act_leader = m.member_id where status = 0"); 
		
		if (!location.equals("무관")) { sb.append(" and a.location = \'").append(location).append("\'"); } 
		if(age!=0) {sb.append(" and a.age = ").append(age); } 
		if (!gender.equals("무관")) { sb.append(" and a.gender = \'").append(gender).append("\'"); }
		if (searchKey.equals("act_name")) { sb.append(" and club_id = ? and upper(a.act_name) like upper(?) order by a.act_id desc"); }
		else { sb.append(" and club_id = ? and upper(m.name) like upper(?) order by a.act_id desc"); }
		
		return sb.toString();
	}

	@Override
	public String createQuery_qual(String location, String gender, int age) {
		StringBuilder sb = new StringBuilder("SELECT member.* FROM activity, member WHERE act_id=?"); 
		if (!gender.equals("무관")) { sb.append(" and activity.gender = member.gender"); }
		if (!location.equals("무관")) { sb.append(" and activity.location = member.location"); } 
		if(age!=0) {sb.append(" and activity.age = (case when m_age >= 60 then 60 else round(member.m_age/10)*10 end)"); } 
		sb.append(" and member_id=?");
		System.out.println("sb.length(): "+sb.length());
		return sb.toString();
	}

	@Override
	public String getClubName(ClubVO vo) {
		System.out.println("getClubName()....");
		String club_name = null;
		try {
			conn = DriverManager.getConnection(ActivityDB_Oracle.URL, ActivityDB_Oracle.USER,
					ActivityDB_Oracle.PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(ActivityDB_Oracle.SQL_ACTIVITY_GET_CLUB_NAME);
			pstmt.setLong(1, vo.getClub_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				club_name = rs.getString("club_name");
			}
			System.out.println("club_name: " + club_name);
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
		return club_name;
	}

}
