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

	//car_id로 카 요소 얻어오는.
	public CarDTO getCar(int c_id) {
		CarDTO dto = null;
		conn = DBUtil.getConnect();
		String sql = "select *from car where car_id=?";

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, c_id);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("car_id");
				int capacity = rs.getInt("capacity");
				String car_type = rs.getString("car_type");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}

		return dto;

	}

	
	
	// insert
	public int carInsert(CarDTO dto) {
		String sql = "insert into CAR values (car_seq.nextval,?,?)";
		conn = DBUtil.getConnect();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, dto.getCar_type());
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
