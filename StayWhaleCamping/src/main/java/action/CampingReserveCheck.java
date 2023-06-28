package action;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import svc.CampingReserveCheckService;
import vo.ActionForward;
import vo.Reserve_Camping;

public class CampingReserveCheck implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Reserve_Camping reserve_check = new Reserve_Camping();
        
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");
        reserve_check.setReserve_date(start);
        reserve_check.setExpire_date(end);
        System.out.println(start+"  "+end);
        // 시작 날짜와 종료 날짜를 LocalDate로 변환
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        // 날짜 차이 계산
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        String daysString = String.valueOf(daysBetween);
//        System.out.println("계산된 날"+daysString);
        
        CampingReserveCheckService crcs = new CampingReserveCheckService();
        ArrayList<Reserve_Camping> articleList = crcs.getReserve_check(reserve_check);
        
        Reserve_Camping emptyReservation = new Reserve_Camping();
        emptyReservation.setDaysBetween(daysString);

        if (articleList.isEmpty()) {
            articleList.add(emptyReservation);
        } else {
            Reserve_Camping firstItem = articleList.get(0);
            firstItem.setDaysBetween(daysString);
        }
        
        
        // 데이터를 JSON 형식으로 변환
        String json = new Gson().toJson(articleList);
        
        // Content-Type 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // JSON 데이터를 응답으로 전송
        response.getWriter().write(json);
        
        return null; // Ajax 요청이므로 ActionForward 객체를 반환할 필요가 없음
    }
}
