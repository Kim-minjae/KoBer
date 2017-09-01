package model;

import dbutil.DBUtil;

import java.sql.*;

/**
 * Created by pose2 on 2017-08-23.
 */
public class PassengerDAO {

	static Connection conn;
	static PreparedStatement pst;
	ResultSet rs;
	int count = 0;


	public int setReqID(int passenger_id, int requirement_id) { //드라이버가 자동차 설정
		String sql = "UPDATE passenger SET requirement_id =? WHERE passenger_id=?";
		conn = DBUtil.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, requirement_id);
			pst.setInt(2, passenger_id);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, pst, rs);
		}
		return count;
	}

	public int loadPassegerId(Connection conn) throws SQLException {
		int pid = 0;
		String sql = "SELECT max(passenger_id) FROM passenger";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			pid = rs.getInt("max(passenger_id)");
		}
		return pid;
	}

	public int createPassengerAccount(PassengerDTO passengerDTO) {
		int result = 0;
		String sql = "INSERT INTO passenger(passenger_id,passenger_name,passenger_phone,passenger_gender,asset,requirement_id,protector_phone) VALUES (passenger_seq.NEXTVAL,?,?,?,?,NULL,?)";
		conn = DBUtil.getConnect();
		try {
			conn.setAutoCommit(false);

			pst = conn.prepareStatement(sql);
			pst.setString(1, passengerDTO.getPassenger_name());
			pst.setString(2, passengerDTO.getPassenger_phone());
			pst.setString(3, passengerDTO.getPassenger_gender());
			pst.setInt(4, passengerDTO.getAsset());
			pst.setString(5, passengerDTO.getProtector_phone());
			// pst.setInt(5,passengerDTO.getRequirement_id());
			result = pst.executeUpdate();

			int tmpid = loadPassegerId(conn);
			LogDTO ldto = new LogDTO(tmpid, "탑승자 등록");
			LogAction.logInsert(conn, ldto);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}
		return result;
	}

	public int Passenger_Asset(int asset, int passenger_id) { //드라이버가 운전범위 변경
		String sql = "UPDATE passenger SET asset= ? WHERE passenger_id= ?";
		conn = DBUtil.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, asset);
			pst.setInt(2, passenger_id);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, pst, rs);
		}
		return count;
	}

	public PassengerDTO getPassenger(int Passenger_id) throws SQLException {

		conn = DBUtil.getConnect();

		String sql = "SELECT * FROM Passenger WHERE Passenger_ID = ?";

		PassengerDTO tmp = new PassengerDTO();
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Passenger_id);

		rs = pst.executeQuery();
		if (rs.next()) {
			tmp.setPassenger_id(rs.getInt("passenger_id"));
			tmp.setPassenger_name(rs.getString("passenger_name"));
			tmp.setPassenger_phone(rs.getString("passenger_phone"));
			tmp.setPassenger_gender(rs.getString("passenger_gender"));
			tmp.setAsset(rs.getInt("asset"));
			tmp.setRequirement_id(rs.getInt("requirement_id"));
		}
		return tmp;
	}

	public void transferLog(int passengerID, String protector_phone, String passenger_name, int driver_id) {
		conn = DBUtil.getConnect();
		try {

			LogDTO ldto = new LogDTO(passengerID, protector_phone + "에"  + passenger_name + "가" + driver_id + "를 탔습니다 ->" + "문자로 전송");
			LogAction.logInsert(conn, ldto);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getProtector_phone(int passengerID) {
		String protector_phone = null;
		conn = DBUtil.getConnect();
		String sql = "SELECT protector_phone FROM Passenger WHERE Passenger_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, passengerID);
			rs = pst.executeQuery();
			if (rs.next()) {
				protector_phone = rs.getString("protector_phone");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return protector_phone;
	}

	public String getPassenger_name(int passengerID) {
		String passenger_name = null;
		conn = DBUtil.getConnect();
		String sql = "SELECT passenger_name FROM Passenger WHERE Passenger_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, passengerID);
			rs = pst.executeQuery();
			if (rs.next()) {
				passenger_name = rs.getString("passenger_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passenger_name;
	}

	public int getRequirementID(int passengerID) {
		int rID = 0;
		conn = DBUtil.getConnect();
		String sql = "SELECT requirement_id FROM Passenger WHERE Passenger_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, passengerID);
			rs = pst.executeQuery();
			if (rs.next()) {
				rID = rs.getInt("requirement_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rID;
	}

	//이거 리턴이 -1이면 아이디 없다는거다. 로그인 못한다는것이다!!
	public int P_login(String passenger_phone, String passenger_name) throws Exception {

		int tmp = -1;

		conn = DBUtil.getConnect();
		String sql = "SELECT * FROM PASSENGER WHERE PASSENGER_PHONE = ? AND  PASSENGER_NAME = ?";

		pst = conn.prepareStatement(sql);
		pst.setString(1, passenger_phone);
		pst.setString(2, passenger_name);
		rs = pst.executeQuery();

		if (rs.next()) {
			tmp = rs.getInt("passenger_id");
		}


		return tmp;

	}

}