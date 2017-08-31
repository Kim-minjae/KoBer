package view;

import java.util.List;

import model.DriverDTO;

public class DriverView {
	public static void print(List<DriverDTO> list) {
		System.out.println("탑승 가능 차량입니다.");
		for (DriverDTO st : list) {
			System.out.print("ID: " + st.getDriver_id() + " 이름: " + st.getDriver_name() + " 폰번호: " + st.getDrver_phone()
					+ " 성별: " + st.getDriver_gender() + " 현재 위치: " + st.getCurrent_pos());
			System.out.println();

		}

	}

	public static void driverInfo(DriverDTO dto) {
		System.out.println("────────────운전자 정보 입니다.─────────────");
		System.out.printf(" ID\t: %-5d \t 이름\t: %-5s \n"
						+ " 폰번호\t: %-11s \t 성별\t: %-1s \n"
						+ " 면허번호\t: %-12s \t 현재위치\t: %-6s \n"
						+ " 가용범위\t: %-3d \n",dto.getDriver_id(),dto.getDriver_name(),dto.getDrver_phone(),dto.getDriver_gender(),dto.getLicence_num(),dto.getCurrent_pos(),dto.getRange());
		System.out.println("──────────────────────────────────────");
		System.out.println();

	}

}
