package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import model.*;
import view.DriverView;

/**
 * Created by pose2 on 2017-08-24.
 */
public class PassengerController {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PassengerDTO dto = null;
	PassengerDAO dao = new PassengerDAO();
	RequirementDAO rdao = new RequirementDAO();

	public void passengerInsertService() throws IOException {
		// public static void main(String[] args) throws IOException {

		int passenger_id = 0, asset = 0, requirement_id = 0;
		String passenger_name = null, passenger_phone = null, passenger_gender = null, protector_phone;
		int result2 = 0;
		System.out.println("─────────탑승자 정보 등록창입니다.─────────");

		Loop1: while (true) {
			System.out.println("탑승자의 이름을 입력하세요: ");
			passenger_name = br.readLine();
			if (passenger_name.equals("")) { // 이름은 형식이 딱히 중요하지 않아서 공백 입력에만 문제가
												// 생기도록
				System.out.println("잘못 입력하셨습니다.");
				continue Loop1;
			}
			break; // Loop1 탈출
		}

		Loop2: while (true) { // 휴대전화 입력 반복문
			System.out.println("휴대전화를 입력하세요.(특수문자 제외 입력): ");
			passenger_phone = br.readLine();
			String regExp = "(010|016|019|011)\\d{3,4}\\d{4}";
			boolean result = Pattern.matches((regExp), passenger_phone);

			if (!result) {
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요.");
				continue Loop2;
			} else {
				break;
			}
		}

		Loop3: while (true) { // 성별 입력창
			System.out.println("성별을 입력하세요. 남자는 m, 여자는 f:  ");
			passenger_gender = br.readLine().toUpperCase();
			if (!(passenger_gender.equals("M") || passenger_gender.equals("F"))) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue Loop3;
			} else {
				break;
			}
		}

		Loop4: while (true) {
			System.out.println("자산을 입력하세요.(원 단위) : ");

			try {
				asset = Integer.parseInt(br.readLine());
				if (asset <= 0) {
					System.out.println("입력할 수 없는 금액입니다. 다시 입력해주세요.");
					continue Loop4;
				} else {
					break Loop4;
				}
			} catch (Exception e) {
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요. ex) 5000");
			}
		}
		
		System.out.println("보호자의 휴대전화를 등록하시겠습니까? (Y/N)");
		System.out.println("승차시 보호자의 번호로 메세지가 전달됩니다.");
		String yOrN = br.readLine();
		Loop5: while (true) { // 휴대전화 입력 반복문
			if(yOrN.equalsIgnoreCase("y")){
				System.out.println("보호자의 휴대전화를 입력하세요.(특수문자 제외 입력): ");
				protector_phone = br.readLine();
				
				String regExp = "(010|016|019|011)\\d{3,4}\\d{4}";
				boolean result = Pattern.matches((regExp), protector_phone);
				if (!result) {
					System.out.println("형식에 맞지 않습니다. 다시 입력하세요.");
					continue Loop5;
				}else{
					break;
				}
			}else if(yOrN.equalsIgnoreCase("n")){
				protector_phone=null;
				break;
			}else{
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
		dto = new PassengerDTO(0, passenger_name, passenger_phone, passenger_gender, asset, -1, protector_phone);
		result2 = dao.createPassengerAccount(dto);

		if (result2 > 0) {
			System.out.println("탑승자 등록 성공");
		} else {
			System.out.println("탑승자 등록 실패");
		}
	}

	public void asset_Change(int pId) throws Exception {
		ploop: while (true) {
			System.out.println("자산을 수정하시겠습니까 ? (Y/N)");
			String yOrN = br.readLine();

			if (yOrN.equalsIgnoreCase("y")) {
				System.out.print("변경할 자산을 입력하세요> ");
				int inputasset = Integer.parseInt(br.readLine());
				dao.Passenger_Asset(inputasset, pId);
				System.out.println(inputasset + "로 자산 변경 완료");
				break ploop;
			} else if (yOrN.equalsIgnoreCase("n")) {
				System.out.println("변경 취소");
				break ploop;
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}

	public void passenger_menu(PassengerDTO passengerDTO, int pID) throws NumberFormatException, IOException, SQLException {
		List<DriverDTO> driverlist = null;
		while(true){
			System.out.println("====== 탑승자 메뉴창입니다. 메뉴를 선택하세요 ======");
			System.out.println("1.요구 사항 등록 2. 자산 변경 3. 탑승 하기 4.종료 ");
			RequirementController rc=new RequirementController();
			int choice = Integer.parseInt(br.readLine());
			PassengerController psc = new PassengerController();
			switch(choice){
			case 1:
				try {
					rc.RequirementService();
					int rId = rdao.loadReqId();
					dao.setReqID(pID, rId);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case 2:
				try {
					psc.asset_Change(pID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				int reqID=dao.getRequirementID(pID);
				RequirementDTO rdto= new RequirementDTO();
				rdto=rdao.makeRdto(reqID);
				driverlist=rdao.showAvailableDriverList(rdto);
				DriverView.print(driverlist);	
				System.out.println();
				System.out.print("선택할 드라이버 ID를 입력해주세요: ");
			    int driver_id=Integer.parseInt(br.readLine());

			    DriverDAO driverDAO = new DriverDAO();
			    driverDAO.changeCurrentPos(rdto.getDestination(),driver_id);

			    System.out.println();
				String protector_phone=dao.getProtector_phone(pID);
				String passenger_name=dao.getPassenger_name(pID);		
				String protector_phone2="";
				for(int i=0;i<11;i++)
				{
					protector_phone2=protector_phone2 + protector_phone.charAt(i);
					if(i==2 || i==6){
						protector_phone2=protector_phone2+"-";
					}
				}
				if(protector_phone!=null)
				{
					System.out.println("회원님은 등록된 보호자 번호가 있습니다.");
					System.out.println("["+passenger_name+"회원님이 "+driver_id+"번 택시를 탑승하셨습니다.]" );
					System.out.println("["+protector_phone2+"번으로 이 내용을 전송합니다.]");
					System.out.println();
					dao.transferLog(pID,protector_phone, passenger_name, driver_id);
				}
				
				
				/*
				 * ID잘 썼는지,
				 */
				/*
				 * 출력해주는 DAO
				 */
				
				/*
				 * 탑승자에게는 누구랑 매치되었다.
				 * 
				 */
				break;
			case 4:
				System.out.println("시스템 종료");
				System.exit(0);
			}
		}
		
	}
}
