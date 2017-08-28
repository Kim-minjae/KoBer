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
    int count;

    public int passengerInsert(PassengerDTO passengerDTO) {

    	String sql = "insert into passenger(passenger_id,passenger_name,passenger_phone,passenger_gender,asset,requirement_id) VALUES (passenger_seq.NEXTVAL,?,?,?,?,null)";
        conn = DBUtil.getConnect();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,passengerDTO.getPassenger_name());
            pst.setString(2,passengerDTO.getPassenger_phone());
            pst.setString(3,passengerDTO.getPassenger_gender()); 
            pst.setInt(4,passengerDTO.getAsset());
           // pst.setInt(5,passengerDTO.getRequirement_id());
            count = pst.executeUpdate(); 
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
  			DBUtil.dbClose(conn, st, rs);
  		}
        return count;
    }
}
