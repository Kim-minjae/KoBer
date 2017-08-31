package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dbutil.DBUtil;

/**
 * Created by pose2 on 2017-08-25.
 */
public class LogAction {


	static Connection conn;
	static PreparedStatement pst;
	static Statement st;
	static ResultSet rs;

   //insert
   public static int logInsert(Connection conn, LogDTO dto) throws SQLException{
 		String sql="insert into log values (log_seq.NEXTVAL,?,?,SYSDATE)";
 	 		
 		PreparedStatement st = conn.prepareStatement(sql);
 		st.setInt(1,dto.getLog_user());
 		st.setString(2,dto.getLog_action());
 
 		return st.executeUpdate(); 
 		 
 	}
 	public static List<LogDTO> getLogAll() throws SQLException {
   		List<LogDTO> tmp = new ArrayList<LogDTO>();
		conn = DBUtil.getConnect();
		String sql = "SELECT * FROM LOG";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();

		LogDTO tmplog = new LogDTO();
		while (rs.next()) {

			tmplog = new LogDTO(rs.getInt("log_id"),rs.getInt("log_user"),rs.getString("log_action"),rs.getDate("log_time"));

			tmp.add(tmplog);
		}

   		return tmp;
	}
    
}
