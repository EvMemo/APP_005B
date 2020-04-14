package com.example.app_005.Tool;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterTool {
    /**
     * 获取静态网页源码
     *
     * @param path the path
     * @return the html
     * @throws Exception the exception
     */
    public static String getHtml(String path){
        String html=null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取html数据
            byte[] data = readInputStream(inStream);//得到html的二进制数据
            html = new String(data, "UTF-8");
        } catch (Exception e) {
            //Log.e(this.toString(), e.toString());
            e.printStackTrace();
        }
        return html;
    }
    /**
     * 转换InputStream数据
     *
     * @param inStream the in stream
     * @return the byte [ ]
     * @throws Exception the exception
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
