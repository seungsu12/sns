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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class WeatherApi {

//        @Value("${weatherKey}")
        private String weatherKey ="cuNe1YMC4Qsginx6w4aexI6qIHu%2FbHYZPa34K58T3ipMCCHCmKuigo9imZv7FqYFOL0Lkit8QRlJH9T4Yd5bvQ%3D%3D";


    public static void main(String[] args){
        List<String> result = Arrays.asList("1","2","3");
        System.out.println(result.stream().map(s -> s.toString()).collect(Collectors.joining()));
        System.out.println(result.toString());
    }

    public Weather getWeather() {

        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";
        String nx = "55";
        String ny = "127";
        Map<String,String> timeMap = getTime();
        String baseDate = timeMap.get("date");
        String baseTime = timeMap.get("time");
        String targetTime = timeMap.get("targetTime");

        String type ="json";
        String numOfRows = "60";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?"+ URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)+"="+weatherKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("numOfRows",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(numOfRows, StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("dataType",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(type, StandardCharsets.UTF_8));	/* 타입 */
        urlBuilder.append("&" + URLEncoder.encode("base_date",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(baseDate, StandardCharsets.UTF_8)); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time",StandardCharsets.UTF_8) + "=" + URLEncoder.encode(baseTime, StandardCharsets.UTF_8));	/* 한 페이지 결과 수 */
        urlBuilder.append("&"+URLEncoder.encode("nx",StandardCharsets.UTF_8)+"="+URLEncoder.encode(nx,StandardCharsets.UTF_8));
        urlBuilder.append("&"+URLEncoder.encode("ny",StandardCharsets.UTF_8)+"="+URLEncoder.encode(ny,StandardCharsets.UTF_8));


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

                if(!String.valueOf(weather.get("fcstTime")).equals(targetTime)){
                    continue;
                }
                map.put(String.valueOf(weather.get("category")), String.valueOf(weather.get("fcstValue")));
            }

        return Weather.builder()
                .tmp(map.get("T1H"))
                .sky(checkSky(map.get("SKY")))
                .pcp(map.get("REH"))
                .time(LocalDateTime.now())
                .windSpeed(map.get("WSD"))
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
        int hour = LocalDateTime.now().getHour();
        String baseDate="";
        String baseTime ="";
        String targetTime ="";
        if (hour == 00) {
            baseTime= "2300";
            targetTime= "0100";
            baseDate =LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }else{
            baseDate =LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            baseTime =String.format("%02d00" ,hour-1);
            targetTime=String.format("%02d00",hour+1);
        }
        map.put("time",baseTime);
        map.put("date",baseDate);
        map.put("targetTime",targetTime);
        return  map;

    }

    private String checkSky(String value) {

        int key = Integer.valueOf(value);

       switch (key){
           case 3: return "구름많음";
           case 4 :return "흐림";
           default:return "맑음";
       }


    }

}
