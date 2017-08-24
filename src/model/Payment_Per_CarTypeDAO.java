package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbutil.DBUtil;

public class Payment_Per_CarTypeDAO {
	 Connection conn;
     PreparedStatement st;
     ResultSet rs;
     int count;
	public int payment_per_cartypeSetting(Payment_Per_CarTypeDTO dto){
  		String sql="insert into Payment_Per_Cartype values (?,?,?)";  		
  		conn = DBUtil.getConnect();
  		try {
  			st = conn.prepareStatement(sql);
  			st.setInt(1,dto.getSmall());
  			st.setInt(2,dto.getMedium());
  			st.setInt(3, dto.getBig());
  			count = st.executeUpdate(); 
  			
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBUtil.dbClose(conn, st, rs);
  		}
  		return count;		
  	}
}
