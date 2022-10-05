package test.com.club.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.com.activity.model.ActivityDB_Oracle;
import test.com.clubmember.model.ClubMemberDB_oracle;
import test.com.clubmember.model.ClubMemberVO;
import test.com.member.model.MemberDB_oracle;

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
         pstmt.setInt(6, vo.getAge());
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
         pstmt.setInt(4, vo.getAge());
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
            vo.setAge(rs.getInt("age"));
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
   public List<ClubVO> selectAll(String order) {
      System.out.println("selectAll()...");
      
      List<ClubVO> vos = new ArrayList<ClubVO>();

      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         
         System.out.println("conn successed...");
         if (order.equals("desc")) {         
            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SELECT_ALL_new);
         } else if (order.equals("asc")) {
            pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SELECT_ALL_old);
         }
         
         rs = pstmt.executeQuery();
      
         while (rs.next()) {
            ClubVO vo = new ClubVO();
            
            vo.setClub_id(rs.getLong("club_id"));
            vo.setClub_leader(rs.getString("club_leader"));
            vo.setClub_name(rs.getString("club_name"));
            vo.setCdate(rs.getDate("cdate"));
            vo.setClub_img(rs.getString("club_img"));
            vo.setImg_name(rs.getString("img_name"));
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
   public List<ClubVO> searchList(String query, String searchWord) {
//      String location,String age, String gender, 
      System.out.println("searchList()...");
//      System.out.println("club_id:"+club_id);
      System.out.println("query:"+query);
      System.out.println("searchWord:"+searchWord);
      List<ClubVO> vos = new ArrayList<ClubVO>();
      // 2.
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         // 3.
         pstmt = conn.prepareStatement(query);
         System.out.println("conn successed...");
//         pstmt.setLong(1, club_id);         
         pstmt.setString(1, "%" + searchWord + "%");         
         rs = pstmt.executeQuery();
         // 6.
         while (rs.next()) {
            ClubVO vo = new ClubVO();
            
            vo.setClub_id(rs.getLong("club_id"));
            vo.setClub_leader(rs.getString("club_leader"));
            vo.setClub_name(rs.getString("club_name"));
            vo.setCdate(rs.getDate("cdate"));
            vo.setClub_img(rs.getString("club_img"));
            vo.setImg_name(rs.getString("img_name"));
            vo.setName(rs.getString("name"));
            
//            vo.setGender(rs.getString("gender"));
//            vo.setAge(rs.getInt("age"));
//            vo.setLocation(rs.getString("location"));
//            vo.setClose(rs.getInt("close"));
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
            vo.setMember_id(rs.getString("member_id"));
         
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
   public List<ClubVO> selectAllMyClub(ClubVO vo) {
      System.out.println("selectAllMyClub()...");
      // 1.
      List<ClubVO> myclublist = new ArrayList<ClubVO>();

      // 2.
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         // 3.
         System.out.println("conn successed...");
         
         // 4.
         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_MY_CLUB_LIST);
         pstmt.setString(1, vo.getMember_id());
         // 5.
         rs = pstmt.executeQuery();
         // 6.
         while (rs.next()) {
            ClubVO vo1 = new ClubVO();
            vo1.setClub_id(rs.getLong("club_id"));
            vo1.setClub_img(rs.getString("club_img"));
            vo1.setClub_name(rs.getString("club_name"));
            vo1.setCdate(rs.getDate("cdate"));
            
            myclublist.add(vo1);
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
      return myclublist;
   }



   @Override
   public String createQuery_searchList(String location, String gender, int age, String searchKey) {
      StringBuilder sb = 
            new StringBuilder("select c.club_id, c.club_leader, c.club_name, c.cdate, c.club_img, m.img_name, m.name "
                  + "from club c left outer join member m on c.club_leader = m.member_id where c.close=0 "); 
      
      if(!gender.equals("무관")) { sb.append(" and c.gender = \'").append(gender).append("\'"); }
      if(age!=0) {sb.append(" and c.age = ").append(age); } 
      if(!location.equals("무관")) { sb.append(" and c.location = \'").append(location).append("\'"); } 
      if(searchKey.equals("club_name")) { sb.append(" and c.club_name like ? order by club_id desc"); }
      else { sb.append("and m.name like ? order by club_id desc"); }
      System.out.println("sb.length():"+sb.length());
      return sb.toString();
   }

   @Override
   public List<ClubVO> selectAllByID() {
      System.out.println("selectAll()...");
      // 1.
      List<ClubVO> vos = new ArrayList<ClubVO>();

      // 2.
      try {
         conn = DriverManager.getConnection(ClubDB_oracle.URL, ClubDB_oracle.USER, ClubDB_oracle.PASSWORD);
         // 3.
         System.out.println("conn successed...");
         
         // 4.
         pstmt = conn.prepareStatement(ClubDB_oracle.SQL_SELECT_ALL_BY_CDATE);
         // 5.
         rs = pstmt.executeQuery();
         // 6.
         
//         club_id, club_name, club_leder, club_img, cdate, img_name
         while (rs.next()) {
            ClubVO vo = new ClubVO();
            
            vo.setClub_id(rs.getLong("club_id"));
            vo.setClub_name(rs.getString("club_name"));
            vo.setClub_leader(rs.getString("club_leader"));
            vo.setClub_img(rs.getString("club_img"));
            vo.setCdate(rs.getDate("cdate"));
            vo.setImg_name(rs.getString("img_name"));
         
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

}