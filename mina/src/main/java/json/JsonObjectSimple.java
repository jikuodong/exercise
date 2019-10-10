package json;


import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;

/**
 * describe
 *
 * @author JKD
 * @version 1.0.0
 * @ClassName JsonObjectSimple.java
 * @createTime 2019Äê10ÔÂ09ÈÕ 15:35:00
 */
public class JsonObjectSimple {
    public static void main(String[] args) {
        JSONObject();
    }

    private static  void JSONObject() {
        JSONObject xiaoer = new JSONObject();
        Object nullObj = null;

        try {
            xiaoer.put("1","1");
            xiaoer.put("2","2");
            xiaoer.put("3","3");
            xiaoer.put("4","4");
            xiaoer.put("5","5");
            xiaoer.put("6","6");
            System.out.println(xiaoer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
