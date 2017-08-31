package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import dbutil.DBUtil;

public class RequirementDAO {

	static Connection conn;
	static PreparedStatement pst;
	Statement st;
	ResultSet rs;
	int count;

	   public int loadReqId() throws SQLException{//최신 요구사항
		   conn = DBUtil.getConnect();
	    	int rId = 0;
	    	String sql = "select max(requirement_id) from requirement";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				rId = rs.getInt("max(requirement_id)");
			}
	    	 return rId;
	    }
	    
	
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
	public RequirementDTO makeRdto(int requirementID){
		conn = DBUtil.getConnect();
		String sql = "select * from requirement where requirement_id=?";
		RequirementDTO rdto=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, requirementID);
			rs = pst.executeQuery();
			while(rs.next()){
				rdto = makeRequirement(rs);	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rdto;
	}
	private RequirementDTO makeRequirement(ResultSet rs) throws SQLException {
 		//여기서 get은 하나의 Column에서 각 열(1~8)에 있는 데이터를 읽어온다. 
		int demand_id=rs.getInt(1);
		String start_point=rs.getString(2);
		String destination=rs.getString(3);
		int fellow_num=rs.getInt(4);
 		return new RequirementDTO(demand_id,start_point,destination,fellow_num);
 	}
	 public List<DriverDTO> showAvailableDriverList(RequirementDTO requirementdto){
			List<DriverDTO> slist = new ArrayList<>();
			CarDAO cdao=new CarDAO();
			String sql="select * from driver where d_possible=1"; 
			conn = DBUtil.getConnect();
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()){
					DriverDTO dto = makeDriverList(rs );
					slist.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, pst, rs);
			}
			
			List<DriverDTO> slist2 = new ArrayList<>();
			for(DriverDTO dto: slist){
				StringTokenizer st=new StringTokenizer(dto.current_pos,","); //운전자 현재위치
				int tmp_x=Integer.parseInt(st.nextToken());
				int tmp_y=Integer.parseInt(st.nextToken());
				
				st=new StringTokenizer(requirementdto.getStart_point(), ","); //탑승자 현재위치
				int start_x=Integer.parseInt(st.nextToken());
				int start_y=Integer.parseInt(st.nextToken());
				double calculresult=Math.sqrt(Math.pow(tmp_x-start_x, 2)+Math.pow(tmp_y-start_y, 2));
			
				st=new StringTokenizer(requirementdto.getDestination(), ","); //탑승자 끝위치
				int dst_x=Integer.parseInt(st.nextToken());
				int dst_y=Integer.parseInt(st.nextToken());
				double calculresult2=Math.sqrt(Math.pow(dst_x-tmp_x, 2)+Math.pow(dst_y-tmp_y, 2));
				
				if((5.0-calculresult>=0) && dto.range-calculresult2>=0 ){
					if(cdao.getCapacitybyId(dto.getCar_id())-(requirementdto.getFellow_num()+1)>=0){
						slist2.add(dto);						
					}
				}
			}
			
		
			return slist2;	
		}
	 
	 
	 private DriverDTO makeDriverList(ResultSet rs) throws SQLException {
			int driver_id = rs.getInt(1);
			String driver_name=rs.getString(2);
			String driver_phone = rs.getString(3);
			String driver_gender = rs.getString(4);
			int carId=rs.getInt(10);
			String current_pos = rs.getString(7);
			return new DriverDTO(driver_id, driver_name ,driver_phone, driver_gender, current_pos,carId);
		}
}
