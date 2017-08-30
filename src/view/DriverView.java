package view;

import java.util.List;

import model.DriverDTO;
public class DriverView {
	public static void print( List<DriverDTO> list){
		System.out.println("탑승 가능 차량입니다.");
		for(DriverDTO st : list){
			System.out.print("ID: "+st.getDriver_id()+" 이름: "+st.getDriver_name()+" 폰번호: "+st.getDrver_phone()+
					" 성별: "+st.getDriver_gender()+" 현재 위치: "+st.getCurrent_pos());
			System.out.println();
			
			
		}
		
	}
}




