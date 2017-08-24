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

        conn = DBUtil.getConnect();
        String sql = "insert into passenger(passenger_id,passenger_name,passenger_phone,passenger_gender,asset,requirement_id) VALUES (seq_require.NEXTVAL,?,?,?,?,?,?)";

        try{

            pst = conn.prepareStatement(sql);
            pst.setString(1,passengerDTO.getPassenger_name());
            pst.setString(2,passengerDTO.getPassenger_phone());
            pst.setString(3,passengerDTO.getPassenger_gender()+ ""); // 이거 char타입으로 넣는방법
            pst.setInt(4,passengerDTO.getAsset());
            pst.setInt(5,passengerDTO.getRequirement_id());

        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
    }
}
