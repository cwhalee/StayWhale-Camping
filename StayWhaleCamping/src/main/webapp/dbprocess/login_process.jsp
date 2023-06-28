<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
	<% 
		request.setCharacterEncoding("utf-8");
		String id_get = request.getParameter("user_id");
		String pw_get = request.getParameter("user_pass");
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/websitedb","root","1234");
			if(conn == null) {
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			}
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select user_id, password from sign_up where user_id='" + id_get + "';");

		if(rs.next()) {
			String id = rs.getString("user_id");
			String pw = rs.getString("password");
			if(id_get.equals(id) && pw_get.equals(pw)) {
				request.setAttribute("id", id);
				session.setAttribute("id", id);
				String returnUrl = (String)session.getAttribute("returnUrl");
				if(returnUrl.contains("selecHotel.xr")) {
					String hNum = null;
					String cin = null;
					String cout = null;

					String[] params = returnUrl.split("[?&]"); // "?" 또는 "&"을 기준으로 문자열을 분리합니다.

					for (String param : params) {
					    String[] keyValue = param.split("="); // "="을 기준으로 키와 값으로 분리합니다.

					    if (keyValue.length == 2) {
					        String key = keyValue[0];
					        String value = keyValue[1];

					        switch (key) {
					            case "hNum":
					                hNum = value;
					                break;
					            case "cin":
					                cin = value;
					                break;
					            case "cout":
					                cout = value;
					                break;
					            default:
					                // 다른 매개변수 처리
					                break;
					        }
					    }
					}
					%>
						<script>
							var hNum = '<%= hNum %>'; // 자바 변수 값을 자바스크립트 변수에 할당합니다.
					        var user_id = '<%= id %>'; // 자바 변수 값을 자바스크립트 변수에 할당합니다.
					        var cin = '<%= cin %>'; // 자바 변수 값을 자바스크립트 변수에 할당합니다.
					        var cout = '<%= cout %>'; // 자바 변수 값을 자바스크립트 변수에 할당합니다.
					        
					        location.href = "../selecHotel.xr?hNum="+hNum+"&id="+user_id+"&cin="+cin+"&cout="+cout;
						</script>
					<%
				} else {
					response.sendRedirect(returnUrl);
				}
			} else {
				%>
				<script>
					alert("비밀번호를 확인해주세요")
					history.back();
				</script>
				<%
			}

		}  else {
			%>
			<script>
				alert("회원가입을 먼저 진행해주세요.")
				history.back();
			</script>
			<%
		}
		
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {

			} try {
				conn.close();
			} catch (Exception ignored) {

			}
		}
	%>