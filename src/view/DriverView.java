package view;


import java.util.List;

import model.DriverDTO;
public class DriverView {
	public static void print( List<DriverDTO> list){
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
			/*
			System.out.print("ID: "+st.getDriver_id()+" 이름: "+st.getDriver_name()+" 폰번호: "+st.getDrver_phone()+
					" 성별: "+st.getDriver_gender()+" 현재 위치: "+"["+st.getCurrent_pos().charAt(0)+"."+st.getCurrent_pos().charAt(2)+"]");
			System.out.println();
			*/
			
			
		}
		
	}
}




