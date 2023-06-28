package action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import svc.SearchKeywordService;
import vo.Accmodation_Camping;
import vo.ActionForward;
import vo.Camping_Youtube;


 public class SearchKeywordAction implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String keyword = request.getParameter("keyword");
		ArrayList<Accmodation_Camping> searchkeywordList=new ArrayList<Accmodation_Camping>();
		searchkeywordList = SearchKeywordService.keyword_search(keyword);
		
		// YouTube API 호출 및 데이터 가져오기
        String apiKey = "AIzaSyBWnOMs5_oWvp5w-K9qW7lYHFDfbpirHro";
        // 검색어
        String query = "캠핑팁";
        //Instant : 세계의 공식적인 평균시간을 사용할 수 있음
        Instant publishedAfter = Instant.now().minus(100, ChronoUnit.DAYS);

        // API 요청 URL 생성
        String url = "https://www.googleapis.com/youtube/v3/search" +
                "?key=" + URLEncoder.encode(apiKey, StandardCharsets.UTF_8.toString()) +
                "&part=snippet" +
                "&q=" + URLEncoder.encode(query, StandardCharsets.UTF_8.toString()) +
                "&type=video" +
                "&order=relevance" + //viewCount는 조회수, relevance는 관련성기준 검색
                "&publishedAfter=" + publishedAfter.toString() +
                "&maxResults=16" +
                "&regionCode=KR"; // 한국 영상만 검색이며 바꾸려면 KR부분 찾아서 바꾸면됨

        // API 요청 보내기
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        String apiResponseBody = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                    apiResponseBody = responseBuilder.toString();
            }
        } else {
            System.err.println("YouTube API 요청에 실패했습니다. 상태 코드: " + responseCode);
            return null;
        }

        // API 응답을 Gson을 활용해 처리
        JsonObject responseJson = new Gson().fromJson(apiResponseBody, JsonObject.class);
        JsonArray items = responseJson.getAsJsonArray("items");
        
        // 필요한 데이터를 추출하고, 필요한 형태로 가공하여 youtubeData에 저장
        ArrayList<Camping_Youtube> youtubeData = new ArrayList<>();
        // 최대 8개의 영상 데이터만 가져오도록 제한
        int size = Math.min(items.size(), 8); 
        for (int i = 0; i < size; i += 2) {
            JsonObject itemObject1 = items.get(i).getAsJsonObject();
            JsonObject itemObject2 = items.get(i + 1).getAsJsonObject();

            String videoId1 = itemObject1.getAsJsonObject("id").get("videoId").getAsString();
            String videoId2 = itemObject2.getAsJsonObject("id").get("videoId").getAsString();

            String title1 = itemObject1.getAsJsonObject("snippet").get("title").getAsString();
            String title2 = itemObject2.getAsJsonObject("snippet").get("title").getAsString();

            Camping_Youtube youtube1 = new Camping_Youtube();
            youtube1.setVideoId(videoId1);
            youtube1.setVideoTitle(title1);

            Camping_Youtube youtube2 = new Camping_Youtube();
            youtube2.setVideoId(videoId2);
            youtube2.setVideoTitle(title2);

            youtubeData.add(youtube1);
            youtubeData.add(youtube2);
        }
        request.setAttribute("youtubeData", youtubeData);		
		
		request.setAttribute("searchKeywordList", searchkeywordList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/Camp_Glam_Index.jsp");
   		return forward;   		
	 }
	 
 }