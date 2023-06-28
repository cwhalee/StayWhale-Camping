<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.awt.Graphics2D" %>
<%@ page import="java.awt.image.renderable.ParameterBlock" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.media.jai.JAI" %>
<%@ page import="javax.media.jai.RenderedOp" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.UUID" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="data" class="BoardDAO.DataProcess_Review"> </jsp:useBean>
	<jsp:useBean id="obj" class="DTO.Writer"> </jsp:useBean>
	<%
		String imgData = "";
		String saveDir = request.getRealPath("/review_image");
	    int size = 10 * 1024 * 1024; // 첨부 파일의 용량(10메가)
	    File currentDir = new File(saveDir);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDir);
		factory.setSizeThreshold(size);
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	try {
		List<FileItem> items = upload.parseRequest(request);
		for(FileItem fi : items) {
			if (fi.isFormField()) {
		        String fieldName = fi.getFieldName();
		        //out.println(fieldName);
		        String value = fi.getString("utf-8");
		        //out.println(value);

		        if (fieldName.equals("id")) {
		            obj.setUser_id(value);
		        } else if (fieldName.equals("title")) {
		            obj.setPost_title(value);
		        } else if (fieldName.equals("like")) {
		            obj.setPost_like(0);
		        } else if (fieldName.equals("txt")) {
		            obj.setPost_body(value.replace("\r\n", "<br>"));
		        } else if (fieldName.equals("travellocation")) {
		            obj.setPost_travel_location(value);
		        } else if (fieldName.equals("hNum")) {
		            obj.setPost_category(value);
		        } else if (fieldName.equals("reviewStar")) {
		            double rating = Double.parseDouble(value);
		            obj.setPost_rating(rating);
		        }
			}
			else {
				//out.println(fi.getFieldName());
				String origin = fi.getName();
				//out.println(origin);
				String ext = origin.substring(origin.lastIndexOf(".")); // 이미지 확장명 자르기
				
				UUID uuid = UUID.randomUUID();
				String name = uuid + ext;

				imgData += uuid + ext + ",";
				obj.setPost_file(imgData.substring(0, imgData.length()-1));
				//out.println(fi.getSize());
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			    //String today = formatter.format(java.time.LocalDate.now());
				//File upPath = new File(currentDir + "\\" + today);
				//if(!upPath.exists()) {
				//	upPath.mkdirs();
				//}
				fi.write(new File(currentDir, name));
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		data.review_insert(obj);
		response.sendRedirect("../reviewSelec.xr?page=1");
	%>
</body>
</html>