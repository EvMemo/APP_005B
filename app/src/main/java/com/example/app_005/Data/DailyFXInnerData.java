package com.example.app_005.Data;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DailyFXInnerData {
    public DailyFXData dailyFXData;
    //String stringTitle;
    public TextTag[] textTags;
    //        public String[] stringsText;
//        public String[] stringsImageUrl;
    public DailyFXInnerData(DailyFXData dailyFXData_A) throws IOException {
        dailyFXData=dailyFXData_A;
        if(dailyFXData.string_Url!=null)
        {
            ccDailyFXInnerHtml(dailyFXData.string_Url);

        }
    }
    void ccDailyFXInnerHtml(String string_Data) throws IOException {
        // string_Data="https://www.dailyfxasia.com/techs/20191115-18288.html";
        //]Log.w(this.toString(), "ccDailyFXInnerHtmlF1: "+"="+string_Data);
        Document document_A= Jsoup.connect(string_Data).get();
        Elements elements =document_A.select("article.dfx-article-inner");
        String string_B=elements.get(0).getElementsByClass("dfx-article-title").get(0).text();
        //]Log.w(this.toString(), "ccDailyFXInnerHtmlF2: "+"="+string_B );
        //]Log.w(this.toString(), "ccDailyFXInnerHtmlF0: "+"="+elements.html() );
        Element element_A=elements.select("div.dfx-article-content").get(0);
        //]Log.w(this.toString(), "ccDailyFXInnerHtmlF3: "+"="+element_A.childNodeSize() );
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF3: "+"="+element_A.child(0).text()+"="+element_A.child(1).text()  );
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF5: "+"="+element_A.child(0).nodeName()+"="+element_A.child(1).nodeName());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF5: "+"="+element_A.child(0).className()+"="+element_A.child(1).className());
//            ArrayList<String> arrayList_A=new ArrayList<>();
//            ArrayList<String> arrayList_B=new ArrayList<>();
        ArrayList<TextTag> textTags_A=new ArrayList<>();

        for(int i=0;i<element_A.childNodeSize();i++)
        {
            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF7: " +"="+i+"="+element_A.childNode(i).nodeName());

            String string_Tag=element_A.childNode(i).nodeName();
            switch(string_Tag){
                case "p"://text
                    //]Log.w(this.toString(), "ccDailyFXInnerHtmlJ0: " +"="+i+"="+element_A.childNode(i).outerHtml());

                    textTags_A.add(new TextTag(0,((Element)element_A.childNode(i)).text()));
                    break;
                case "a"://image
//                            String string_image="<image>="+arrayList_B.size();
//                            arrayList_A.add(string_image);
//                            arrayList_B.add(element_A.childNode(i).attr("href"));
//                            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF9: "+"="+element_A.childNode(i).attr("href") );
                    textTags_A.add(new TextTag(2,element_A.childNode(i).attr("href")));
                    break;
                case "div"://pass
                    break;
                case "h2"://Mtext
                    textTags_A.add(new TextTag(1,((Element)element_A.childNode(i)).text()));
                    break;
                case "#text"://text
                    // //]Log.w(this.toString(), "ccDailyFXInnerHtmlJ1: " +"="+i+"="+(element_A.childNode(i).toString()));
                    textTags_A.add(new TextTag(0,(element_A.childNode(i).toString())));
                    break;
                case "table"://pass
                    break;
                case "br"://pass
                    break;
                case "strong"://pass
                    break;
                case "span"://Mtext
                    //]Log.w(this.toString(), "ccDailyFXInnerHtmlJ1: " +"="+i+"="+((Element)element_A.childNode(i)).text());
                    textTags_A.add(new TextTag(1,((Element)element_A.childNode(i)).text()));
                    break;
                default:
                    //]Log.w(this.toString(), "ccDailyFXInnerHtmlE0: "+"="+string_Tag );
                    break;
            }
        }
        for(int i=0;i<textTags_A.size();i++)
        {
            //]Log.w(this.toString(), "ccDailyFXInnerHtmlW0: "+"="+i +"="+textTags_A.get(i).intMode +"="+textTags_A.get(i).stringData );

        }
        textTags=(TextTag[])textTags_A.toArray();

//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(1).tagName()+"="+element_A.child(6).text());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(2).tagName()+"="+element_A.child(6).text());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(3).tagName()+"="+element_A.child(6).text());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(4).tagName()+"="+element_A.child(6).text());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(5).tagName()+"="+element_A.child(6).text());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(6).tagName()+"="+element_A.child(6).text());
//            //]Log.w(this.toString(), "ccDailyFXInnerHtmlF6: "+"="+element_A.child(7).tagName()+"="+element_A.child(6).text());
        //Elements elements_B=elements.select("li.jsdfx-feed-item.dfx-real-time-news.jsdfx-feed-item-type-livenews");

    }
    public class TextTag{
        public int intMode;
        public String stringData;
        public TextTag(int int_Mode,String stringData_data){
            intMode=int_Mode;
            stringData=stringData_data;
        }
    }
    /**
     * 获取静态网页源码
     *
     * @param path the path
     * @return the html
     * @throws Exception the exception
     */
    public String getHtml(String path){
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
    private byte[] readInputStream(InputStream inStream) throws Exception{
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