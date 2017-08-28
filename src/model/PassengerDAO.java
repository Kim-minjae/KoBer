package model;

import dbutil.DBUtil;

import java.sql.*;

/**
 * Created by pose2 on 2017-08-23.
 */
public class PassengerDAO {

    static Connection conn;
    static PreparedStatement pst;
    Statement st;
    ResultSet rs;
    
    public int loadPassegerId(Connection conn) throws SQLException{
    	int pid = 0;
    	String sql = "select max(passenger_id) from passenger";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			pid = rs.getInt("max(passenger_id)");
		}
    	 return pid;
    }
    
    public int createPassengerAccount(PassengerDTO passengerDTO) {
    	int result=0;
    	String sql = "insert into passenger(passenger_id,passenger_name,passenger_phone,passenger_gender,asset,requirement_id) VALUES (passenger_seq.NEXTVAL,?,?,?,?,null)";
        conn = DBUtil.getConnect();
        try{
        	conn.setAutoCommit(false);
        	
            pst = conn.prepareStatement(sql);
            pst.setString(1,passengerDTO.getPassenger_name());
            pst.setString(2,passengerDTO.getPassenger_phone());
            pst.setString(3,passengerDTO.getPassenger_gender()); 
            pst.setInt(4,passengerDTO.getAsset());
           // pst.setInt(5,passengerDTO.getRequirement_id());
            result=pst.executeUpdate();
            
            int tmpid = loadPassegerId(conn) ;
            LogDTO ldto = new LogDTO(tmpid,"탑승자 등록");
           LogAction.logInsert(conn, ldto);
            conn.commit();
        }catch (SQLException e){
        	try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            e.printStackTrace();

        }
        return result;
    }

    //이거 리턴이 -1이면 아이디 없다는거다. 로그인 못한다는것이다!!
    public int P_login(String passenger_phone, String passenger_name) throws Exception{

        int tmp = -1;

        conn = DBUtil.getConnect();
        String sql = "SELECT * from PASSENGER WHERE PASSENGER_PHONE = ? AND  PASSENGER_NAME = ?";

        pst = conn.prepareStatement(sql);
        pst.setString(1,passenger_phone);
        pst.setString(2,passenger_name);
        rs = pst.executeQuery();

        if(rs.next()){
            tmp = rs.getInt("passenger_id");
        }

        return tmp;

        
    }
}
