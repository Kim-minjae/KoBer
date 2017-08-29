package controller;

import java.util.Scanner;
import java.util.regex.Pattern;

import model.DriverDAO;
import model.DriverDTO;

public class DriverController {

	 public void DriverRegisterService() throws Exception{
	//public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String driver_name, driver_phone, driver_gender, licence_num = null;
		String current_pos = null;
		DriverDAO dao = new DriverDAO();
		DriverDTO dto = null;
		int driver_possible;
		int result = 0;
		int range = 0;

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
				range = sc.nextInt();
				
				System.out.println("드라이버 운전가능 여부, default는 OFF입니다.(자동입력)");
				driver_possible = 0;
				System.out.println("추후 운전 가능 여부를 변경해주세요");
				System.out.println("=====================================");
				if (range < 0 || range > 400) {
					System.out.println("허용 범위를 벗어났습니다. 다시 입력하세요.");
					continue Loop7;
				}
				dto = new DriverDTO(driver_name, driver_phone, driver_gender, licence_num, range, current_pos, driver_possible);
				result = dao.driverInsert(dto);
				String msg = "입력 실패";
				if (result > 0) {
					System.out.println("운전자 정보 입력 성공");
				} else {
					System.out.println(msg);
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("입력 포멧이 다릅니다. 다시 입력하세요.(숫자)");

			} // catch

		}

	}

}
