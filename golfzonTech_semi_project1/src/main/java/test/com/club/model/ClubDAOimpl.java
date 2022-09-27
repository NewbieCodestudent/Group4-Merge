package test.com.club.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.com.clubmember.model.ClubMemberVO;

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

//         jdbcConnectionTest();

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
         //  Է ,    ,     : DML

         // 4.
         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_INSERT);
         
         // 5.
         pstmt.setLong(1, vo.getClub_id());
         pstmt.setString(2, vo.getClub_leader());
         pstmt.setString(3, vo.getClub_name());
         pstmt.setString(4, vo.getClub_desc());
         pstmt.setString(5, vo.getGender());
         pstmt.setString(6, vo.getAge());
         pstmt.setString(7, vo.getLocation());
         pstmt.setInt(8, vo.getClose());
         pstmt.setString(9, vo.getClub_img());
         

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
      System.out.println("delete()...");
      System.out.println(vo);
      
      int flag = 0;
      
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         System.out.println("conn successed...");
         

         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_DELETE);
      
         pstmt.setLong(1, vo.getClub_id());
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
//      String location,String age, String gender, 
      System.out.println("searchList()...");
      System.out.println(searchKey);
      System.out.println(searchWord);
      List<ClubVO> vos = new ArrayList<ClubVO>();
 
      // 2.
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         // 3.
         System.out.println("conn successed...");
         // 4.
         if (searchKey.equals("club_name")) {
            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SEARCH_LIST_CLUB_NAME);
            pstmt.setString(1, "%" + searchWord + "%");
         }else if (searchKey.equals("club_leader")) {
            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SEARCH_LIST_CLUB_LEADER);
            pstmt.setString(1, "%" + searchWord + "%");
         }else if (searchKey.equals("gender")) {
            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SEARCH_LIST_GENDER);
            pstmt.setString(1, "%" + searchWord + "%");
         }else if (searchKey.equals("age")) {
            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SEARCH_LIST_AGE);
            pstmt.setInt(1, Integer.parseInt(searchWord));

         
//         if (location.equals("location")) {
//            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SEARCH_LIST_LOCATION);
//            pstmt.setString(1, "%" + "서울"+ "%");
//         }
//         else if(location.equals("location")) {
//            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SEARCH_LIST_AGE);
//            pstmt.setInt(1, Integer.parseInt(searchWord));
         }
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
      } // end finally
      return vos;
   }

   public long club_id() {
      System.out.println("club_id()...");
      ClubVO vo1 = new ClubVO();
      long club_id = 0l;
      
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         System.out.println("conn successed...");
         
         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_CLUB_ID);
         rs = pstmt.executeQuery();
            
         while (rs.next()) {
            vo1.setClub_id(rs.getLong("nextval"));
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
      club_id = vo1.getClub_id();
      
      return club_id;
   }

   @Override
   public int insertleader(ClubMemberVO vo) {
      System.out.println("insertleader()...");
      System.out.println(vo);
      // 1.
      int flag = 0;

      // 2.
      try {
         // 3.
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         System.out.println("conn successed...");

         // 4.
         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_INSERT_CLUBLEADER);
         // 5.
         pstmt.setLong(1, vo.getClub_id());
         pstmt.setString(2, vo.getMember_id());
//         pstmt.setInt(3, vo.getQualified()); 
//         pstmt.setInt(4, vo.getStatus());
//         pstmt.setDate(6, vo.getCmdate());
//         pstmt.setInt(7, vo.getCmtype());
         
         
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
   public ClubVO leaderinfo(ClubVO vo) {
      System.out.println("leaderinfo()...");
      System.out.println(vo);
      // 2.
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         // 3.
         System.out.println("conn successed...");
         
         // 4.
         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_LEADERINFO);
         pstmt.setLong(1, vo.getClub_id());
         // 5.
         rs = pstmt.executeQuery();
         // 6.
         while (rs.next()) {
            
            vo.setName(rs.getString("name"));
            vo.setM_gender(rs.getString("gender"));
            vo.setBirthday(rs.getDate("birth"));
            vo.setM_location(rs.getString("location"));         
            vo.setImg_name(rs.getString("img_name"));
            vo.setM_age(rs.getInt("m_age"));
         
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


}