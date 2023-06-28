package action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CampingUnlikeService;
import vo.ActionForward;
import vo.Camping_Like;

 public class CampingUnlikeAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 Camping_Like like_check = new Camping_Like();
		 String id = request.getParameter("id");
		 String reg_num_c = request.getParameter("reg_num_c");
		 
		 like_check.setUser_id(id);
		 like_check.setReg_num_c(reg_num_c);
	 
		 CampingUnlikeService camplikeservice = new CampingUnlikeService();
		 camplikeservice.getUnlikecheck(like_check);
		 
		 return null;		
	 }
	 
 }