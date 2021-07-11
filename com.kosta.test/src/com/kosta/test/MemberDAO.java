package com.kosta.test;

public class MemberDAO {

ArrayList<MemberDTO> arr = null;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

private Connection getConnection() {
	String className = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "hr";
    String pwd = "hr";

   Connection conn = null;

	try {
	Class.forName(className);
	conn = DriverManager.getConnection(url, user, pwd);

    } catch(SQLException | ClassNotFoundException e) {
	System.out.println(e);
	}
    return conn;
	
   }


   public int insert(String mid, String mpwd, String mname, String memail) {
   Connection conn = getConnection(); //getConnection() 메서드 호출해서 DB 연결
   PreparedStatement pstmt = null;

   StringBuilder sql = new StringBuilder();

	sql.append("	insert into member(mno, mid, mpwd, mname, memail, mdate)  	 ");
	sql.append(" 	values(memberseq.nextval, ?, ?, ?, ?, sysdate)				 ");
    
	int result = 0;

	try {
    pstmt = conn.prepareStatement(sql.toString());

   pstmt.setString(1, mid);
   pstmt.setString(2, mpwd); 
   pstmt.setString(3, mname); 
   pstmt.setString(4, memail); 

  result = pstmt.executeUpdate();

  } catch(SQLException e) {
	System.out.println(e);
  } finally {
	close(pstmt, conn);
	}
  
	return result;
	}
   
   public int update(String mid, String mpwd, String memail) {
	Connection conn = getConnection();
	PreparedStatement pstmt = null;

	StringBuilder sql = new StringBuilder();

	sql.append("	update member	");
	sql.append("	set			    ");
	sql.append("		mpwd = ?	");
	sql.append("	   ,memail = ?	");
	sql.append("	where mid = ?	");

	int result = 0;

	try {
	pstmt = conn.prepareStatement(sql.toString());

	pstmt.setString(1, mpwd);
	pstmt.setString(2, memail);
	pstmt.setString(3, mid);

    result = pstmt.executeUpdate();

   } catch (SQLException e) {
	 System.out.println(e);

   } finally {
	close(pstmt, conn);
	}

   return result;
    }
   
   public int delete(String mid) {
	Connection conn = getConnection();
	PreparedStatement pstmt = null;

    StringBuilder sql = new StringBuilder();

	sql.append("	delete			");
	sql.append("	from member 	");
	sql.append("	where mid = ?	");

	int result = 0;

	try {
	pstmt = conn.prepareStatement(sql.toString());
	pstmt.setString(1, mid);

	result = pstmt.executeUpdate();

	} catch (SQLException e) {
	 System.out.println(e);

	}finally {
	  close(pstmt, conn);
	}

	return result;
	}

   public void select(String mid) {
	Connection conn = getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	StringBuilder sql = new StringBuilder();

	sql.append("	select				");
	sql.append("		  mno			");
	sql.append("		, mid			");
	sql.append("		, mpwd			");
	sql.append("		, mname			");
	sql.append("		, memail		");
	sql.append("		, mdate			");
	sql.append("	from member			");
	sql.append("	where mid = ?		");

	try {
	 pstmt = conn.prepareStatement(sql.toString());
	 pstmt.setString(1, mid);

	 rs = pstmt.executeQuery();

	while(rs.next()) {
	 System.out.println(rs.getInt("mno") + "\t" + rs.getString("mid") + "\t" + rs.getString("mpwd") + "\t"
										+ rs.getString("mname") + "\t" + rs.getString("memail") + "\t" + rs.getString("mdate"));
	 }

    } catch(SQLException e) {
	 System.out.println(e);

    } finally {
	  close(pstmt, conn);
    }

  }
	
 public ArrayList<MemberDTO> getAll() {
	Connection conn = getConnection(); //getConnection() 메서드 호출해서 DB 연결
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	StringBuilder sql = new StringBuilder();

	sql.append("	select				");
	sql.append("		  mno			");
	sql.append("		, mid			");
	sql.append("		, mpwd			");
	sql.append("		, mname			");
	sql.append("		, memail		");
	sql.append("		, mdate			");
	sql.append("	from member			");
	sql.append("	order by mno		");

	try {
	  pstmt = conn.prepareStatement(sql.toString());
	  rs = pstmt.executeQuery();

	  arr = new ArrayList<MemberDTO>();

	while(rs.next()) {
	  MemberDTO dto = new MemberDTO();
	  dto.setMno(rs.getInt("mno"));
	  dto.setMid(rs.getString("mid"));
	  dto.setMpwd(rs.getString("mpwd"));
	  dto.setMname(rs.getString("mname"));
	  dto.setMemail(rs.getString("memail"));
	  dto.setMdate(rs.getString("mdate"));

	  arr.add(dto); //ArrayList에 추가
		}

	} catch(SQLException e) {
	  System.out.println(e);

	} finally {
	  close(pstmt, conn);
	}

    return arr;
    }
	
 private void close(PreparedStatement pstmt, Connection conn) {
	if(pstmt != null) try{pstmt.close();} catch(SQLException e) {}
	if(conn != null) try{conn.close();} catch(SQLException e) {}
	}

  public boolean isCheck(String mid) {
	Connection conn = getConnection(); //getConnection() 메서드 호출해서 DB 연결
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	boolean check = false;

	StringBuilder sql = new StringBuilder();

	sql.append("	select				");
	sql.append("		  mid			");
	sql.append("	from member			");
	sql.append("	where mid = ?		");

	try {
	 pstmt = conn.prepareStatement(sql.toString());
	 pstmt.setString(1, mid);
	 rs = pstmt.executeQuery();
	 check = rs.next();

	} catch(SQLException e) {
	  System.out.println(e);

	} finally {
	  close(pstmt, conn);
	}

	 return check;
	}
}



