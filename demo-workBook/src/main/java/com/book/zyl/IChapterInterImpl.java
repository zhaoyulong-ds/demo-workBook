package com.book.zyl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoYuLong on 2022/1/24 17:22
 */
public class IChapterInterImpl implements IChapterInter{

    protected String crawl(String url) throws Exception {
        //采用HttpClient技术
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url));
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Chapter> getChapter(String url) {
        try {
            String result = crawl(url);
            Document doc = Jsoup.parse(result);
            Elements as = doc.select("#list dd a");
            List<Chapter> chapters = new ArrayList<>();
            for (Element a : as) {
                Chapter chapter = new Chapter();
                chapter.setTitle(a.text());
                chapter.setUrl("https://www.biquge.biz/" + a.attr("href"));
                String resultC = crawl(chapter.getUrl());
                Document docC = Jsoup.parse(resultC);
                Element body = docC.body();
                Element content = body.getElementById("content");
                String text = content.text();
                text = text.replace(" "," ");
                chapter.setContent(text);
                chapters.add(chapter);
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
