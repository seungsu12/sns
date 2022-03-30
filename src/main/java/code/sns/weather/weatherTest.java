package code.sns.weather;

import java.net.URLEncoder;

public class weatherTest {
    public static void main(String[] args) {
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));	/* 타입 */
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));	/* 한 페이지 결과 수 */

        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        //어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
        //System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result= sb.toString();
        System.out.println(result);


        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);
        // response 키를 가지고 데이터를 파싱
        JSONObject parse_response = (JSONObject) obj.get("response");
        // response 로 부터 body 찾기
        JSONObject parse_body = (JSONObject) parse_response.get("body");
        // body 로 부터 items 찾기
        JSONObject parse_items = (JSONObject) parse_body.get("items");

        // items로 부터 itemlist 를 받기
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        String category;
        JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
        // 카테고리와 값만 받아오기
        String day="";
        String time="";
        for(int i = 0 ; i < parse_item.size(); i++) {
            weather = (JSONObject) parse_item.get(i);
            Object fcstValue = weather.get("fcstValue");
            Object fcstDate = weather.get("fcstDate");
            Object fcstTime = weather.get("fcstTime");
            //double형으로 받고싶으면 아래내용 주석 해제
            //double fcstValue = Double.parseDouble(weather.get("fcstValue").toString());
            category = (String)weather.get("category");
            // 출력
            if(!day.equals(fcstDate.toString())) {
                day=fcstDate.toString();
            }
            if(!time.equals(fcstTime.toString())) {
                time=fcstTime.toString();
                System.out.println(day+"  "+time);
            }
            System.out.print("\tcategory : "+ category);
            System.out.print(", fcst_Value : "+ fcstValue);
            System.out.print(", fcstDate : "+ fcstDate);
            System.out.println(", fcstTime : "+ fcstTime);
    }
}
