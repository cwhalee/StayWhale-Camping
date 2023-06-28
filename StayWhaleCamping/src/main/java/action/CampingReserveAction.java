package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CampingLikeCheckService;
import svc.CampingReserveService;
import vo.ActionForward;
import vo.Camping_ALL;
import vo.Camping_Like;
import vo.Room_Info_Camping;


 public class CampingReserveAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	String reg_num = request.getParameter("reg_num_c");
		 	String user_id = request.getParameter("user_id");
		 	
		 	ArrayList<Object> articleList=new ArrayList<Object>();
		 	Camping_Like like_check = new Camping_Like();
		 	
		 	articleList = CampingReserveService.getCampReserve(reg_num);
		 	like_check = CampingLikeCheckService.getLikeCheck(reg_num, user_id);
			
		 	request.setAttribute("articleList", articleList);
			request.setAttribute("like_check", like_check);
			ActionForward forward= new ActionForward();
	   		forward.setPath("/Camp_Glam_Reserve.jsp");
	   		return forward; 
   		
	 }
	 
 }