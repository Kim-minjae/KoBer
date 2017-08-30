package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

import model.DriverDTO;
import model.PassengerDAO;
import model.PassengerDTO;
import model.RequirementDAO;
import model.RequirementDTO;
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
		String passenger_name = null, passenger_phone = null, passenger_gender = null;
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
		dto = new PassengerDTO(0, passenger_name, passenger_phone, passenger_gender, asset, -1);
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

	public void passenger_menu(PassengerDTO passengerDTO, int pID) throws NumberFormatException, IOException{
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
					// TODO Auto-generated catch block
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
				RequirementDTO rdto=new RequirementDTO();
				rdto=rdao.makeRdto(reqID);
				driverlist=rdao.showAvailableDriverList(rdto);
				DriverView.print(driverlist);	
				System.out.println();
				break;
			case 4:
				System.out.println("시스템 종료");
				System.exit(0);
			}
		}
		
	}
}
