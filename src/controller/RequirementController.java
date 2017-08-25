package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.RequirementDAO;
import model.RequirementDTO;

public class RequirementController {
	public void RequirementService() throws Exception {
	//public static void main(String[] args) throws Exception{
 
	Scanner sc = new Scanner(System.in);
		String start_point, destination = null;
		RequirementDAO dao = new RequirementDAO();
		RequirementDTO dto = null;
		int result = 0;
		int fellow_num = 0;

		System.out.println("탑승자의 요구사항을 입력하는 창입니다.");

		
		Loop1: while (true) {
			System.out.println("탑승자의 출발 위치를 입력하세요. ex>(4,5) ");
			start_point = sc.nextLine();
			if (start_point.equals("")) {
				System.out.println("null값이 입력됐습니다. 다시 입력하세요");
				continue Loop1; //아무것도 입력하지 않고 엔터를 입력할 경우 Loop1으로 돌아감.
			}

			Loop2: while (true) {
				System.out.println("목적지를 입력하세요. ex> (6,7) ");
				destination = sc.nextLine();
				if (destination.equals("")) {
					System.out.println("null값이 입력됐습니다. 다시 입력하세요");
					continue Loop2;		//아무것도 입력하지 않고 엔터를 입력할 경우 Loop2으로 돌아감.
				}

				Loop3: while (true) {
					try {
						System.out.println("동승자가 있습니까? 있다면 인원을 입력하고, 없다면 0을 입력해주세요.");
						fellow_num = Integer.parseInt(sc.nextLine());
						dto = new RequirementDTO(start_point, destination, fellow_num);
						result = dao.RequirementInsert(dto);
						String msg = "입력에 실패";
						if (result > 0) {
							System.out.println("입력에 성공");
						} else {
							System.out.println(msg);
						}
						break; //동승자 입력작업이 성공적으로 끝났으면 break를 통해 Loop3 반복문을 빠져나옴
					} catch (NumberFormatException e) {
						System.out.println("입력되지 않았습니다.");
					}

				}
				break;	//Loop2 빠져나옴
			}
			break;		//Loop1 빠져나옴 
		}
	}

}
