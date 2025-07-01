/**
 * 
 */
package egovframework.example.job.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author user
 * 스케줄러 클래스: 특정시간에 반복적으로 실행되는 클래스
 * => 공공데이터포털에서 10초마다 데이터를 받아서 파일에 내용 쓰기
 * 참고) AI/빅데이터 처리: 1)수집 2)가공 3)적재(DB,파일 등)
 */
@Log4j2
@Component
public class PublicApiJob {
//   상수 정의
   final String serviceKey = "INH5JlH9iuKNiuZVX2tblTV7m9CqLf0rNKopVhVq8vF0LpzZNp658j7xXeucRpukCmqE%2BekjfJk9g3%2BPWAGWZA%3D%3D"; // 공공데이터 API 인증키
   final String url="https://api.odcloud.kr/api/15096711/v1/uddi:aa5a69ca-a427-4b7f-b5cd-d39d347f1327?page=1&perPage=10&serviceKey="+serviceKey;
   
//   10초마다 크론식(cron = "초 분 시 일 월 요일")
   @Scheduled(cron = "*/10 * * * * ?")
   public void autoInsert() throws Exception {
//      1) URL 요청 클래스 생성: okhttp
      OkHttpClient client = new OkHttpClient();
//      2) 요청 정보 만들기(인터넷주소:url(공공데이터주소))
//      사용법: Request 변수 = new Request.Builder().url(인터넷주소).build();
      Request request = new Request.Builder().url(url).build(); // 요청 정보
//      3) 실제 요청: 공공데이터포털에
//      사용법: Response 결과변수 = client.newCall(요청변수).execute();
      Response response = client.newCall(request).execute();    // 결과 클래스(객체)
//      4) 결과받기: 글자들(String변수)
      String json=response.body().string();
      log.info(json);
//      5) 변수 내용을 파일로 쓰기
      jsonWriter(json,"c://Work/output/cooks.json");
   }
   
//   파일로 쓰는 메소드:
   public void jsonWriter(String json, String path) throws Exception {
       // 결과 데이터를 파일로 저장: 파일이 있으면 덮어쓰기가 됩니다.
       String filePath = path;
       BufferedWriter writer = null;

       try {
           writer = new BufferedWriter(new FileWriter(filePath));
           writer.write(json);
       } catch (IOException e) {
           log.info("파일 오류 : "+e.toString());
       } finally {
           if (writer != null) writer.close();
       }
   }
}





