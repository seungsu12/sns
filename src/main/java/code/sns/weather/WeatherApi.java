package code.sns.weather;


import code.sns.domain.Weather;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class WeatherApi {

//        @Value("${weatherKey}")
        private String weatherKey ="cuNe1YMC4Qsginx6w4aexI6qIHu%2FbHYZPa34K58T3ipMCCHCmKuigo9imZv7FqYFOL0Lkit8QRlJH9T4Yd5bvQ%3D%3D";


    public static void main(String[] args){
        WeatherApi api = new WeatherApi();
        api.getWeather();
    }

    public Weather getWeather() {

        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
        String nx = "55";
        String ny = "127";
        Map<String,String> timeMap = getTime();
        String baseDate = timeMap.get("date");
        String baseTime = timeMap.get("time");

        String type ="json";
        String time = baseTime;
        String numOfRows = "12";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?"+ URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)+"="+weatherKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("numOfRows",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(numOfRows, StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("dataType",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(type, StandardCharsets.UTF_8));	/* 타입 */
        urlBuilder.append("&" + URLEncoder.encode("base_date",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(baseDate, StandardCharsets.UTF_8)); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(time, StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&"+URLEncoder.encode("nx",StandardCharsets.UTF_8)+"="+URLEncoder.encode(nx,StandardCharsets.UTF_8));
        urlBuilder.append("&"+URLEncoder.encode("ny",StandardCharsets.UTF_8)+"="+URLEncoder.encode(ny,StandardCharsets.UTF_8));
//        urlBuilder.append("&" + URLEncoder.encode("base_time",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(baseTime, StandardCharsets.UTF_8)); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */


        try {
        URL url = new URL(urlBuilder.toString());
        BufferedReader bf ;
        String line;
        String result ="";

            bf = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = bf.readLine()) != null) {
                result = result.concat(line);

            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject parse_body = (JSONObject) response.get("body");
            JSONObject parse_items = (JSONObject) parse_body.get("items");
            JSONArray item = (JSONArray) parse_items.get("item");

            JSONObject weather;
            Map<String, String> map = new HashMap<>();

            for (int i = 0; i < item.size(); i++) {
                weather = (JSONObject) item.get(i);
                map.put(String.valueOf(weather.get("category")), String.valueOf(weather.get("fcstValue")));

            }

        return Weather.builder()
                .tmp(map.get("TMP"))
                .sky(checkSky(map.get("SKY")))
                .pcp(map.get("PCP"))
                .time(LocalDateTime.now())
                .windSpeed(map.get("UUU"))
                .build();

        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    private Map<String,String> getTime() {

        Map<String,String> map = new HashMap<>();
        int time = Integer.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));

        if (time <= 5) {
            map.put("date",LocalDate.now().minusDays(1) .format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            map.put("time","1700");
        }else{
            map.put("date",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            map.put("time",time >12 ? "0500" :"1700");
        }

        return  map;

    }

    private String checkSky(String value) {

        int key = Integer.valueOf(value);

        if (key <= 5) {
            return "맑음";
        } else if (key <= 8) {
            return "구름맑음";
        }
        return "흐림";

    }

}
