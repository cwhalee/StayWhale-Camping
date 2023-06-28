package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.CampIndexAction;
import action.CampIndexAction2;
import action.CampingLikeAction;
import action.CampingReserveAction;
import action.CampingReserveCheck;
import action.CampingUnlikeAction;
import action.ReserveInsertAction;
import action.SearchDateAction;
import action.SearchKeywordAction;
import action.SearchPlaceAction;
import vo.ActionForward;


@WebServlet("*.cp")
public class CampingProductController extends javax.servlet.http.HttpServlet 
{ // 진행방향 잡아주는 컨트롤러 각 조건의 맞는 action패키지의 Action파일을 실행한다.
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;

		if(command.equals("/Camp_Glam_Index.cp")){
			//action = new CampIndexAction();
			action = new CampIndexAction2();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/camping_reserve.cp")){
			action = new CampingReserveAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/reserve_insert.cp")){
			action = new ReserveInsertAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/search_date.cp")){
			action = new SearchDateAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/search_place.cp")){
			action = new SearchPlaceAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/search_keyword.cp")){
			action = new SearchKeywordAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/camping_Like.cp")){
			action = new CampingLikeAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/camping_unLike.cp")){
			action = new CampingUnlikeAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/reserve_check.cp")){
			action = new CampingReserveCheck();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		if(forward != null){	
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}