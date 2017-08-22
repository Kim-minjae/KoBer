package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbutil.DBUtil;

public class requirementDAO {

	static Connection conn;
	static PreparedStatement pst;
	Statement st;
	ResultSet rs;
	int count;

	public void RequirementInsert(requirementDTO rdto){
		conn = DBUtil.getConnect();
		String sql = "insert into requirement(demand_id, start_point, destination, fellow_num) values(seq_require.NEXTVAL,?,?,?)";
		
		try{
			pst = conn.prepareStatement(sql);
			pst.setString(1, rdto.getStart_point());
			pst.setString(2, rdto.getDestination());
			pst.setInt(3, rdto.getFellow_num());
			
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, pst, null); //resultSet은 필요없으니 null이 들어감.
		} 
		
		
	}

}
