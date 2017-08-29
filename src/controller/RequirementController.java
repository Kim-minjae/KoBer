package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.RequirementDAO;
import model.RequirementDTO;

public class RequirementController {
	 public void RequirementService() throws Exception {
	//public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		String start_point, destination = null;
		RequirementDAO dao = new RequirementDAO();
		RequirementDTO dto = null;
		int result = 0;
		int fellow_num = 0;

		System.out.println("탑승자의 요구사항을 입력하는 창입니다.");

		Loop1: while (true) {
			System.out.println("탑승자의 출발 위치를 입력하세요. ex> 4,5 ");
			start_point = sc.nextLine();
			String regExp = "\\d{1,2},\\d{1,2}";
			boolean rst = Pattern.matches((regExp), start_point);

			if (!rst) { // if문의 조건을 변경해야. start_point 데이터 입력 형식에 벗어나면 if문이 발동되도록
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요");
				continue Loop1;
			}
			break; // loop1 break;
		}
		Loop2: while (true) {
			System.out.println("목적지를 입력하세요. ex> 6,7 ");
			destination = sc.nextLine();
			String regExp = "\\d{1,2},\\d{1,2}";
			boolean rst = Pattern.matches((regExp), destination);
			if (!rst) { // if문의 조건을 변경해야 합니다. destination 데이터 입력 형식에 벗어나면 if문이
						// 발동되도록
				System.out.println("형식에 맞지 않습니다. 다시 입력하세요");
				continue Loop2;
			}
			break; // loop2 break;
		}

		Loop3: while (true) {
			try {
				System.out.println("동승자가 있습니까? 있다면 인원을 입력하고, 없다면 0을 입력해주세요.");
				fellow_num = Integer.parseInt(sc.nextLine());
				if (fellow_num < 0 || fellow_num > 15) {
					System.out.println("불가능한 인원이 입력됐습니다. 다시 입력하세요.");
					continue Loop3;
				}
				dto = new RequirementDTO(start_point, destination, fellow_num);
				result = dao.RequirementInsert(dto);
				String msg = "입력에 실패";
				if (result > 0) {
					System.out.println("요구사항 입력 성공");
				} else {
					System.out.println(msg);
				}
				break; // 동승자 입력작업이 성공적으로 끝났으면 break를 통해 Loop3 반복문을 빠져나옴
			} catch (NumberFormatException e) {
				System.out.println("입력 포멧이 다릅니다. 다시 입력하세요.(숫자)");
				
			} // catch end

		} 

	} // RequirementService() end
} // class end
