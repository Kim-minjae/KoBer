package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

/**
 * Created by pose2 on 2017-08-25.
 */
public class LogAction {

	Connection conn;
    PreparedStatement st;
    ResultSet rs;
    int count;
    
   //insert
   public int logInsert(LogDTO dto){
 		String sql="insert into log values (log_seq.NEXTVAL,?,?,SYSDATE)";

 		conn = DBUtil.getConnect();
 		try {
 			conn.setAutoCommit(false);
 			
 			st = conn.prepareStatement(sql);
 			st.setInt(1,dto.getLog_user());
 			st.setString(1,dto.getLog_action());
 
 			count = st.executeUpdate(); 
 		} catch (SQLException e) {
 			e.printStackTrace();
 			//conn.rollback();
 		} finally {
 			DBUtil.dbClose(conn, st, rs);
 		}
 		return count;		
 	}
	public static void log(){
	
 	
    }
    
    
}
