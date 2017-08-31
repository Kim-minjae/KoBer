package view;

import model.LogDTO;

import java.util.List;

/**
 * Created by pose2 on 2017-08-31.
 */
public class LogView {
    public static void printall(List<LogDTO> list){

        System.out.println("-------------------- Log 목록 --------------------");

        for(LogDTO st : list){

            System.out.printf("ID:%d\t"+"USER : %s \t"+"ACTION : %s\t ",st.getLog_id(),st.getLog_user(),st.getLog_action());
            System.out.println(st.getLog_time());

        }
        System.out.println("-------------------- Log 목록끝 --------------------");
    }
}
