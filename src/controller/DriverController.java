package controller;

import java.util.Scanner;
import java.util.regex.Pattern;

import model.CarDAO;
import model.DriverDAO;
import model.DriverDTO;

public class DriverController {

	String driver_name, driver_phone, driver_gender, licence_num = null;
	String current_pos = null;
	DriverDAO dao = new DriverDAO();
	DriverDTO dto = null;
	int driver_possible;
	int result = 0;
	int range = 0;
	Scanner sc = new Scanner(System.in);

	public void DriverRegisterService() throws Exception {
		// public static void main(String[] args) {

		System.out.println("─────────운전자 정보 등록창입니다.─────────");
		System.out.println("─────────────────────────────────");

		Loop1: while (true) {
			System.out.println("운전자의 이름을 입력하세요: ");
			driver_name = sc.nextLine();
			if (driver_name.equals("")) { // 이름은 형식이 딱히 중요하지 않아서 공백 입력에만 문제가
											// 생기도록
				System.out.println("잘못 입력하셨습니다.");
				continue Loop1;
			}
			break; // Loop1 탈출
		}

		Loop2: while (true) { // 휴대전화 입력 반복문
			System.out.println("휴대전화를 입력하세요.(특수문자 제외 입력): ");
			driver_phone = sc.nextLine();
			String regExp = "(010|016|019|011)\\d{3,4}\\d{4}";
			boolean rss = Pattern.matches((regExp), driver_phone);

			if (!rss) {
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요.");
				continue Loop2;
			} else {
				break;
			}
		}

		Loop3: while (true) { // 성별 입력창
			System.out.println("성별을 입력하세요. 남자는 m, 여자는 f:  ");
			driver_gender = sc.nextLine().toUpperCase();
			if (!(driver_gender.equals("M") || driver_gender.equals("F"))) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue Loop3;
			} else {
				break;
			}
		}

		Loop4: while (true) { // 면허번호 입력창.
			System.out.println("면허 번호를 입력하세요 ex> 10-111111-21 : ");
			licence_num = sc.nextLine();
			String regExp = "\\d{2}-\\d{6}-\\d{2}";
			boolean rss = Pattern.matches((regExp), licence_num);
			if (!rss) {
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요.");
				continue Loop4;
			} else {
				break;
			}

		}

		Loop5: while (true) { // 운전자의 현재위치 입력
			System.out.println("운전자의 현재 위치를 입력하세요. ex> 1,1 : ");
			current_pos = sc.nextLine();
			String regExp = "\\d{1,2},\\d{1,2}";
			boolean rst = Pattern.matches((regExp), current_pos);
			if (!rst) { // if문의 조건을 변경해야 합니다. destination 데이터 입력 형식에 벗어나면 if문이
						// 발동되도록
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요");
				continue Loop5;
			}
			break; // loop5 break;
		}

		Loop7: while (true) { // 운전 가능한 범위
			try {
				System.out.println("운전 가능한 범위를 입력하세요(숫자로 입력). ex>30 : ");
				range = Integer.parseInt(sc.nextLine());
				if (!(range > 0 && range < 20)) {
					System.out.println("허용 범위를 벗어났습니다. 다시 입력하세요.");
					continue Loop7;
				}
				System.out.println("드라이버 운전가능 여부, default는 OFF입니다.(자동입력)");
				driver_possible = 0;
				System.out.println("추후 운전 가능 여부를 변경해주세요");
				System.out.println("=====================================");
				dto = new DriverDTO(driver_name, driver_phone, driver_gender, licence_num, range, current_pos,
						driver_possible);
				result = dao.driverInsert(dto);
				String msg = "입력 실패";
				if (result > 0) {
					System.out.println("운전자 정보 입력 성공");
					break Loop7;
				} else {
					System.out.println(msg);
				}
			} catch (NumberFormatException e) {
			}
		}

	}// register

	public void carInfoUpdate() throws Exception {// 처음 차 등록 및 변경
		cloop: while (true) {
			CarDAO cdao = new CarDAO();
			CarController carservice = new CarController();

			System.out.println("차를 등록하시겠습니까? (Y/N)");
			String yOrN = sc.nextLine();

			if (yOrN.equalsIgnoreCase("y")) {
				carservice.CarRegisterService();
				int cId = cdao.loadCarId();
				dao.setCarID(dto, cId);
				break cloop;
			} else if (yOrN.equalsIgnoreCase("n")) {
				System.out.println("등록 취소");
				break cloop;
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}

	// driver possible
	public void driver_possible(int dId) throws Exception {
		ploop: while (true) {
			System.out.println("운전 가능하십니까 ? (Y/N)");
			String yOrN = sc.nextLine();

			if (yOrN.equalsIgnoreCase("y")) {
				dao.possibleToggle(1, dId);
				System.out.println(dId);
				System.out.println("운행 가능(ON)");
				break ploop;
			} else if (yOrN.equalsIgnoreCase("n")) {
				dao.possibleToggle(0, dId);
				System.out.println("운행 불가(OFF)");
				break ploop;
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}

	// driver possible
	public void range_change(int dId) throws Exception {
		ploop: while (true) {
			System.out.println("범위를 수정하시겠습니까 ? (Y/N)");
			String yOrN = sc.nextLine();

			if (yOrN.equalsIgnoreCase("y")) {
				System.out.print("변경할 범위를 입력하세요> ");
				int inputrange = sc.nextInt();
				dao.changeRange(inputrange, dId);
				System.out.println(inputrange + "로 범위 변경 완료");
				break ploop;
			} else if (yOrN.equalsIgnoreCase("n")) {
				System.out.println("변경 취소");
				break ploop;
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}

	public void menu(DriverDTO driverDTO, int dId) {
		while (true) {
			System.out.println("====== 운전자 메뉴창입니다. 메뉴를 선택하세요 ======");
			System.out.println("1.운전 가능 여부  2.가용범위 설정 3.자동차 변경 > 4.종료 ");
			int choice = sc.nextInt();
			DriverController dc = new DriverController();

			switch (choice) {
			case 1:
				try {
					dc.driver_possible(dId);
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 2:
				try {
					dc.range_change(dId);
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			case 3:
				try {
					dc.carInfoUpdate();
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 0:
				System.out.println("시스템 종료");
				System.exit(0);
				break;
			default:
			}
		}

	}

}
