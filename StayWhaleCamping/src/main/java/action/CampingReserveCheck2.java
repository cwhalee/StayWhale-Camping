package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import svc.CampIndexService;
import svc.CampingLikeService;
import svc.CampingReserveCheckService;
import vo.Accmodation_Camping;
import vo.ActionForward;
import vo.Camping_Like;
import vo.Reserve_Camping;

public class CampingReserveCheck2 implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Reserve_Camping reserve_check = new Reserve_Camping();
        String room = request.getParameter("room_c");
        reserve_check.setFacility_num_c(room);
//        System.out.println(room);
        CampingReserveCheckService crcs = new CampingReserveCheckService();
        ArrayList<Reserve_Camping> articleList = crcs.getReserve_check(reserve_check);
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
