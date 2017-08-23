package controller;

import java.util.Scanner;

import model.RequirementDAO;
import model.RequirementDTO;

public class RequirementController {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		RequirementDTO dto = null;
		RequirementDAO dao = new RequirementDAO();
		
		System.out.println("요구사항 입력창입니다.");

		while (true) {
			System.out.println("탑승자의 출발 위치를 입력하세요. ex>(4,5) ");
			String start_point = sc.nextLine(); 

			System.out.println("도착지를 입력하세요. ex>(6,9) ");
			String destination = sc.nextLine();

			
			System.out.println("동승자가 있습니까? 있다면 인원을 입력하고, 없다면 0을 입력해주세요.");
			String fellow = sc.nextLine();

			if(start_point.equals("") || destination.equals("")){
				System.out.println("값이 입력되지 않았습니다. 다시 입력하세요");
				continue;

			}
			
			int fellow_num = Integer.parseInt(fellow);
			
		/*	dto = new RequirementDTO(0, start_point, destination, fellow_num);
			int result = dao.RequirementInsert(dto);
			
			String msg = "입력에 실패";
			if(result>0){
				System.out.println("입력에 성공");
			}else{
				System.out.println(msg);
			}
			*/

			break;
		}
	}
}
