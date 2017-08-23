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

    public int passengerInsert(requirementDTO rdto) {

        conn = DBUtil.getConnect();
        String sql = "insert into passenger(passenger_id,passenger_name,passenger_phone,passenger_gender,asset,demend_id) VALUES (seq_require.NEXTVAL,?,?,?,?,?)";



        try{

            pst = conn.prepareStatement(sql);


        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
    }
}
