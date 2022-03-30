package code.sns.weather;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@Slf4j
public class WeatherApi {

        @Value("${weatherKey}")
        private static String weatherKey;

    public void getWeather() throws IOException {

        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";
        String nx = "60";
        String ny = "125";
        String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH"));
        String type ="json";
        String time = "0300";
        String numOfRows = "30";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?"+ URLEncoder.encode("ServiceKey", StandardCharsets.UTF_8)+"="+weatherKey);
        urlBuilder.append("&"+URLEncoder.encode("nx",StandardCharsets.UTF_8)+"="+URLEncoder.encode(nx,StandardCharsets.UTF_8));
        urlBuilder.append("&"+URLEncoder.encode("ny",StandardCharsets.UTF_8)+"="+URLEncoder.encode(ny,StandardCharsets.UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("base_date",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(baseDate, StandardCharsets.UTF_8)); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(baseTime, StandardCharsets.UTF_8)); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("dataType",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(type, StandardCharsets.UTF_8));	/* 타입 */
        urlBuilder.append("&" + URLEncoder.encode("numOfRows",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(numOfRows, StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("time",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(time, StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */


        URL url = new URL(urlBuilder.toString());
        BufferedReader bf ;
        String line;
        String result ="";

        bf = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = bf.readLine()) != null) {
            result = result.concat(line);

        }

        JSONParser jsonParser = new JSONParser(result);
        JsonOb


//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        String result= sb.toString();




   
    }

}
