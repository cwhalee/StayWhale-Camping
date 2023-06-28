package action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import svc.ReserveInsertService;
import vo.ActionForward;
import vo.Reserve_Camping;


public class ReserveInsertAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionForward forward=null;
		Reserve_Camping reserve = null;   
		
		reserve = new Reserve_Camping();
		reserve.setReserve_date(request.getParameter("start_date"));
		reserve.setExpire_date(request.getParameter("end_date"));
		reserve.setUser_id(request.getParameter("user_id"));
		reserve.setFacility_num_c(request.getParameter("facility_num_c"));
			
		ReserveInsertService reserveinsertservice = new ReserveInsertService();
		boolean checking = reserveinsertservice.registArticle(reserve);
		
		if (!checking) {
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('해당일에는 예약할 수 없습니다.');");
		    out.println("history.back();");
		    out.println("</script>");
		} else {
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('예약완료');");
		    out.println("location.href='Camp_Glam_Index.cp';"); // 페이지 이동
		    out.println("</script>");
		}
		return forward;
	}
	
}