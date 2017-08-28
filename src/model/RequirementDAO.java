package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbutil.DBUtil;

public class RequirementDAO {

	static Connection conn;
	static PreparedStatement pst;
	Statement st;
	ResultSet rs;
	int count;

	public RequirementDTO getRequirement(int r_id) {
		RequirementDTO dto = null;
		conn = DBUtil.getConnect();
		String sql = "select *from requirement where requirement_id=?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, r_id);
			rs = pst.executeQuery();

			while (rs.next()) {
				dto.setDemand_id(r_id);
				dto.setStart_point(rs.getString("start_point"));
				dto.setDestination(rs.getString("destination"));
				dto.setFellow_num(rs.getInt("fellow_num"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, pst, rs);
		}

		return dto;

	}

	public int RequirementInsert(RequirementDTO rdto) {
		conn = DBUtil.getConnect();
		String sql = "insert into REQUIREMENT values(require_seq.NEXTVAL,?,?,?)";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, rdto.getStart_point());
			pst.setString(2, rdto.getDestination());
			pst.setInt(3, rdto.getFellow_num());

			count = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, pst, rs);
		}
		return count;
	}

}
