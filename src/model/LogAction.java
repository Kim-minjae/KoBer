package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbutil.DBUtil;

/**
 * Created by pose2 on 2017-08-25.
 */
public class LogAction {
 
   //insert
   public static int logInsert(Connection conn, LogDTO dto) throws SQLException{
 		String sql="insert into log values (log_seq.NEXTVAL,?,?,SYSDATE)";
 	 		
 		PreparedStatement st = conn.prepareStatement(sql);
 		st.setInt(1,dto.getLog_user());
 		st.setString(2,dto.getLog_action());
 
 		return st.executeUpdate(); 
 		 
 	}
    
}
