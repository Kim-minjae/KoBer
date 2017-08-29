package controller;

import java.util.Scanner;

import model.CarDAO;
import model.CarDTO;

public class CarController {

	public void CarRegisterService() throws Exception {
//	 public static void main(String[] args) throws Exception{

		Scanner sc = new Scanner(System.in);
		String car_type = null;
		int capacity = 0;
		CarDAO dao = new CarDAO();
		CarDTO dto = null;
		int result = 0;

		System.out.println("차 정보 입력 창입니다.");

		Loop1: while (true) { // 차종 입력 반복문
			System.out.println("차종을 입력하세요.ex>소형 : ");
			car_type = sc.nextLine();
			if (!(car_type.equals("소형") || car_type.equals("중형") || car_type.equals("대형"))) {
				System.out.println("차종을 다시 입력하세요(소형, 중형, 대형)");
				continue Loop1; // 아무것도 입력하지 않고 엔터를 입력할 경우 Loop1으로 돌아감.
			}
			break; // Loop1 end.
		}

		Loop2: while (true) {
			try {
				System.out.println("수용 인원을 입력하세요(숫자): ");
				capacity = Integer.parseInt(sc.nextLine());
				if (capacity < 0 || capacity > 15) {
					System.out.println("불가능한 인원이 입력됐습니다. 다시 입력하세요.");
					continue Loop2;
				}

				dto = new CarDTO(0, car_type, capacity);
				result = dao.carInsert(dto);

				String msg = "등록 실패";
				if (result > 0) {
					System.out.println("차 정보 등록 완료");
				} else {
					System.out.println(msg);
				}
				break; // 포멧에 맞게 입력시 반복문 탈출

			} catch (NumberFormatException e) { // 그렇지 않다면
				System.out.println("입력 포멧이 틀렸습니다. 다시 입력하세요.");
			}
		}
	}

}
