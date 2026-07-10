package com.itheima.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * 短信发送工具类
 */
public class SMSUtils {
    /**
     * 发送短信
     *
     * @param phoneNumbers
     * @param param
     */
    public static void sendShortMessage(String phoneNumbers, String param){
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        String host = "https://fesms.market.alicloudapi.com";//请求地址支持http和https及WEBSOCKET
        String path = "/sms/";//后缀
        String sign = "1"; //签名
        String skin = "1"; //模板
        String urlSend = host + path + "?code=" + param + "&phone="
                + phoneNumbers + "&sign=" + sign + "&skin=" + skin; //拼接请求链接
        String appCode = "ff4938669cb544e4815ab260a975c7df"; //查看AppCode
        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection)
                    url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE "
                    + appCode);// 格式:APPCODE
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                System.out.println("发送成功");
                System.out.println("获取返回的json:" + json);
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 &&
                        error.equals("Invalid AppCode`not exists`")) {
                    System.out.println("AppCode错误 ");
                } else if (httpCode == 400&&error.equals("Invalid Url")) {
                    System.out.println("请求的Method、Path或者环境错误");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    System.out.println("参数错误");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    System.out.println("服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    System.out.println("套餐包次数用完");
                } else {
                    System.out.println("参数名错误 或 其他错误");
                    System.out.println(error);
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("URL格式错误");
        } catch (UnknownHostException e) {
            System.out.println("URL地址错误");
        } catch (Exception e) {
            e.printStackTrace();// 打开注释查看详细报错异常信息
        }
    }

    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
