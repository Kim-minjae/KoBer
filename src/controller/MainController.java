package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by pose2 on 2017-08-25.
 */
public class MainController {
    public static void main(String args[]) throws IOException {



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



                        break;
                    default: System.out.println("맞는 케이스가 없습니다.");
                    continue Loop1;
                }
            }
        }

    }
}
