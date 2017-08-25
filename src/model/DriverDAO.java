package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DriverDTO;

import dbutil.DBUtil;
public class DriverDAO {
	Connection conn;
    PreparedStatement st;
    ResultSet rs;
    int count;
   //insert
   public int driverInsert(DriverDTO dto){
 		String sql="insert into Driver values (car_seq.NEXTVAL,?,?,?,?,?,?,?)";  		
 		conn = DBUtil.getConnect();
 		try {
 			conn.setAutoCommit(false);

 			st = conn.prepareStatement(sql);
 			st.setString(1,dto.getDriver_name());
 			st.setString(2, dto.getDrver_phone());
 			st.setString(3, dto.getDriver_gender());
 			st.setString(4, dto.getLicence_num());
 			st.setInt(5, dto.getRange());
 			st.setString(6, dto.getCurrent_pos());
 			st.setString(7, dto.getDrive_possible());
 
 			count = st.executeUpdate(); 
 		} catch (SQLException e) {
 			e.printStackTrace();
 			//conn.rollback();
 		} finally {
 			DBUtil.dbClose(conn, st, rs);
 		}
 		return count;		
 	}
   public int setCarID(DriverDTO dto,int carID){ //드라이버가 자동차 설정
	   String sql="update driver set car_id=? where driver_id=?";		
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1,carID);
			st.setInt(2, dto.getDriver_id());
			count = st.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return count;		
   }
   public int car_CapacityUpdate(DriverDTO dto,int capacity){	    //차 ID에 따른 capacity 설정
	   String sql="update car set capacity=? where car_id=?";			
 		conn = DBUtil.getConnect();
 		try {
 			st = conn.prepareStatement(sql);
 			
 			st.setInt(1, capacity);
    		st.setInt(2, dto.getCar_id());
 			count = st.executeUpdate();
 			
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DBUtil.dbClose(conn, st, rs);
 		}
 		return count;
 	}
}
