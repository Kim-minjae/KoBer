package controller;

import model.DriverDAO;
import model.PassengerDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by pose2 on 2017-08-25.
 */
public class MainController {
    public static void main(String args[]) throws IOException {

        DriverDAO driverDAO = new DriverDAO();
        PassengerDAO passengerDAO = new PassengerDAO();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean session = true;

        Loop1 : while (true) {
            System.out.println("다음 메뉴를 골라주세요 : ");

            System.out.println("1.회원가입 ");
            System.out.println("2.로그인 ");
            String tmp = br.readLine();
            if (!tmp.equals("1") && !tmp.equals("2")) {
                continue Loop1;
            }else {

                switch (tmp){
                    case "1" :
                        //회원가입 호출
                        break;
                    case "2" :
                        //로그인 호출
                        System.out.println(" 전화번호와 이름을 순서대로 입력해주세요 : <ex) 010-1111-2222,홍길동>");
                        st = new StringTokenizer(br.readLine());

                        String phone_num_tmp = st.nextToken();
                        String user_name_tmp = st.nextToken();

                        try {
                            if(driverDAO.D_login(phone_num_tmp,user_name_tmp) == -1){
                                if(passengerDAO.P_login(phone_num_tmp,user_name_tmp) == -1){
                                    System.out.println("해당하는 유저가 없습니다. 다시 메인화면으로 돌아갑니다.");
                                    continue Loop1;
                                }else if(passengerDAO.P_login(phone_num_tmp,user_name_tmp) == 1){

                                }
                            }

                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    default: System.out.println("맞는 케이스가 없습니다.");
                    continue Loop1;
                }
            }
        }

    }
}
