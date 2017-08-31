package view;


import java.util.List;

import model.DriverDTO;

public class DriverView {
	public static void print(List<DriverDTO> list) {
		System.out.println("탑승 가능 차량입니다.");

		for(DriverDTO st : list){
			String gender=st.getDriver_gender().trim();
			//trim()을 통해서 공백을 제거해줘야한다.
			if(gender.equals("F")){
				gender="여";				
			}else{
				gender="남";
			}
			String phone=st.getDrver_phone().trim();
			String phone2="";
			for(int i=0;i<11;i++)
			{
				phone2=phone2+phone.charAt(i);
				if(i==2 || i==6){
					phone2=phone2+"-";
				}
			}
			System.out.printf("ID:%d\t"+"이름:%s\t"+"폰번호:%s\t"+"성별:%s\t   "+"현재위치:[%c.%c]\n",st.getDriver_id(),st.getDriver_name(),phone2,gender,st.getCurrent_pos().charAt(0),st.getCurrent_pos().charAt(2));
			
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
