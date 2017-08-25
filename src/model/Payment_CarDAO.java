package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbutil.DBUtil;

public class Payment_CarDAO {
	 Connection conn;
     PreparedStatement st;
     ResultSet rs;
     int count;
	public int payment_per_cartypeSetting(Payment_CarDTO dto){
  		String sql="insert into payment_car values (?,?)";  		
  		conn = DBUtil.getConnect();
  		try {
  			st = conn.prepareStatement(sql);
  			count = st.executeUpdate(); 
  			
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBUtil.dbClose(conn, st, rs);
  		}
  		return count;		
  	}
}
