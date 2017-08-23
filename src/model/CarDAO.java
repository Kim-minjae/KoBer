package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbutil.DBUtil;
import model.CarDTO;
public class CarDAO {
	 Connection conn;
     PreparedStatement st;
     ResultSet rs;
     int count;
    
    public int carInsert(CarDTO dto){
  		String sql="insert into CAR values (?,?,?)";  		
  		conn = DBUtil.getConnect();
  		try {
  			st = conn.prepareStatement(sql);
  			st.setInt(1,dto.getId());
  			dto.setId(dto.getId()+1);
  			st.setString(2,dto.getCar_type());
  			st.setInt(3, dto.getCapacity());
  			count = st.executeUpdate(); 
  			
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBUtil.dbClose(conn, st, rs);
  		}
  		return count;		
  	}
    public int carDelete(CarDTO dto){
   		
  		String sql=
  				"delete from car"+
  				"where car_id=?";	
  		
   		conn = DBUtil.getConnect();   		
   		try {
   			st = conn.prepareStatement(sql);
   			st.setInt(1,dto.getId());      			
   			count = st.executeUpdate();
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		} finally {
   			DBUtil.dbClose(conn, st, rs);
   		}
   		return count; 		
   	}
    	public int carUpdate(CarDTO dto,int N ){
  		
 		String sql=
 				"update car set capacity=?"+
 				"where car_id=?";	
 		
  		conn = DBUtil.getConnect();
  		try {
  			st = conn.prepareStatement(sql);
  			st.setInt(1, N);
  			st.setInt(1, dto.getId());
  			count = st.executeUpdate();	
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBUtil.dbClose(conn, st, rs);
  		}
  		return count;
  	}
}
