package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class CarDAO {
	 Connection conn;
     PreparedStatement st;
     ResultSet rs;
     int count;
    //insert
    public int carInsert(CarDTO dto){
  		String sql="insert into CAR values (car_seq.nextval,?,?)";  		
  		conn = DBUtil.getConnect();
  		try {
  			st = conn.prepareStatement(sql);
  			st.setString(1,dto.getCar_type());
  			st.setInt(2, dto.getCapacity());
  			count = st.executeUpdate(); 
  			
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBUtil.dbClose(conn, st, rs);
  		}
  		return count;		
  	}
}
