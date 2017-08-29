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
    
    public int loadDriverId(Connection conn) throws SQLException{
    	int dId = 0;
    	String sql = "select max(driver_id) from Driver";
		st = conn.prepareStatement(sql);
		rs = st.executeQuery();
		while (rs.next()) {
			dId = rs.getInt("max(driver_id)");
		}
    	 return dId;
    }
    
    
   public int driverInsert(DriverDTO dto){
 		String sql="insert into Driver(driver_id, driver_name, driver_phone, driver_gender,licence_num, range, current_pos, d_possible) values (driver_seq.NEXTVAL,?,?,?,?,?,?,?)";  		
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
 			st.setInt(7, dto.getDrive_possible());
 			//st.setInt(8, dto.getPassenger_id());
 			//st.setInt(9, dto.getCar_id());
 			count = st.executeUpdate();


            int tmpid = loadDriverId(conn) ;
            LogDTO ldto = new LogDTO(tmpid,"운전자 등록");
           LogAction.logInsert(conn, ldto);
            conn.commit();
// 			if(LogAction.log(LogDTO dto)); 이게 성공했을때 commit 하면됨. conn.commit();
 		} catch (SQLException e) {
 			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
 			e.printStackTrace();
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

   public int possibleToggle(int d_possible,int driver_id){ //드라이버가 운전가능여부
	   String sql="update driver set d_possible=? where driver_id=?";		
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1,d_possible);
			st.setInt(2, driver_id);
			count = st.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return count;		
   }
   
   public int changeRange(int range,int driver_id){ //드라이버가 운전범위 변경
	   String sql="update driver set range=? where driver_id=?";		
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1,range);
			st.setInt(2, driver_id);
			count = st.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return count;		
   }
   
   public DriverDTO getDriver(int driver_id)throws SQLException{

   	conn = DBUtil.getConnect();

   	String sql = "SELECT * from DRIVER WHERE DRIVER_ID = ?";

   	DriverDTO tmp = null;

   	st = conn.prepareStatement(sql);
   	st.setInt(1,driver_id);

   	rs = st.executeQuery();

   	if(rs.next()){
   		tmp.setCar_id(driver_id);
   		tmp.setDrver_phone(rs.getString("driver_phone"));
   		tmp.setDriver_name(rs.getString("driver_name"));
   		tmp.setDriver_gender(rs.getString("driver_gender"));
   		tmp.setLicence_num(rs.getString("licence_num"));
   		tmp.setRange(rs.getInt("range"));
   		tmp.setCurrent_pos(rs.getString("currnet_pos"));
   		tmp.setDrive_possible(rs.getInt("d_possible"));
   		tmp.setPassenger_id(rs.getInt("passenger_id"));
   		tmp.setCar_id(rs.getInt("car_id"));
	}


   		return tmp;
   }


   // -1이면 운전자아이디가 아니라는것 ,
	public int D_login(String driver_phone, String driver_name) throws Exception{

		int tmp = -1;

		conn = DBUtil.getConnect();
		String sql = "SELECT * from DRIVER WHERE  DRIVER_PHONE= ? AND  DRIVER_NAME = ?";

		st = conn.prepareStatement(sql);
		st.setString(1,driver_phone);
		st.setString(2,driver_name);
		rs = st.executeQuery();

		if(rs.next()){
			tmp = rs.getInt("driver_id");
		}

		return tmp;

	}
}
